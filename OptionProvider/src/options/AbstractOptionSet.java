package options;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import options.field.ValueField;
import options.gui.OptionEditComponent;

public abstract class AbstractOptionSet implements OptionSet {

	private Component editComponent = null;

	public Component getEditComponent() {
		if (editComponent == null)
			editComponent = buildEditComponent();
		return editComponent;
	}

	private Map<String, OptionEditComponent<?>> editComponents = new HashMap<String, OptionEditComponent<?>>();

	@Override
	public void save() {
		for (OptionEditComponent<?> component : editComponents.values())
			component.save();
	}

	@Override
	public void load() {
		for (OptionEditComponent<?> component : editComponents.values())
			component.load();
	}

	private LinkedHashMap<ValueField<?>, String> valueFields = null;

	public LinkedHashMap<ValueField<?>, String> getValueFields() {
		if (valueFields == null)
			valueFields = buildValueFields();
		return valueFields;
	}

	// returns map of value fields to label
	protected abstract LinkedHashMap<ValueField<?>, String> buildValueFields();

	private Component buildEditComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3, 6, 0, 6);
		int row = 0;

		for (ValueField<?> field : getValueFields().keySet()) {
			c.gridy = row++;
			c.gridx = 0;
			c.weightx = 0.0;
			panel.add(new JLabel(getValueFields().get(field)), c);

			c.gridx = 1;
			c.weightx = 1.0;
			OptionEditComponent<?> component = field.getEditComponent();
			if (component != null) {
				component.load();
				panel.add(component.getComponent(), c);
			}
			editComponents.put(field.getName(), component);
		}

		c.gridy = row;
		c.weighty = 1.0;
		panel.add(Box.createGlue(), c);// force component to top

		return panel;
	}

}
