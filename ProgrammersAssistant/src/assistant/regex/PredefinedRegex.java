package assistant.regex;

public class PredefinedRegex {
	private final String name;
	private final String matchPattern;
	private final String replacePattern;
	private final boolean isMultiline;
	private final boolean isCaseInsensitive;
	private boolean isDotAll;

	public PredefinedRegex(String name, String matchPattern, String replacePattern){
		this(name, matchPattern, replacePattern, false, false, false);
	}

	public PredefinedRegex(String name, String matchPattern, String replacePattern, boolean isMultiline, boolean isCaseInsensitive, boolean isDotAll){
		this.name = name;
		this.matchPattern = matchPattern;
		this.replacePattern = replacePattern;
		this.isMultiline = isMultiline;
		this.isCaseInsensitive = isCaseInsensitive;
		this.isDotAll = isDotAll;
	}
	
	public boolean isDotAll() {
		return isDotAll;
	}

	public String toString(){
		return getName();
	}

	public String getName() {
		return name;
	}

	public String getMatchPattern() {
		return matchPattern;
	}

	public String getReplacePattern() {
		return replacePattern;
	}

	public boolean isMultiline() {
		return isMultiline;
	}

	public boolean isCaseInsensitive() {
		return isCaseInsensitive;
	}
	
	
	
}
