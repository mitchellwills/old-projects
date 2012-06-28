package lisp.parser;

public class Position {
	private int line;
	private int col;
	public Position(int line, int col) {
		this.line = line;
		this.col = col;
	}
	public int getLine(){
		return line;
	}
	public int getCol(){
		return col;
	}
	@Override
	public String toString(){
		return "Line: "+getLine()+", Col: "+getCol();
	}
}
