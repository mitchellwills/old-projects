package lisp.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public class JavaFunction extends LispFunction{
	
	public JavaFunction(Object context, String className, String methodName, Class<?>[] methodArgs) throws ClassNotFoundException, SecurityException, NoSuchMethodException{
		this(context, Class.forName(className), methodName, methodArgs);
	}
	
	public JavaFunction(Object context, Class<?> clazz, String methodName, Class<?>[] methodArgs) throws SecurityException, NoSuchMethodException{
		this(context, clazz.getMethod(methodName, methodArgs));
	}
	
	
	
	private Object context;
	private Method method;
	private Class<?>[] argTypes;
	public JavaFunction(Object context, Method method){
		this.context = context;
		if(method==null)
			throw new NullPointerException("The method for a java function must not be null");
		this.method = method;
		argTypes = method.getParameterTypes();
	}
	
	
	@Override
	public LispValue execute(LispValue... args) throws LispRuntimeException {
		if(args.length!=argTypes.length)
			throw new LispRuntimeException("The function takes "+argTypes.length+" arguments");
		Object[] methodArgs = new Object[argTypes.length];
		for(int i = 0; i<argTypes.length; ++i){
			methodArgs[i] = LispJava.toJava(args[i], argTypes[i]);
		}
		try {
			Object returnValue = method.invoke(context, methodArgs);
			return LispJava.toLisp(returnValue, method.getReturnType());
		} catch (InvocationTargetException e) {
			throw new LispRuntimeException("Exception in java method call", e.getCause());
		} catch (Exception e) {
			throw new LispRuntimeException("Error calling java method", e);
		}
		
	}
}
