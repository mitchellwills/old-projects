package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public abstract class StringFunction extends LispFunction{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("empty?", StringFunction.EMPTY);
	}
	
	public static final StringFunction EMPTY = new StringFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=1)
				throw new LispRuntimeException("empty? takes one argument");
			if(args[0] instanceof StringValue)
				return new BooleanValue(((StringValue)args[0]).getValue().isEmpty());
			else
				throw new LispRuntimeException("empty? takes a single String value");
		}
	};

}
