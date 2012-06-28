package options.field;

import options.OptionProvider;
import options.gui.EnumEditComponent;
import options.gui.OptionEditComponent;

public class EnumField<T extends Enum<T>> extends DefaultValueField<T> {
	private T value;
	public EnumField(String name, T initial, Class<T> clazz) {
		super(name, clazz);
		value = initial;
	}

	@Override
	public String getString() {
		if(value==null)
			return null;
		return value.name();
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void set(String value) {
		setRaw(Enum.valueOf(getType(), value));
	}
	
	@Override
	public void setRaw(T value) {
		this.value = value;
		OptionProvider.fireOptionEvent(getName(), value);
	}

	@Override
	protected OptionEditComponent<T> buildEditComponent() {
		return new EnumEditComponent<T>(this);
	}

}
