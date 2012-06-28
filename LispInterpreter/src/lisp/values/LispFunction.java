package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;


public abstract class LispFunction implements LispValue {
	public abstract LispValue execute(LispValue... args) throws LispRuntimeException;

	@Override
	public LispValue eval(LispContext context) throws LispRuntimeException {
		return this;
	}
}
