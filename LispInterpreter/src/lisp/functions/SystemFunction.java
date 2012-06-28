package lisp.functions;

import java.io.PrintStream;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispFunction;
import lisp.values.LispValue;

public abstract class SystemFunction extends LispFunction{
	public static void defineIn(LispContext context) throws LispRuntimeException {
		context.define("exit", SystemFunction.EXIT);
		try {
			context.define("syso", new JavaFunction(System.out, PrintStream.class, "println", new Class<?>[]{String.class}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			context.define("syse", new JavaFunction(System.err, PrintStream.class, "println", new Class<?>[]{String.class}));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final SystemFunction EXIT = new SystemFunction(){
		@Override
		public LispValue execute(LispValue... args) throws LispRuntimeException {
			if(args.length==0)
				System.exit(0);
			if(args.length==1)
				if(args[0] instanceof NumberValue)
					System.exit((int)((NumberValue)args[0]).getValue());
			throw new LispRuntimeException("exit takes none or a single integer parameter");
		};
	};
}
