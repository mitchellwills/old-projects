package options.gui;

import java.awt.Component;

import javax.swing.JComboBox;

import options.field.EnumField;

public class EnumEditComponent<T extends Enum<T>> extends OptionEditComponent<T> {
	private JComboBox fieldEditor;

	public EnumEditComponent(EnumField<T> field) {
		super(field);
		fieldEditor = new JComboBox(
				(Enum<?>[]) field.getType().getEnumConstants());
	}

	@Override
	public Component getComponent() {
		return fieldEditor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save() {
		((EnumField<T>)getField()).setRaw((T)fieldEditor.getSelectedItem());
	}

	@Override
	public void load() {
		try {
			fieldEditor.setSelectedItem(getField().get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}