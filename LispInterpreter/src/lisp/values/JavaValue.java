package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public class JavaValue implements LispValue {
	private Object value;
	public JavaValue(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}

	@Override
	public LispValue eval(LispContext context) throws LispRuntimeException {
		return this;
	}

}
