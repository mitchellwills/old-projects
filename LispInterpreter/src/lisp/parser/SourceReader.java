package lisp.parser;

public class SourceReader {
	private String source;
	private int pos = 0;// next position to be read
	private int sourceLine = 1;
	private int sourceCol = 0;

	public SourceReader(String source) {
		this.source = source;
	}

	public boolean hasNext() {
		return pos < source.length();
	}

	public char next() {
		if (!hasNext())
			return '\0';
		++sourceCol;
		if(peek()=='\n'){
			++sourceLine;
			sourceCol = 0;
		}
		return source.charAt(pos++);
	}
	
	public void next(int distance){
		for(int i = 0; i<distance; ++i)
			next();
	}

	public char peek() {
		if (!hasNext())
			return '\0';
		return source.charAt(pos);
	}

	public char peek(int distance) {
		if (pos+distance>=source.length())
			return '\0';
		return source.charAt(pos+distance-1);
	}

	public char current() {
		if (pos < 1)
			return '\0';
		return source.charAt(pos - 1);
	}

	public char previous() {
		if (pos < 2)
			return '\0';
		return source.charAt(pos - 2);
	}
	
	public boolean hasNext(String expression){
		for(int i = 0; i<expression.length(); ++i)
			if(expression.charAt(i)!=peek(i+1))
				return false;
		return true;
	}
	public boolean hasNext(char c){
		return peek()==c;
	}
	
	public int getSourceLine(){
		return sourceLine;
	}
	
	public int getSourceCol(){
		return sourceCol;
	}
	public Position getPosition(){
		return new Position(sourceLine, sourceCol);
	}
	
	public void readWhitespace(){
		while (isWhitespace(peek()) && hasNext())
			next();// read all whitespace
	}
	
	//does not read expression
	public String readUntil(String expression){
		StringBuilder builder = new StringBuilder();
		while(!hasNext(expression))
			builder.append(next());
		return builder.toString();
	}

	public String readUntilNewline() {
		StringBuilder builder = new StringBuilder();
		while(peek()!='\n' && peek()!='\r'&&hasNext())
			builder.append(next());
		return builder.toString();
	}
	public void readThroughNewline() {
		readUntilNewline();
		while(peek()=='\n' || peek()=='\r')
			next();
	}

	public static boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}
}
