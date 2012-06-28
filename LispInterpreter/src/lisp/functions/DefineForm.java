package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.IdentifierValue;
import lisp.values.LispForm;
import lisp.values.LispValue;
import lisp.values.LispVoid;

public class DefineForm extends LispForm{
	
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("define", DefineForm.getInstance());
	}
	
	
	private static DefineForm instance;
	public static DefineForm getInstance(){
		if(instance==null)
			instance = new DefineForm();
		return instance; 
	}
	private DefineForm(){}
	

	@Override
	public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
		if(args.length!=2)
			throw new LispRuntimeException("Define takes two argument");
		if(!(args[0] instanceof IdentifierValue))
			throw new LispRuntimeException(args[0]+" is not an identifier");
		context.define(((IdentifierValue)args[0]).getName(), args[1].eval(context));
		return LispVoid.getInstance();
	}

}
