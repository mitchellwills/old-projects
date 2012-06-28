package options.gui;

import java.awt.Component;

import javax.swing.JTextField;

import options.field.StringField;

public class StringEditComponent extends OptionEditComponent<String> {
	private JTextField fieldEditor;

	public StringEditComponent(StringField field) {
		super(field);
		fieldEditor = new JTextField();
	}

	@Override
	public Component getComponent() {
		return fieldEditor;
	}

	@Override
	public void save() {
		getField().set(fieldEditor.getText());
	}

	@Override
	public void load() {
		fieldEditor.setText(getField().getString());
	}
}