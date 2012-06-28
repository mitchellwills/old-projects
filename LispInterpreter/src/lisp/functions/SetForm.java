package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.IdentifierValue;
import lisp.values.LispForm;
import lisp.values.LispValue;
import lisp.values.LispVoid;

public class SetForm extends LispForm{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("set!", SetForm.getInstance());
	}
	
	private static SetForm instance;
	public static SetForm getInstance(){
		if(instance==null)
			instance = new SetForm();
		return instance; 
	}
	private SetForm(){}
	

	@Override
	public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
		if(!(args[0] instanceof IdentifierValue))
			throw new LispRuntimeException(args[0]+" is not an identifier");
		context.set(((IdentifierValue)args[0]).getName(), args[1].eval(context));
		return LispVoid.getInstance();
	}

}
