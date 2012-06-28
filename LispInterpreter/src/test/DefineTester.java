package test;

import static org.junit.Assert.fail;
import lisp.runtime.LispContext;
import lisp.runtime.LispGlobalContext;
import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import lisp.values.IdentifierValue;

import org.junit.Assert;
import org.junit.Test;

public class DefineTester {
	private LispContext context = new LispContext(LispGlobalContext.getInstance());

	@Test
	public void testDefine() throws Exception{
		LispJava.callLispFunction(context, "define", new IdentifierValue("hello"), 10);

		Assert.assertEquals(LispJava.toLisp(10), new IdentifierValue("hello").eval(context));
		Assert.assertEquals(LispJava.toLisp(30), LispJava.functionCallFromJavaArgs("+", new IdentifierValue("hello"), 20).eval(context));
	}

	@Test
	public void testSetException() throws Exception{
		try{
			LispJava.callLispFunction(context, "set!", new IdentifierValue("hi"), 10);
			fail();
		} catch(LispRuntimeException e){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testSet() throws Exception{
		LispJava.callLispFunction(context, "define", new IdentifierValue("h"), 10);
		LispJava.callLispFunction(context, "set!", new IdentifierValue("h"), 1);

		Assert.assertEquals(LispJava.toLisp(1), new IdentifierValue("h").eval(context));
		Assert.assertEquals(LispJava.toLisp(21), LispJava.functionCallFromJavaArgs("+", new IdentifierValue("h"), 20).eval(context));
	}

}
