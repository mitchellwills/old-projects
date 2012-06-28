package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public abstract class MathFunction extends LispFunction{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("+", MathFunction.PLUS);
		context.define("-", MathFunction.MINUS);
		context.define("*", MathFunction.MULTIPLY);
		context.define("/", MathFunction.DIVIDE);
	}
	
	public static final MathFunction PLUS = new MathFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			double sum = 0;
			for(int i = 0; i<args.length; ++i){
				if(args[i] instanceof NumberValue){
					sum += ((NumberValue)args[i]).getValue();
				}
				else
					throw new LispRuntimeException("Cannot add values of non number type");
			}
			return new NumberValue(sum);
		};
	};
	public static final MathFunction MINUS = new MathFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			double difference = 0;

			if(args[0] instanceof NumberValue){
				difference = ((NumberValue)args[0]).getValue();
			}
			else
				throw new LispRuntimeException("Cannot subtract values of non number type");
			for(int i = 1; i<args.length; ++i){
				if(args[i] instanceof NumberValue){
					difference -= ((NumberValue)args[i]).getValue();
				}
				else
					throw new LispRuntimeException("Cannot subtract values of non number type");
			}
			return new NumberValue(difference);
		};
	};
	public static final MathFunction MULTIPLY = new MathFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			double product = 1;
			
			for(int i = 0; i<args.length; ++i){
				if(args[i] instanceof NumberValue){
					product *= ((NumberValue)args[i]).getValue();
				}
				else
					throw new LispRuntimeException("Cannot multiply values of non number type");
			}
			return new NumberValue(product);
		};
	};
	public static final MathFunction DIVIDE = new MathFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			double quotient = 0;

			if(args[0] instanceof NumberValue){
				quotient = ((NumberValue)args[0]).getValue();
			}
			else
				throw new LispRuntimeException("Cannot subtract values of non number type");
			for(int i = 1; i<args.length; ++i){
				if(args[i] instanceof NumberValue){
					quotient /= ((NumberValue)args[i]).getValue();//TODO handle divide by 0
				}
				else
					throw new LispRuntimeException("Cannot subtract values of non number type");
			}
			return new NumberValue(quotient);
		};
	};

}
