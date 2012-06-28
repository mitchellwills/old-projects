package lisp;

import java.util.List;
import java.util.Scanner;

import lisp.parser.LispParser;
import lisp.parser.LispSyntaxException;
import lisp.runtime.LispContext;
import lisp.runtime.LispGlobalContext;
import lisp.runtime.LispRuntimeException;
import lisp.values.LispValue;
import lisp.values.LispVoid;

public class LispInteractionWindow {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		LispContext interactionsContext = new LispContext(LispGlobalContext.getInstance());
		while (true) {
			try {
				System.out.print("> ");
				String command = s.nextLine();
				LispParser parser = new LispParser(command);
				List<LispValue> expressions = parser.getRootValues();
				for(LispValue e:expressions){
					try {
						LispValue result = e.eval(interactionsContext);
						if(result!=LispVoid.getInstance())
							System.out.println(result);
					} catch (LispRuntimeException e1) {
						try {
							Thread.sleep(20);//so the output buffer is emptied first
						} catch (InterruptedException e2) {
						}
						System.err.println("Error executing expression: "+e1.getMessage());
						//e1.printStackTrace();
					}
					try {
						Thread.sleep(20);
					} catch (InterruptedException e2) {
					}
				}
			} catch (LispSyntaxException e1) {
				System.err.println("Syntax Error : "+e1.getMessage());
			}
		}
	}
}
