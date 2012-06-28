package eh.parts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.input.keyboard.KeySequenceBuilder;
import eh.OutputMode;

public class UnderOverBracePart extends Part {

	@Override
	public boolean suppots(OutputMode mode) {
		return mode == OutputMode.OneNote;
	}

	@Override
	public void execute(OutputMode mode) {
		if (mode == OutputMode.OneNote)
			new UnderOverBraceDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Under, Over Brace";
	}

	private class UnderOverBraceDialog extends PartPopupDialog {
		private JTextField labelField;
		private JTextField contentField;
		private JCheckBox belowField;

		public UnderOverBraceDialog(OutputMode mode) {
			super("Under Over Brace", mode);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			if (mode == OutputMode.OneNote) {
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;

				addRow(contentPanel, c, "Content:",
						contentField = new JTextField(""));
				addRow(contentPanel, c, "Label:",
						labelField = new JTextField(""));
				addRow(contentPanel, c, "Below:",
						belowField = new JCheckBox());
				belowField.setSelected(true);
				return contentPanel;
			}
			return null;
		}

		@Override
		protected void insert(OutputMode mode) {
			if (mode == OutputMode.OneNote) {
				boolean below = belowField.isSelected();
				KeySequenceBuilder builder = new KeySequenceBuilder();
				if(below)
					builder = builder.type("\\underbrace");
				else
					builder = builder.type("\\overbrace");
				builder = builder.type("(").type(contentField.getText()).type(")");
				if(below)
					builder = builder.type("\\below");
				else
					builder = builder.type("\\above");
				builder = builder.type("(").type(labelField.getText()).type(") ");
						

				builder.build().execute();
			}
		}
	}

}
