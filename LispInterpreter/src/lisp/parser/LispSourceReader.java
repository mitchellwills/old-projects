package lisp.parser;

import lisp.parser.ParserAtom.Type;

public class LispSourceReader extends SourceReader {

	public LispSourceReader(String source) {
		super(source);
	}

	public ParserValue readValue() throws LispSyntaxException {
		readWhitespace();
		Position p = getPosition();
		if (isOpenBrace(peek())) {
			ParserExpression expression = new ParserExpression(next(), p);
			while (hasNext()) {
				ParserValue value = readValue();
				if (value instanceof ParserExpression.End) {
					expression.end((ParserExpression.End) value);
					break;
				} else
					expression.add(value);
				if (!hasNext())
					throw new LispSyntaxException(
							"No closing brace at end of document for expression started on "
									+ expression.getStartPosition(),
							getPosition());
			}
			return expression;
		}
		if (isCloseBrace(peek())) {
			ParserExpression.End expressionEnd = new ParserExpression.End(
					next(), p);
			return expressionEnd;
		}
		if (hasNext('"')) {
			next();

			StringBuilder builder = new StringBuilder();
			while (hasNext()) {
				next();
				if (current() == '\\') {
					next();
					if (current() == '"')
						builder.append('"');
					else if (current() == 'n')
						builder.append('\n');
					else if (current() == 't')
						builder.append('\t');
					else if (current() == 'a')
						builder.append((char)7);
					else if (current() == 'b')
						builder.append('\b');
					else if (current() == 'v')
						builder.append((char)11);
					else if (current() == 'f')
						builder.append('\f');
					else if (current() == 'r')
						builder.append('\r');
					else if (current() == 'e')
						builder.append((char)27);
					else if (current() == '\'')
						builder.append('\'');
					else if (current() == '\n')
						builder.append('\n');
					else if (current() == '\\')
						builder.append('\\');
					// TODO other escape characters
					else
						throw new LispSyntaxException(
								"Unknown escape character \\" + current(),
								getPosition());
				} else if (current() == '"') 
					break;
				else
					builder.append(current());
			}
			return new ParserAtom(Type.String, builder.toString(), p);
		}
		if (hasNext('\'')) {
			next();
			return ParserExpression.pair(new ParserAtom("quote", p),
					readValue(), p);
		}
		if (hasNext('`')) {
			next();
			return ParserExpression.pair(new ParserAtom("quasiquote", p),
					readValue(), p);
		}
		if (hasNext(',')) {
			next();
			return ParserExpression.pair(new ParserAtom("backquote", p),
					readValue(), p);
		}
		if (hasNext("#t") || hasNext("#T")) {
			next(2);
			return new ParserAtom(Type.Boolean, "true",
					p);
		}
		if (hasNext("#f") || hasNext("#F")) {
			next(2);
			return new ParserAtom(Type.Boolean, "false",
					p);
		}
		if (hasNext("#;")) {
			readThroughNewline();
			return null;
		}
		// TODO vectors
		// TODO structure
		if (hasNext("#\\")) {
			next(2);
			return new ParserAtom(Type.Character, readUntilDelimiter(), p);
		}
		// TODO byte string
		if (hasNext("#%")) {
			next(2);
			return new ParserAtom(Type.Symbol, readUntilDelimiter(), p);
		}
		if (hasNext("#:")) {
			next(2);
			return new ParserAtom(Type.Keyword, readUntilDelimiter(), p);
		}
		if (hasNext("#&")) {
			next(2);
			return new ParserAtom(Type.Box, readUntilDelimiter(), p);
		}
		if (hasNext("#|")) {
			next(2);
			readUntil("|#");
			next(2);
			return null;
		}
		if (hasNext("#;")) {
			readValue();
			return null;
		}
		// TODO syntax quotes
		if (hasNext("#! ")) {
			readThroughNewline();
			return null;
		}
		if (hasNext("#!")) {
			throw new LispSyntaxException("Reader extensions not supported",
					getPosition());
		}
		if (hasNext("#~")) {
			throw new LispSyntaxException("Compiled code not supported",
					getPosition());
		}
		if (hasNext("#I") || hasNext("#i")) {
			next(2);
			return new ParserAtom(Type.DecimalNumber, readUntilDelimiter(),
					p);
		}
		if (hasNext("#E") || hasNext("#e")) {
			throw new LispSyntaxException("Exact numbers not supported", p);
		}
		if (hasNext("#X") || hasNext("#x")) {
			next(2);
			return new ParserAtom(Type.HexNumber, readUntilDelimiter(), p);
		}
		if (hasNext("#O") || hasNext("#o")) {
			next(2);
			return new ParserAtom(Type.OctalNumber, readUntilDelimiter(), p);
		}
		if (hasNext("#D") || hasNext("#d")) {
			next(2);
			return new ParserAtom(Type.DecimalNumber, readUntilDelimiter(),
					p);
		}
		if (hasNext("#B") || hasNext("#b")) {
			next(2);
			return new ParserAtom(Type.BinaryNumber, readUntilDelimiter(),
					p);
		}
		if (hasNext("#<<")) {
			next(3);
			String terminator = readUntilNewline();
			readThroughNewline();// read newlines
			String value = readUntil(terminator);
			next(terminator.length());
			return new ParserAtom(Type.String, value, p);
		}
		// TODO regular expressions
		// TODO case sensitivity
		// TODO hashtable

		if (hasNext("#reader")) {
			throw new LispSyntaxException("#reader not supported",
					getPosition());
		}
		if (hasNext("#lang")) {
			throw new LispSyntaxException("#lang not supported", getPosition());
		}
		// TODO more vectors

		return new ParserAtom(null, readUntilDelimiter(), p);
	}

	public String readUntilDelimiter() {
		StringBuilder builder = new StringBuilder();
		while (!isDelimiter(peek()))
			builder.append(next());
		return builder.toString();
	}

	public static boolean isDelimiter(char c) {
		return c == '\0' || isWhitespace(c) || isOpenBrace(c)
				|| isCloseBrace(c) || c == '"' || c == ',' || c == '\''
				|| c == '`' || c == ';';
	}

	public static boolean isOpenBrace(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	public static boolean isCloseBrace(char c) {
		return c == ')' || c == ']' || c == '}';
	}

	public static boolean bracesMatch(char startChar, char endChar) {
		if (!isOpenBrace(startChar))
			throw new RuntimeException(startChar + " is not a starting brace");
		return getMatchingBrace(startChar) == endChar;
	}

	public static char getMatchingBrace(char c) {
		switch (c) {
		case '(':
			return ')';
		case ')':
			return '(';

		case '[':
			return ']';
		case ']':
			return '[';

		case '{':
			return '}';
		case '}':
			return '{';
		}
		throw new RuntimeException(c + " is not a brace");
	}
}
