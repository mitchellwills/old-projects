package options;

import java.util.HashMap;
import java.util.Map;

import options.field.BooleanField;
import options.field.EnumField;
import options.field.IntegerField;
import options.field.StringField;
import options.field.ValueField;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TypeManager {
	private static Map<Class<?>, Class<ValueField<?>>> fieldTypes = new HashMap<Class<?>, Class<ValueField<?>>>();
	
	private static <T> void registerType(Class<T> type, Class<? extends ValueField<T>> fieldType){
		fieldTypes.put(type, (Class<ValueField<?>>)(Class<?>)fieldType);
	}

	public static <T> ValueField<T> getField(String name, T initial, Class<T> clazz) {
		Class<ValueField<?>> type = fieldTypes.get(clazz);
		if(type!=null){
			try {
				return (ValueField<T>)type.getConstructor(String.class, clazz).newInstance(name, initial);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Class<?> aType:fieldTypes.keySet()){//TODO ensure best fit
			if(aType.isAssignableFrom(clazz)){
				try {
					return (ValueField<T>)fieldTypes.get(aType).getConstructor(String.class, aType, Class.class).newInstance(name, initial, clazz);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	static{
		registerType(Integer.class, IntegerField.class);
		registerType(Boolean.class, BooleanField.class);
		registerType(String.class, StringField.class);
		registerType(Enum.class, (Class<ValueField<Enum>>)(Class<?>)EnumField.class);
	}
}
