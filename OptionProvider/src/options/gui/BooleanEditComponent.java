package options.gui;

import java.awt.Component;

import javax.swing.JCheckBox;

import options.field.BooleanField;

public class BooleanEditComponent extends OptionEditComponent<Boolean> {
	private JCheckBox fieldEditor;

	public BooleanEditComponent(BooleanField field) {
		super(field);
		fieldEditor = new JCheckBox();
	}

	@Override
	public Component getComponent() {
		return fieldEditor;
	}

	@Override
	public void save() {
		getField().set(fieldEditor.isSelected());
	}

	@Override
	public void load() {
		fieldEditor.setSelected(getField().getBoolean());
	}
}