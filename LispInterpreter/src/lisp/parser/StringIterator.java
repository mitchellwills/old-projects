package lisp.parser;

import java.util.NoSuchElementException;

/**
 * @author Mitchell Wills
 */
public class StringIterator {
	private String string;
	private int pos = 0;// position in the string when next is called


	/**
	 * @param string
	 *            String to be iterated through
	 */
	public StringIterator(String string) {
		this.string = string;
	}
	
	/**
	 * @return the current position
	 */
	public int getPosition() {
		return pos - 1;
	}

	/**
	 * @return the string being iterated through
	 */
	public String getString() {
		return string;
	}

	/**
	 * @return Returns true if the iteration has more elements.
	 */
	public boolean hasNext() {
		return pos < string.length();
	}

	private int currentLine = 1;
	public int getLine(){
		return currentLine;
	}
	
	/**
	 * @return Returns the next character in the string.
	 */
	public char next() {
		if (hasNext()){
			if(string.charAt(pos)=='\n')
				++currentLine;
			return string.charAt(pos++);
		}
		throw new NoSuchElementException(
				"This StringIterator has reached the end of the String");
	}

	/**
	 * @param distance
	 *            distance to advance
	 * @return character after advanced distance
	 */
	public char next(int distance) {
		if (pos + distance <= string.length()) {
			pos += distance;
			//TODO implement current line
			return string.charAt(pos - 1);
		}
		throw new NoSuchElementException(
				"The distance requested to advance exceeds the end of the String");
	}

	/**
	 * retrieves the previous character without going backwards
	 * 
	 * @return the previous character
	 */
	public char getPrevious() {
		if (pos - 1 > 0)
			return string.charAt(pos - 2);
		throw new NoSuchElementException(
				"This StringIterator has reached the end of the String");
	}

	/**
	 * @return the current character
	 */
	public char getCurrent() {
		return string.charAt(pos - 1);
	}

	/**
	 * @return the next character without iterating
	 */
	public char peek() {
		if (pos >= string.length())
			throw new NoSuchElementException(
					"This StringIterator has reached the end of the String");
		return string.charAt(pos);
	}

	/**
	 * set the position back to the beginning
	 */
	public void reset() {
		currentLine = 1;
		pos = 0;
	}

	/**
	 * @param s
	 *            String to be checked for
	 * @return true if the iterator's next characters are the given string
	 */
	public boolean startsWith(String s) {
		for (int a = 0; a < s.length(); ++a) {
			if (string.charAt(pos + a) != s.charAt(a)) {
				return false;
			}
		}
		return true;
	}
}