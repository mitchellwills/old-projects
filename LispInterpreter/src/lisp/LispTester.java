package lisp;

import java.util.ArrayList;
import java.util.List;

import lisp.functions.JavaFunction;
import lisp.parser.LispParser;
import lisp.runtime.LispContext;
import lisp.runtime.LispJava;
import lisp.values.LispValue;
import lisp.values.SExpression;

public class LispTester {


	public static void print(SExpression e, String prefix) {
		System.out.println(prefix+"(");
		for(int i = 0; i<e.size(); ++i){
			if(e.getElement(i) instanceof SExpression){
				print((SExpression) e.getElement(i), prefix+"\t");
			}
			else
				System.out.println(prefix+"  "+e.getElement(i));
		}
		System.out.println(prefix+")");
	}
	
	public static void main(String[] args){
		List<String> list = new ArrayList<String>(){{
			add("hello");
		}};

		try{
			LispContext context = new LispContext();
			LispParser parser = new LispParser("(define x (lambda (a b c) (a b c)))");
			List<LispValue> expressions = parser.getRootValues();
			for(LispValue e:expressions)
				e.eval(context);
			JavaFunction javaFunction = new JavaFunction(null, LispTester.class, "testCallback", new Class<?>[]{Object.class, Integer.TYPE});
			LispJava.callLispFunction(context, "x", javaFunction, list, 20);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void testCallback(Object b, int c){
		System.out.println(b + " - " + c);
	}

}
