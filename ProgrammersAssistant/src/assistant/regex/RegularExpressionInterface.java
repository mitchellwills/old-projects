package assistant.regex;

public interface RegularExpressionInterface {

	boolean multilinePattern();

	boolean caseInsensitivePattern();

	boolean dotAllPattern();

	String getMatchPattern();

	void setError(String string);

	CharSequence getInput();

	void setOutput(String string);

	String getReplacePattern();

}
