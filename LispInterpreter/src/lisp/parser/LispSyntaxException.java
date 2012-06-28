package lisp.parser;


public class LispSyntaxException extends Exception {
	private Position position;
	public LispSyntaxException(String message, Position position) {
		super(message);
		this.position = position;
	}
	public LispSyntaxException(String message, int line, int col) {
		this(message, new Position(line, col));
	}
	
	@Override
	public String getMessage(){
		return super.getMessage()+"> "+position;
	}

}
