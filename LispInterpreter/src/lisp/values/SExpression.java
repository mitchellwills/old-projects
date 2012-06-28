package lisp.values;

import java.util.Arrays;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public class SExpression implements LispValue {
	private final LispValue[] values;
	public SExpression(LispValue... values){
		this.values = Arrays.copyOf(values, values.length);
	}

	public int size() {
		return values.length;
	}

	public LispValue getElement(int i) {
		return values[i];
	}

	@Override
	public LispValue eval(LispContext context) throws LispRuntimeException {
		LispValue first = getElement(0).eval(context);

		if (first instanceof LispFunction) {
			LispFunction function = (LispFunction) first;

			LispValue[] args = new LispValue[size() - 1];
			for (int i = 0; i < args.length; ++i)
				args[i] = getElement(i+1).eval(context);

			return function.execute(args);
		}
		if (first instanceof LispForm) {
			LispForm form = (LispForm) first;

			LispValue[] args = new LispValue[size() - 1];
				for (int i = 0; i < args.length; ++i)
					args[i] = getElement(i+1);

			return form.execute(context, args);
		}
		throw new LispRuntimeException(
				"SExpression does not have a function as its first value");
	}

}
