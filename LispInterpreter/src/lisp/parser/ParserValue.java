package lisp.parser;

import lisp.macors.LispMacro;
import lisp.values.LispValue;

public interface ParserValue {
	public Position getPosition();
	public LispValue toValue() throws LispSyntaxException;
	public ParserValue applyMacro(LispMacro macro) throws LispSyntaxException;
}
