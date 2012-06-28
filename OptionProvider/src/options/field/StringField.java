package options.field;

import options.OptionProvider;
import options.gui.OptionEditComponent;
import options.gui.StringEditComponent;

public class StringField extends DefaultValueField<String> {
	private String value;
	public StringField(String name, String initial) {
		super(name, String.class);
		value = initial;
	}

	@Override
	public String getString() {
		return value;
	}

	@Override
	public String get() {
		return value;
	}

	@Override
	public void set(String value) {
		this.value = value;
		OptionProvider.fireOptionEvent(getName(), value);
	}

	@Override
	protected OptionEditComponent<String> buildEditComponent() {
		return new StringEditComponent(this);
	}

}
