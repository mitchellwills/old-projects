package lisp.parser;

import java.util.ArrayList;
import java.util.List;

import lisp.macors.LispMacro;
import lisp.values.LispValue;
import lisp.values.SExpression;

public class ParserExpression implements ParserValue{
	public static class End implements ParserValue{
		private char endChar;
		private Position endPosition;
		public End(char endChar, Position endPosition){
			this.endChar = endChar;
			this.endPosition = endPosition;
		}
		@Override
		public Position getPosition() {
			return endPosition;
		}
		@Override
		public LispValue toValue() throws LispSyntaxException {
			return null;
		}
		@Override
		public ParserValue applyMacro(LispMacro macro) {
			return null;
		}
	}

	private final char startChar;
	private final Position startPosition;
	private char endChar;
	private Position endPosition;
	private List<ParserValue> values = new ArrayList<ParserValue>();

	public ParserExpression(char startChar, Position startPosition) throws LispSyntaxException {
		if(!LispSourceReader.isOpenBrace(startChar))
			throw new LispSyntaxException("Tried starting expression with "+startChar, startPosition);
		
		this.startChar = startChar;
		this.startPosition = startPosition;
	}

	public ParserExpression(char startChar) throws LispSyntaxException {
		if(!LispSourceReader.isOpenBrace(startChar))
			throw new LispSyntaxException("Tried starting expression with "+startChar, null);
		
		this.startChar = startChar;
		this.startPosition = null;
		this.endChar = LispSourceReader.getMatchingBrace(startChar);
		this.endPosition = null;
	}

	public char getStartChar() {
		return startChar;
	}

	public Position getStartPosition() {
		return startPosition;
	}

	public char getEndChar() {
		return endChar;
	}

	public Position getEndPosition() {
		return endPosition;
	}
	
	@Override
	public Position getPosition(){
		return getStartPosition();
	}

	public void end(End end) throws LispSyntaxException {
		this.endPosition = end.endPosition;
		this.endChar = end.endChar;
		if(!LispSourceReader.bracesMatch(startChar, endChar))
			throw new LispSyntaxException("Expression starts on "+getStartPosition()+" with "+startChar+" and ends with "+endChar, endPosition);
	}

	public void add(ParserValue value) {
		if(value!=null)
			values.add(value);
	}

	public ParserValue getElement(int i) {
		return values.get(i);
	}
	public int size(){
		return values.size();
	}

	public void print(String prefix) {
		System.out.println(getStartPosition()+": "+prefix+getStartChar());
		for(int i = 0; i<size(); ++i){
			ParserValue v = getElement(i);
			if(v instanceof ParserExpression){
				((ParserExpression) v).print(prefix+"\t");
			}
			else
				System.out.println(v.getPosition()+": "+prefix+"  "+v);
		}
		System.out.println(getEndPosition()+": "+prefix+getEndChar());
	}
	

	public static ParserExpression pair(ParserValue value1, ParserValue value2, Position startPosition) throws LispSyntaxException{
		ParserExpression expression = new ParserExpression('(', startPosition);
		expression.end(new End(')', null));
		expression.add(value1);
		expression.add(value2);
		return expression;
	}

	@Override
	public LispValue toValue() throws LispSyntaxException {
		LispValue[] lispValues = new LispValue[values.size()];
		for(int i = 0; i<values.size(); ++i)
			lispValues[i] = values.get(i).toValue();
		return new SExpression(lispValues);
	}

	@Override
	public ParserValue applyMacro(LispMacro macro) throws LispSyntaxException {
		if(macro==null)
			return null;
		
		boolean change = false;
		for (int i = 0; i<values.size(); ++i){
			ParserValue newValue = values.get(i).applyMacro(macro);
			if(newValue!=null){
				values.set(i, newValue);
				change = true;
			}
		}
		ParserValue newVal =  macro.processExpression(this, values.toArray(new ParserValue[values.size()]));
		if(newVal!=null)
			return newVal;
		if(change)
			return this;
		return null;
	}
}
