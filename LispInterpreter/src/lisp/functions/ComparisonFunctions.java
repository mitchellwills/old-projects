package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public abstract class ComparisonFunctions extends LispFunction{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("<", ComparisonFunctions.LT);
		context.define(">", ComparisonFunctions.GT);
		context.define("<=", ComparisonFunctions.LE);
		context.define(">=", ComparisonFunctions.GE);
		context.define("=", ComparisonFunctions.E);
		context.define("equals?", ComparisonFunctions.EQUALS);
	}

	public static final ComparisonFunctions LT = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Less than must have form (< val1 val2)");
			if(args[0] instanceof NumberValue && args[1] instanceof NumberValue)
				return LispJava.toLisp(LispJava.toDouble(args[0])<LispJava.toDouble(args[1]));
			
			throw new LispRuntimeException("Less than can only operate on numbers");
		}
	};
	public static final ComparisonFunctions GT = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Greater than must have form (> val1 val2)");
			if(args[0] instanceof NumberValue && args[1] instanceof NumberValue)
				return LispJava.toLisp(LispJava.toDouble(args[0])>LispJava.toDouble(args[1]));
			
			throw new LispRuntimeException("Greater than can only operate on numbers");
		}
	};
	public static final ComparisonFunctions LE = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Less than or equal to must have form (<= val1 val2)");
			if(args[0] instanceof NumberValue && args[1] instanceof NumberValue)
				return LispJava.toLisp(LispJava.toDouble(args[0])<=LispJava.toDouble(args[1]));
			
			throw new LispRuntimeException("Less than or equal to can only operate on numbers");
		}
	};
	public static final ComparisonFunctions GE = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Greater than than or equal to must have form (>= val1 val2)");
			if(args[0] instanceof NumberValue && args[1] instanceof NumberValue)
				return LispJava.toLisp(LispJava.toDouble(args[0])>=LispJava.toDouble(args[1]));
			
			throw new LispRuntimeException("Greater than or equal to can only operate on numbers");
		}
	};
	public static final ComparisonFunctions E = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Equal to must have form (= val1 val2)");
			if(args[0] instanceof NumberValue && args[1] instanceof NumberValue)
				return LispJava.toLisp(LispJava.toDouble(args[0])==LispJava.toDouble(args[1]));
			
			throw new LispRuntimeException("Equal to can only operate on numbers");
		}
	};
	public static final ComparisonFunctions EQUALS = new ComparisonFunctions(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length!=2)
				throw new LispRuntimeException("Equal to must have form (equals? val1 val2)");
			return new LispValue.BooleanValue(args[0].equals(args[1]));
		}
	};

}
