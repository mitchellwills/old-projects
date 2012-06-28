package test;

import lisp.runtime.LispContext;
import lisp.runtime.LispGlobalContext;
import lisp.runtime.LispJava;

import org.junit.Assert;
import org.junit.Test;

public class MathTester {
	private LispContext context = new LispContext(LispGlobalContext.getInstance());

	@Test
	public void testAddition() throws Exception{
		Assert.assertEquals(LispJava.toLisp(12), LispJava.functionCallFromJavaArgs("+", 10, 2).eval(context));
		Assert.assertEquals(LispJava.toLisp(2), LispJava.functionCallFromJavaArgs("+", -1, 3).eval(context));
		Assert.assertEquals(LispJava.toLisp(6), LispJava.functionCallFromJavaArgs("+", -1, 3, 4).eval(context));
		Assert.assertEquals(LispJava.toLisp(7), LispJava.functionCallFromJavaArgs("+", 7).eval(context));
	}

	@Test
	public void testSubtraction() throws Exception{
		Assert.assertEquals(LispJava.toLisp(8), LispJava.functionCallFromJavaArgs("-", 10, 2).eval(context));
		Assert.assertEquals(LispJava.toLisp(-4), LispJava.functionCallFromJavaArgs("-", -1, 3).eval(context));
		Assert.assertEquals(LispJava.toLisp(-8), LispJava.functionCallFromJavaArgs("-", -1, 3, 4).eval(context));
		Assert.assertEquals(LispJava.toLisp(7), LispJava.functionCallFromJavaArgs("-", 7).eval(context));
	}

	@Test
	public void testMultiplication() throws Exception{
		Assert.assertEquals(LispJava.toLisp(20), LispJava.functionCallFromJavaArgs("*", 10, 2).eval(context));
		Assert.assertEquals(LispJava.toLisp(-3), LispJava.functionCallFromJavaArgs("*", -1, 3).eval(context));
		Assert.assertEquals(LispJava.toLisp(-12), LispJava.functionCallFromJavaArgs("*", -1, 3, 4).eval(context));
		Assert.assertEquals(LispJava.toLisp(7), LispJava.functionCallFromJavaArgs("*", 7).eval(context));
	}

	@Test
	public void testDivision() throws Exception{
		Assert.assertEquals(LispJava.toLisp(5), LispJava.functionCallFromJavaArgs("/", 10, 2).eval(context));
		Assert.assertEquals(LispJava.toLisp(-1.0/3), LispJava.functionCallFromJavaArgs("/", -1, 3).eval(context));
		Assert.assertEquals(LispJava.toLisp(-1.0/12), LispJava.functionCallFromJavaArgs("/", -1, 3, 4).eval(context));
		Assert.assertEquals(LispJava.toLisp(7), LispJava.functionCallFromJavaArgs("/", 7).eval(context));
	}

}
