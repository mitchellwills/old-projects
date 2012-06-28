package options;

import java.awt.Component;

public interface OptionSet {
	public String getName();
	public Component getEditComponent();
	
	public void save();
	public void load();
}
