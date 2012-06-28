package lisp.functions;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.IdentifierValue;
import lisp.values.Lambda;
import lisp.values.LispForm;
import lisp.values.LispValue;
import lisp.values.SExpression;

public class LambdaForm extends LispForm{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("lambda", LambdaForm.getInstance());
	}
	
	private static LambdaForm instance;
	public static LambdaForm getInstance(){
		if(instance==null)
			instance = new LambdaForm();
		return instance; 
	}
	private LambdaForm(){}
	

	@Override
	public LispValue execute(LispContext context, LispValue... args) throws LispRuntimeException {
		if(args.length!=2)
			throw new LispRuntimeException("Lambda must have form (lambda (argName ...) expression)");
		if(args[0] instanceof SExpression){
			SExpression argNameSExpression = (SExpression)args[0];
			String[] argNames = new String[argNameSExpression.size()];
			for(int i = 0; i<argNameSExpression.size(); ++i){
				if(argNameSExpression.getElement(i) instanceof IdentifierValue)
					argNames[i] = ((IdentifierValue)argNameSExpression.getElement(i)).getName();
				else
					throw new LispRuntimeException("Lambda argument names must be identifiers");
			}
			return new Lambda(args[1], argNames, context);
		}
		else
			throw new LispRuntimeException("Lambda must have an SExpression as the first parameter for arguments");
	}

}
