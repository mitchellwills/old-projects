package eh.parts;

import javax.swing.Icon;

import lisp.runtime.LispContext;
import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import eh.OutputMode;

public class LispPart extends Part {

	private String name;
	private LispContext partContext;
	public LispPart(String name, String keyboardShortcut, LispContext partContext) {
		super(keyboardShortcut);
		this.name = name;
		this.partContext = partContext;
		try {
			partContext.define("this", LispJava.toLispJavaValue(this));
		} catch (LispRuntimeException e) {
		}
	}

	@Override
	public String getName() {
		return name;
	}

	public LispContext getContext() {
		return partContext;
	}

	@Override
	public boolean suppots(OutputMode mode) {
		try {
			return LispJava.callLispFunction(partContext, Boolean.TYPE, "supports?", mode.name());
		} catch (LispRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void execute(OutputMode mode) {
		try {
			LispJava.callLispFunction(partContext, "execute", mode.name());
		} catch (LispRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
