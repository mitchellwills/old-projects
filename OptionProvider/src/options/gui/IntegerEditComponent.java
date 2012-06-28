package options.gui;

import java.awt.Component;

import javax.swing.JFormattedTextField;

import options.field.IntegerField;

public class IntegerEditComponent extends OptionEditComponent<Integer> {
	private JFormattedTextField fieldEditor;

	public IntegerEditComponent(IntegerField field) {
		super(field);
		fieldEditor = new JFormattedTextField(
				java.text.NumberFormat.getIntegerInstance());
	}

	@Override
	public Component getComponent() {
		return fieldEditor;
	}

	@Override
	public void save() {
		Object fieldValue = fieldEditor.getValue();
		if (fieldValue != null)
			getField().set(((Number) fieldValue).intValue());
		else
			getField().set(0);
	}

	@Override
	public void load() {
		fieldEditor.setValue(getField().getInt());
	}
}