package test;

import lisp.runtime.LispContext;
import lisp.runtime.LispGlobalContext;
import lisp.runtime.LispJava;

import org.junit.Assert;
import org.junit.Test;

public class BooleanTester {
	private LispContext context = new LispContext(LispGlobalContext.getInstance());

	@Test
	public void testOR() throws Exception{
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("or", false, true).eval(context));
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("or", true, true).eval(context));
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("or", true, false).eval(context));
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("or", false, false).eval(context));
	}

	@Test
	public void testAND() throws Exception{
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("and", true, false).eval(context));
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("and", true, true).eval(context));
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("and", false, true).eval(context));
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("and", false, false).eval(context));
	}

	@Test
	public void testNOT() throws Exception{
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("not", true).eval(context));
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("not", false).eval(context));
	}

}
