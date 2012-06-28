package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public class LispVoid implements LispValue {
	private static LispVoid instance;
	public static LispVoid getInstance(){
		if(instance==null)
			instance = new LispVoid();
		return instance; 
	}
	private LispVoid(){}
	@Override
	public LispValue eval(LispContext context) throws LispRuntimeException {
		return this;
	}
	public String toString(){
		return "(void)";
	}
}
