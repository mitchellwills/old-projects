package eh.options;

import options.SimpleOptionSet;
import eh.OutputMode;
import eh.util.WindowPosition;

public class GeneralOptions extends SimpleOptionSet{
	@OptionField(label = "Main Window Width", type = Integer.class)
	public static final String MAIN_WINDOW_WIDTH = "Main Window Width";
	
	@OptionField(label = "Main Window Height", type = Integer.class)
	public static final String MAIN_WINDOW_HEIGHT = "Main Window Height";
	
	@OptionField(label = "Main Window Position", type = WindowPosition.class)
	public static final String MAIN_WINDOW_POSITION = "Main Window Position";
	
	@OptionField(label = "Show Main Window Title Bar", type = Boolean.class)
	public static final String MAIN_WINDOW_TITLE_BAR = "Main Window Title Bar";
	
	@OptionField(label = "Output Mode", type = OutputMode.class)
	public static final String OUTPUT_MODE = "Output Mode";

	
	@Override
	public String getName() {
		return "General";
	}
}
