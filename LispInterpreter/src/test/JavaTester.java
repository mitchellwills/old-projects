package test;

import static org.junit.Assert.assertEquals;
import lisp.runtime.LispJava;
import lisp.values.LispValue;

import org.junit.Test;

public class JavaTester {

	@Test
	public void testDoubleToLisp() throws Exception {
		assertEquals(new LispValue.NumberValue(0.0), LispJava.toLisp(0.0));
		assertEquals(new LispValue.NumberValue(1.0), LispJava.toLisp(1.0));
		assertEquals(new LispValue.NumberValue(3.14), LispJava.toLisp(3.14));
		assertEquals(new LispValue.NumberValue(-1.0), LispJava.toLisp(-1.0));
	}

	@Test
	public void testIntegerToLisp() throws Exception {
		assertEquals(new LispValue.NumberValue(0), LispJava.toLisp(0));
		assertEquals(new LispValue.NumberValue(1.0), LispJava.toLisp(1));
		assertEquals(new LispValue.NumberValue(3), LispJava.toLisp(3));
		assertEquals(new LispValue.NumberValue(-1), LispJava.toLisp(-1));
		assertEquals(new LispValue.NumberValue(-2000), LispJava.toLisp(-2000));
	}

	@Test
	public void testBooleanToLisp() throws Exception {
		assertEquals(new LispValue.BooleanValue(true), LispJava.toLisp(true));
		assertEquals(new LispValue.BooleanValue(false), LispJava.toLisp(false));
	}

	@Test
	public void testCharacterToLisp() throws Exception {
		assertEquals(new LispValue.CharacterValue('a'), LispJava.toLisp('a'));
		assertEquals(new LispValue.CharacterValue('Z'), LispJava.toLisp('Z'));
		assertEquals(new LispValue.CharacterValue('~'), LispJava.toLisp('~'));
		assertEquals(new LispValue.CharacterValue('!'), LispJava.toLisp('!'));
	}

	@Test
	public void testStringToLisp() throws Exception {
		assertEquals(new LispValue.StringValue("Hello"), LispJava.toLisp("Hello"));
		assertEquals(new LispValue.StringValue("Goodbye"), LispJava.toLisp("Goodbye"));
		assertEquals(new LispValue.StringValue(""), LispJava.toLisp(""));
	}
	
	
	
	


	@Test
	public void testLispToDouble() throws Exception {
		assertEquals(LispJava.toDouble(new LispValue.NumberValue(0.0)), 0.0, 0);
		assertEquals(LispJava.toDouble(new LispValue.NumberValue(1.0)), 1.0, 0);
		assertEquals(LispJava.toDouble(new LispValue.NumberValue(3.14)), 3.14, 0);
		assertEquals(LispJava.toDouble(new LispValue.NumberValue(-1.0)), -1.0, 0);
	}

	@Test
	public void testLispToInteger() throws Exception {
		assertEquals(LispJava.toInteger(new LispValue.NumberValue(0)), 0);
		assertEquals(LispJava.toInteger(new LispValue.NumberValue(1.0)), 1);
		assertEquals(LispJava.toInteger(new LispValue.NumberValue(3)), 3);
		assertEquals(LispJava.toInteger(new LispValue.NumberValue(-1)), -1);
		assertEquals(LispJava.toInteger(new LispValue.NumberValue(-2000)), -2000);
	}

	@Test
	public void testLispToBoolean() throws Exception {
		assertEquals(LispJava.toBoolean(new LispValue.BooleanValue(true)), true);
		assertEquals(LispJava.toBoolean(new LispValue.BooleanValue(false)), false);
	}

	@Test
	public void testLispToCharacter() throws Exception {
		assertEquals(LispJava.toCharacter(new LispValue.CharacterValue('a')), 'a');
		assertEquals(LispJava.toCharacter(new LispValue.CharacterValue('Z')), 'Z');
		assertEquals(LispJava.toCharacter(new LispValue.CharacterValue('~')), '~');
		assertEquals(LispJava.toCharacter(new LispValue.CharacterValue('!')), '!');
	}

	@Test
	public void testLispToString() throws Exception {
		assertEquals(LispJava.toString(new LispValue.StringValue("Hello")), "Hello");
		assertEquals(LispJava.toString(new LispValue.StringValue("Goodbye")), "Goodbye");
		assertEquals(LispJava.toString(new LispValue.StringValue("")), "");
	}
}
