package eh;

import options.OptionProvider;

public enum OutputMode {
	OneNote("One Note"),
	Text("Text"),
	GoogleDocs("Google Docs"),
	WeBWorK("WeBWorK"),
	LaTeX("LaTeX");
	
	private String displayName;
	private OutputMode(String displayName){
		this.displayName = displayName;
	}

	public String toString() {
		return displayName;
	}

	public static OutputMode getOption(String name) {
		Object o = OptionProvider.getField(name).get();
		if(o instanceof OutputMode)
			return (OutputMode) o;
		return OutputMode.Text;
	}
}
