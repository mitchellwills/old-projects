package lisp.parser;

import lisp.macors.LispMacro;
import lisp.values.IdentifierValue;
import lisp.values.LispValue;
import lisp.values.LispValue.BooleanValue;
import lisp.values.LispValue.BoxValue;
import lisp.values.LispValue.CharacterValue;
import lisp.values.LispValue.KeywordValue;
import lisp.values.LispValue.NumberValue;
import lisp.values.LispValue.StringValue;
import lisp.values.LispValue.SymbolValue;

public class ParserAtom implements ParserValue{
	private Type type;
	private String content;
	private Position position;

	public ParserAtom(Type type, String content, Position position) {
		this.type = type;
		this.content = content;
		this.position = position;
	}

	public ParserAtom(String content, Position position) {
		this(null, content, position);
	}

	public String getContent() {
		return content;
	}

	@Override
	public Position getPosition() {
		return position;
	}
	
	public Type getType(){
		return type;
	}
	
	public String toString(){
		return type+":"+content;
	}
	@Override
	public LispValue toValue() throws LispSyntaxException {
		if(type!=null){
			try{
				return type.toValue(content);
			} catch(LispSyntaxException e){
				throw new LispSyntaxException(e.getMessage(), getPosition());
			}
		}
		else{
			try{
				return new NumberValue(Double.parseDouble(content));
			} catch(NumberFormatException e){
				return new IdentifierValue(content);
			}
		}
	}
	
	public enum Type{
		Boolean {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new BooleanValue(java.lang.Boolean.parseBoolean(content));
			}
		}, Character {
			@Override
			public LispValue toValue(java.lang.String content) throws LispSyntaxException {
				char c;
				if(content.equals("nul") || content.equals("null"))
					c = '\0';
				else if(content.equals("backspace"))
					c = (char)8;
				else if(content.equals("tab"))
					c = '\t';
				if(content.equals("newline") || content.equals("linefeed"))
					c = '\n';
				else if(content.equals("return"))
					c = '\r';
				else if(content.equals("vtab"))
					c = (char)11;
				else if(content.equals("page"))
					c = (char)12;
				else if(content.equals("space"))
					c = ' ';
				else if(content.equals("rubout"))
					c = (char)127;
				else if(content.matches("[0-7]+"))
					c = (char)Integer.parseInt(content, 8);
				else if(content.matches("[uU][0-9a-f]+"))
					c = (char)Integer.parseInt(content, 16);
				else if(content.length()==1)
					c = content.charAt(0);
				else
					throw new LispSyntaxException("Bad character expression", null);
				
				return new CharacterValue(c);
			}
		}, Symbol {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new SymbolValue(content);
			}
		}, Keyword {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new KeywordValue(content);
			}
		}, Box {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new BoxValue(content);
			}
		}, DecimalNumber {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new NumberValue(Double.parseDouble(content));
			}
		}, HexNumber {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new NumberValue(Integer.parseInt(content, 16));
			}
		}, OctalNumber {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new NumberValue(Integer.parseInt(content, 8));
			}
		}, BinaryNumber {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new NumberValue(Integer.parseInt(content, 2));
			}
		}, String {
			@Override
			public LispValue toValue(java.lang.String content) {
				return new StringValue(content);
			}
		};
		
		public abstract LispValue toValue(java.lang.String content) throws LispSyntaxException;
	}

	@Override
	public ParserValue applyMacro(LispMacro macro) throws LispSyntaxException {
		if(macro!=null)
			return macro.processAtom(this);
		return null;
	}
	
	
}
