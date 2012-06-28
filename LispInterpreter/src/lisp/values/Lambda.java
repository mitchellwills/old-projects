package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public class Lambda extends LispFunction {
	private LispValue expression;
	private LispContext context;
	private String[] argNames;
	public Lambda(LispValue expression, String[] argNames, LispContext context){
		this.expression = expression;
		this.argNames = argNames;
		this.context = context;
	}
	@Override
	public LispValue execute(LispValue... args) throws LispRuntimeException {
		LispContext evalContext = new LispContext(context);
		for(int i = 0; i<args.length; ++i)
			evalContext.define(argNames[i], args[i]);
		return expression.eval(evalContext);
	}

}
