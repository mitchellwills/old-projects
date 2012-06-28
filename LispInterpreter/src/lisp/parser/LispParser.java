package lisp.parser;

import java.util.ArrayList;
import java.util.List;

import lisp.macors.LispMacro;
import lisp.macors.QuoteMacro;
import lisp.values.LispValue;

public class LispParser {
	private LispSourceReader reader;
	private List<ParserValue> rootValues = new ArrayList<ParserValue>();
	private List<LispMacro> macros = new ArrayList<LispMacro>();
	{
		macros.add(QuoteMacro.getInstance());
	}
	private List<LispValue> lispValues = new ArrayList<LispValue>();

	public LispParser(String programSource) throws LispSyntaxException {
		reader = new LispSourceReader(programSource);

		while (reader.hasNext()) {
			ParserValue value = reader.readValue();
			if(value!=null)
				rootValues.add(value);
		}

		/*for (ParserValue v : rootValues) {
			if (v instanceof ParserExpression)
				((ParserExpression) v).print("");
			else
				System.out.println(v.getPosition() + ": " + v);
		}*/
		
		int e = 3;
		boolean change = false;
		do{
			change = false;
			for(LispMacro macro:macros){
				for (int i = 0; i<rootValues.size(); ++i){
					ParserValue newValue = rootValues.get(i).applyMacro(macro);
					if(newValue!=null){
						rootValues.set(i, newValue);
						change = true;
					}
				}
			}
		} while(change && e>0);

		/*System.out.println("After Macros:");
		for (ParserValue v : rootValues) {
			if (v instanceof ParserExpression)
				((ParserExpression) v).print("");
			else
				System.out.println(v.getPosition() + ": " + v);
		}*/

		for (ParserValue v : rootValues) {
			LispValue value = v.toValue();
			if (value != null)
				lispValues.add(value);
		}

	}

	public List<LispValue> getRootValues() {
		return lispValues;
	}
}
