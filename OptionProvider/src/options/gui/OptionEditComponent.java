package options.gui;

import java.awt.Component;

import options.field.ValueField;

public abstract class OptionEditComponent<T> {

	private final ValueField<T> field;

	protected OptionEditComponent(ValueField<T> field) {
		this.field = field;
	}

	public ValueField<T> getField() {
		return field;
	}

	public abstract Component getComponent();

	public abstract void save();

	public abstract void load();
}
