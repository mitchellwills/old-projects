package options;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import options.field.ValueField;

public abstract class SimpleOptionSet extends AbstractOptionSet {

	protected LinkedHashMap<ValueField<?>, String> buildValueFields(){
		LinkedHashMap<ValueField<?>, String> valueFields = new LinkedHashMap<ValueField<?>, String>();
		
		try {
			addFields(valueFields);
			addMethods(valueFields);
		} catch (Exception e) {
			// TODO handle exception
			e.printStackTrace();
		}
		
		return valueFields;
	}

	@Target(ElementType.FIELD)
	@Retention(value = RetentionPolicy.RUNTIME)
	public @interface OptionField {
		String label();

		Class<?> type();
	}
	private void addFields(LinkedHashMap<ValueField<?>, String> valueFields) throws Exception{
		Field[] declaredFields = getClass().getDeclaredFields();
		for (Field optionField : declaredFields) {
			OptionField optionData = optionField
					.getAnnotation(OptionField.class);
			if (optionData != null) {
				optionField.setAccessible(true);

				ValueField<?> field = OptionProvider.put(
						optionField.get(null).toString(),
						optionData.type(), null);
				String label = optionData.label();
				
				valueFields.put(field, label);
			}
		}
	}
	
	
	
	
	

	@Target(ElementType.METHOD)
	@Retention(value = RetentionPolicy.RUNTIME)
	public @interface OptionMethod {
		String name();

		String label();
	}
	private void addMethods(LinkedHashMap<ValueField<?>, String> valueFields) throws Exception{
		Method[] declaredMethods = getClass().getDeclaredMethods();
		for (Method optionMethod : declaredMethods) {
			OptionMethod optionData = optionMethod
					.getAnnotation(OptionMethod.class);
			if (optionData != null) {
				optionMethod.setAccessible(true);

				ValueField<?> field = OptionProvider.put(
						optionData.name(), optionMethod.getReturnType(),
						null);
				String label = optionData.label();
				valueFields.put(field, label);
			}
		}
	}
}
