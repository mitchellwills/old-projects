package lisp.runtime;

import lisp.values.IdentifierValue;
import lisp.values.JavaValue;
import lisp.values.LispValue;
import lisp.values.LispVoid;
import lisp.values.SExpression;

public final class LispJava {
	private LispJava(){}
	
	public static Object toJava(LispValue value, Class<?> expectedType) throws LispRuntimeException{
		if(Object.class.isAssignableFrom(expectedType) && value instanceof JavaValue){
			Object javaValue = ((JavaValue)value).getValue();
			if(javaValue==null)
				return null;
			else if(expectedType.isInstance(javaValue))
				return javaValue;
			else
				throw new LispRuntimeException("JavaValue does not have a value of java type: "+expectedType.getName());
		}
		else if(Double.class.isAssignableFrom(expectedType) || Double.TYPE.isAssignableFrom(expectedType)){
			if(value instanceof LispValue.NumberValue)
				return ((LispValue.NumberValue)value).getValue();
			else
				throw new LispRuntimeException(value + " cannot be converted to a java double");
		}
		else if(Integer.class.isAssignableFrom(expectedType) || Integer.TYPE.isAssignableFrom(expectedType)){
			if(value instanceof LispValue.NumberValue){
				Double doubleValue = ((LispValue.NumberValue)value).getValue();
				return doubleValue.intValue();
			}
			else
				throw new LispRuntimeException(value + " cannot be converted to a java integer");
		}
		else if(Boolean.class.isAssignableFrom(expectedType) || Boolean.TYPE.isAssignableFrom(expectedType)){
			if(value instanceof LispValue.BooleanValue)
				return ((LispValue.BooleanValue)value).getValue();
			else
				throw new LispRuntimeException(value + " cannot be converted to a java boolean");
		}
		else if(Character.class.isAssignableFrom(expectedType) || Character.TYPE.isAssignableFrom(expectedType)){
			if(value instanceof LispValue.CharacterValue)
				return ((LispValue.CharacterValue)value).getValue();
			else
				throw new LispRuntimeException(value + " cannot be converted to a java character");
		}
		else if(String.class.isAssignableFrom(expectedType)){
			if(value instanceof LispValue.StringValue)
				return ((LispValue.StringValue)value).getValue();
			else
				throw new LispRuntimeException(value + " cannot be converted to a java String");
		}
		else if(LispValue.class.isAssignableFrom(expectedType)){
			return value;
		}
		else if(Void.TYPE.isAssignableFrom(expectedType) || Void.class.isAssignableFrom(expectedType)){
			return null;
		}
		else
			throw new LispRuntimeException("Unsuported type: "+expectedType.getName());
	}
	public static double toDouble(LispValue value) throws LispRuntimeException{
		return (Double) toJava(value, Double.TYPE);
	}
	public static int toInteger(LispValue value) throws LispRuntimeException{
		return (Integer) toJava(value, Integer.TYPE);
	}
	public static boolean toBoolean(LispValue value) throws LispRuntimeException{
		return (Boolean) toJava(value, Boolean.TYPE);
	}
	public static char toCharacter(LispValue value) throws LispRuntimeException{
		return (Character) toJava(value, Character.TYPE);
	}
	public static String toString(LispValue value) throws LispRuntimeException{
		return (String) toJava(value, String.class);
	}
	@SuppressWarnings("unchecked")
	public static <T> T toJavaValue(LispValue value, Class<T> type) throws LispRuntimeException{
		try{
			return (T) toJava(value, type);
		} catch (ClassCastException e){
			throw new LispRuntimeException("Cannot convert lisp value to java type: "+type.getName(), e);
		}
	}
	
	
	
	

	public static LispValue toLisp(Object value) throws LispRuntimeException{
		if(value==null)
			throw new LispRuntimeException("Cannot convert null to lisp value");
		return toLisp(value, value.getClass());
	}
	public static LispValue toLisp(Object value, Class<?> type) throws LispRuntimeException{
		if(Void.TYPE.isAssignableFrom(type))
			return LispVoid.getInstance();
		
		if(value==null)
			throw new LispRuntimeException("Cannot convert null to lisp value");
		
		if(Boolean.class.isAssignableFrom(type) || Boolean.TYPE.isAssignableFrom(type))
			return new LispValue.BooleanValue(((Boolean)value).booleanValue());
		else if(Character.class.isAssignableFrom(type) || Character.TYPE.isAssignableFrom(type))
			return new LispValue.CharacterValue(((Character)value).charValue());
		else if(Number.class.isAssignableFrom(type) || Integer.TYPE.isAssignableFrom(type)
				|| Short.TYPE.isAssignableFrom(type) || Byte.TYPE.isAssignableFrom(type)
				|| Long.TYPE.isAssignableFrom(type) || Double.TYPE.isAssignableFrom(type)
				|| Float.TYPE.isAssignableFrom(type))
			return new LispValue.NumberValue(((Number)value).doubleValue());
		else if(String.class.isAssignableFrom(type))
			return new LispValue.StringValue((String)value);
		else if(LispValue.class.isAssignableFrom(type))
			return (LispValue)value;
		else if(Object.class.isAssignableFrom(type))
			return new JavaValue(value);
		else
			throw new LispRuntimeException("Unsuported Java type: "+type.getName());
	}
	public static LispValue toLisp(double value) throws LispRuntimeException{
		return toLisp(value, Double.TYPE);
	}
	public static LispValue toLisp(int value) throws LispRuntimeException{
		return toLisp(value, Integer.TYPE);
	}
	public static LispValue toLisp(boolean value) throws LispRuntimeException{
		return toLisp(value, Boolean.TYPE);
	}
	public static LispValue toLisp(char value) throws LispRuntimeException{
		return toLisp(value, Character.TYPE);
	}
	public static LispValue toLisp(String value) throws LispRuntimeException{
		return toLisp(value, String.class);
	}
	public static LispValue toLispJavaValue(Object value) throws LispRuntimeException{
		return toLisp(value, Object.class);
	}

	
	
	
	
	

	public static void callLispFunction(String name, Object... args) throws LispRuntimeException{
		callLispFunction(Void.TYPE, name, args);
	}
	
	public static <T> T callLispFunction(Class<T> expectedType, String name, Object... args) throws LispRuntimeException{
		return callLispFunction(LispGlobalContext.getInstance(), expectedType, name, args);
	}
	
	public static void callLispFunction(LispContext context, String name, Object... args) throws LispRuntimeException{
		callLispFunction(context, Void.TYPE, name, args);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T callLispFunction(LispContext context, Class<T> expectedType, String name, Object... args) throws LispRuntimeException{
		return (T)toJava(functionCallFromJavaArgs(name, args).eval(context), expectedType);
	}
	
	public static SExpression functionCallFromJavaArgs(String functionName, Object... values) throws LispRuntimeException{
		LispValue[] lispValues = new LispValue[values.length+1];
		lispValues[0] = new IdentifierValue(functionName);
		for(int i = 0; i<values.length; ++i){
			lispValues[i+1] = LispJava.toLisp(values[i]);
		}
		return new SExpression(lispValues);
	}

	public static SExpression toSExpression(Object... values) throws LispRuntimeException{
		LispValue[] lispValues = new LispValue[values.length];
		for(int i = 0; i<values.length; ++i){
			lispValues[i] = LispJava.toLisp(values[i]);
		}
		return new SExpression(lispValues);
	}
}
