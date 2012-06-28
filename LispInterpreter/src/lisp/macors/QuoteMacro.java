package lisp.macors;

import lisp.parser.LispSyntaxException;
import lisp.parser.ParserAtom;
import lisp.parser.ParserExpression;
import lisp.parser.ParserExpression.End;
import lisp.parser.ParserValue;

public class QuoteMacro implements LispMacro{
	private static QuoteMacro instance;
	public static QuoteMacro getInstance(){
		if(instance==null)
			instance = new QuoteMacro();
		return instance; 
	}
	private QuoteMacro(){}
	
	@Override
	public ParserValue processExpression(ParserExpression expression, ParserValue[] values)
			throws LispSyntaxException {//TODO handle backquote and quasiquote
		if(values.length!=2)
			return null;
		if(values[0] instanceof ParserAtom && ((ParserAtom)values[0]).getContent().equals("quote")){
			if(values[1] instanceof ParserAtom){
				ParserAtom atom = (ParserAtom)values[1];
				//TODO handle numbers properly
				return new ParserAtom(ParserAtom.Type.Symbol, atom.getContent(), atom.getPosition());
			}
			else if(values[1] instanceof ParserExpression){
				ParserExpression expr = (ParserExpression)values[1];
				ParserExpression newExpr = new ParserExpression(expr.getStartChar(), expr.getStartPosition());
				
				for(int i = 0; i<expr.size(); ++i){
					ParserExpression quoteExpression = new ParserExpression('(', null);
					quoteExpression.add(new ParserAtom("quote", null));
					quoteExpression.add(expr.getElement(i));
					quoteExpression.end(new End(')', null));
					newExpr.add(quoteExpression);
				}
				
				newExpr.end(new End(expr.getEndChar(), expr.getEndPosition()));
				return newExpr;
			}
		}
		return null;
	}
	@Override
	public ParserValue processAtom(ParserAtom atom)
			throws LispSyntaxException {
		return null;
	}
}
