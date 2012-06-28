package lisp.runtime;

public class LispRuntimeException extends Exception {

	public LispRuntimeException(String message) {
		super(message);
	}
	public LispRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
