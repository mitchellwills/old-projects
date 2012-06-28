package lisp.macors;

import lisp.parser.LispSyntaxException;
import lisp.parser.ParserExpression;
import lisp.parser.ParserAtom;
import lisp.parser.ParserValue;

public interface LispMacro {

	ParserValue processExpression(ParserExpression expression, ParserValue[] values)
			throws LispSyntaxException;

	ParserValue processAtom(ParserAtom atom)
			throws LispSyntaxException;
}
