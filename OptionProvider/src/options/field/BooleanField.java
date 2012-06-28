package options.field;

import options.OptionProvider;
import options.gui.BooleanEditComponent;
import options.gui.OptionEditComponent;

public class BooleanField extends DefaultValueField<Boolean> {
	private boolean value;

	public BooleanField(String name, Boolean initial) {
		super(name, Boolean.class);
		if (initial == null)
			value = false;
		else
			value = initial;
	}

	@Override
	public boolean getBoolean() {
		return value;
	}

	@Override
	public String getString() {
		return Boolean.toString(value);
	}

	@Override
	public Boolean get() {
		return value;
	}

	@Override
	public void set(boolean value) {
		this.value = value;
		OptionProvider.fireOptionEvent(getName(), value);
	}

	@Override
	public void set(String value) {
		set(Boolean.parseBoolean(value));
	}

	@Override
	protected OptionEditComponent<Boolean> buildEditComponent() {
		return new BooleanEditComponent(this);
	}

}
