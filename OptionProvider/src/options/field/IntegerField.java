package options.field;

import options.OptionProvider;
import options.gui.IntegerEditComponent;
import options.gui.OptionEditComponent;

public class IntegerField extends DefaultValueField<Integer> {
	private int value;

	public IntegerField(String name, Integer initial) {
		super(name, Integer.class);
		if (initial == null)
			value = 0;
		else
			value = initial;
	}

	@Override
	public int getInt() {
		return value;
	}

	@Override
	public Integer get() {
		return value;
	}

	@Override
	public double getDouble() {
		return value;
	}

	@Override
	public String getString() {
		return Integer.toString(value);
	}

	@Override
	public void set(int value) {
		this.value = value;
		OptionProvider.fireOptionEvent(getName(), value);
	}

	@Override
	public void set(String value) {
		set(Integer.parseInt(value));
	}

	@Override
	protected OptionEditComponent<Integer> buildEditComponent() {
		return new IntegerEditComponent(this);
	}

}
