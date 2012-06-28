package test;

import lisp.runtime.LispContext;
import lisp.runtime.LispGlobalContext;
import lisp.runtime.LispJava;

import org.junit.Assert;
import org.junit.Test;

public class StringTester {
	private LispContext context = new LispContext(LispGlobalContext.getInstance());

	@Test
	public void testEmpty() throws Exception{
		Assert.assertEquals(LispJava.toLisp(true), LispJava.functionCallFromJavaArgs("empty?", "").eval(context));
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("empty?", "hello").eval(context));
		Assert.assertEquals(LispJava.toLisp(false), LispJava.functionCallFromJavaArgs("empty?", " ").eval(context));
	}

}
