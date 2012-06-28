package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.IdentifierValue;
import lisp.values.LispForm;
import lisp.values.LispValue;
import lisp.values.LispVoid;
import lisp.values.SExpression;

public abstract class ControlFlowForm extends LispForm{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("if", IF);
		context.define("cond", COND);
		context.define("begin", BEGIN);
	}

	
	public static final ControlFlowForm IF = new ControlFlowForm(){
		@Override
		public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
			if(args.length!=2 && args.length!=3)
				throw new LispRuntimeException("If must have the form (if test-expr then-expr) or (if test-expr then-expr else-expr)");
			if(args[0].eval(context).equals(LispValue.BooleanValue.TRUE))
				return args[1].eval(context);
			else if(args.length==3)
				return args[2].eval(context);
			else
				return LispVoid.getInstance();
		}
	};
	
	public static final ControlFlowForm COND = new ControlFlowForm(){
		@Override
		public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
			for(int i = 0; i<args.length; ++i){
				if(args[i] instanceof SExpression){
					SExpression clause = (SExpression)args[i];
					if(clause.size()!=2)
						throw new LispRuntimeException("Cond must have the form (cond [test-expr then-expr] ...)");
					if(clause.getElement(0).equals(new IdentifierValue("else")))
						return clause.getElement(1).eval(context);
					if(clause.getElement(0).eval(context).equals(LispValue.BooleanValue.TRUE))
						return clause.getElement(1).eval(context);
				}
				else
					throw new LispRuntimeException("Cond must have the form (cond [test-expr then-expr] ...)");
			}
			return LispVoid.getInstance();
		}
	};
	
	public static final ControlFlowForm BEGIN = new ControlFlowForm(){
		@Override
		public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
			LispValue finalValue = LispValue.VOID;
			for(int i = 0; i<args.length; ++i){
				finalValue = args[i].eval(context);
			}
			return finalValue;
		}
	};
	

}
