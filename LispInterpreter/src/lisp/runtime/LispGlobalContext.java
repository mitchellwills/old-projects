package lisp.runtime;

import lisp.functions.BooleanFunction;
import lisp.functions.ComparisonFunctions;
import lisp.functions.ControlFlowForm;
import lisp.functions.DefineForm;
import lisp.functions.LambdaForm;
import lisp.functions.MathFunction;
import lisp.functions.SetForm;
import lisp.functions.StringFunction;
import lisp.functions.SystemFunction;

public class LispGlobalContext extends LispContext {

	private static LispGlobalContext context;
	public static LispGlobalContext getInstance() {
		if(context==null)
			context = new LispGlobalContext();
		return context;
	}
	
	private LispGlobalContext(){
		super(null);
		try {
			DefineForm.defineIn(this);
			MathFunction.defineIn(this);
			SetForm.defineIn(this);
			SystemFunction.defineIn(this);
			BooleanFunction.defineIn(this);
			ControlFlowForm.defineIn(this);
			ComparisonFunctions.defineIn(this);
			LambdaForm.defineIn(this);
			StringFunction.defineIn(this);
			
		} catch (LispRuntimeException e) {
			System.err.println("Error initializing global context");
			e.printStackTrace();
		}
	}

}
