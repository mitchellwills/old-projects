package lisp.runtime;

import java.util.HashMap;
import java.util.Map;

import lisp.values.LispValue;

public class LispContext {
	private Map<String, LispValue> variables = new HashMap<String, LispValue>();
	private LispContext superContext;
	public LispContext() {
		this(LispGlobalContext.getInstance());
	}
	public LispContext(LispContext superContext) {
		this.superContext = superContext;
	}
	public LispValue lookup(String name) throws LispRuntimeException{
		LispValue value;
		if((value = variables.get(name)) == null){
			if(superContext!=null)
				return superContext.lookup(name);
			throw new LispRuntimeException("Lookup of '"+name+"' failed. It does not exist!!!");
		}
		return value;
	}
	public void define(String name, LispValue value) throws LispRuntimeException{
		if(name==null)
			throw new LispRuntimeException("Cannot set a value with null name");
		if(value==null)
			throw new LispRuntimeException("Set of "+name+" failed. The vaue cannot be null!!!");
		if(superContext==null || !superContext.setIfExists(name, value))
			variables.put(name, value);
	}
	
	public void set(String name, LispValue value) throws LispRuntimeException{
		if(name==null)
			throw new LispRuntimeException("Cannot set a value with null name");
		if(value==null)
			throw new LispRuntimeException("Set of "+name+" failed. The vaue cannot be null!!!");
		if(superContext==null || !superContext.setIfExists(name, value))
			if(!setIfExists(name, value))
				throw new LispRuntimeException(name+" has not been defined");
	}
	
	private boolean setIfExists(String name, LispValue value) throws LispRuntimeException{
		if(variables.containsKey(name)){
			variables.put(name, value);
			return true;
		}
		return false;
	}
}
