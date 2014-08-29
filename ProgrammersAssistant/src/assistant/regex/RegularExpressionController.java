package assistant.regex;

import java.util.regex.*;

import assistant.*;

public class RegularExpressionController implements Updateable{
	private final RegularExpressionInterface controls;

	public RegularExpressionController(RegularExpressionInterface controls) {
		this.controls = controls;
	}

	public void update(){
		Pattern pattern;
		try{
			int patternFlags = 0;
			if(controls.multilinePattern())
				patternFlags |= Pattern.MULTILINE;
			if(controls.caseInsensitivePattern())
				patternFlags |= Pattern.CASE_INSENSITIVE;
			if(controls.dotAllPattern())
				patternFlags |= Pattern.DOTALL;
			pattern = Pattern.compile(controls.getMatchPattern(), patternFlags);
		} catch(PatternSyntaxException e){
			controls.setError("Invalid match pattern");
			return;
		}

		Matcher matcher = pattern.matcher(controls.getInput());
		StringBuffer output = new StringBuffer();
		while(matcher.find()){
			try{
				matcher.appendReplacement(output, controls.getReplacePattern());
			} catch(IllegalArgumentException e){
				controls.setError("Invalid replace pattern: "+e.getMessage());
				return;
			} catch(StringIndexOutOfBoundsException e){
				controls.setError("Invalid replace pattern syntax");
				return;
			} catch(IndexOutOfBoundsException e){
				controls.setError("Invalid replace pattern: "+e.getMessage());
				return;
			}
		}
		matcher.appendTail(output);

		controls.setOutput(output.toString());
		controls.setError("");
	}
}
