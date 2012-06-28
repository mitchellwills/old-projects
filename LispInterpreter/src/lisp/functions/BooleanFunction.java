package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public abstract class BooleanFunction extends LispFunction{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("or", BooleanFunction.OR);
		context.define("and", BooleanFunction.AND);
		context.define("not", BooleanFunction.NOT);
	}
	
	public static final BooleanFunction OR = new BooleanFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			boolean value = false;
			for(int i = 0; i<args.length; ++i){
				if(args[i] instanceof BooleanValue){
					value |= ((BooleanValue)args[i]).getValue();
				}
				else
					throw new LispRuntimeException("Cannot or values of non boolean type");
			}
			return new BooleanValue(value);
		}
	};
	
	public static final BooleanFunction AND = new BooleanFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			boolean value = true;
			for(int i = 0; i<args.length; ++i){
				if(args[i] instanceof BooleanValue){
					value &= ((BooleanValue)args[i]).getValue();
				}
				else
					throw new LispRuntimeException("Cannot and values of non boolean type");
			}
			return new BooleanValue(value);
		}
	};
	
	public static final BooleanFunction NOT = new BooleanFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=1)
				throw new LispRuntimeException("Not takes one argument");
			if(args[0] instanceof BooleanValue)
				return new BooleanValue(!((BooleanValue)args[0]).getValue());
			else
				throw new LispRuntimeException("Not takes a single boolean value");
		}
	};

}
