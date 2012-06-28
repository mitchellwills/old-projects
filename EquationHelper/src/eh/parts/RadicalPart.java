package eh.parts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.EnumSet;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.input.keyboard.KeyMod;
import util.input.keyboard.KeySequenceBuilder;
import util.input.keyboard.WinVK;
import eh.OutputMode;

public class RadicalPart extends Part {

	public RadicalPart() {
		super(EnumSet
				.of(KeyMod.NOREPEAT, KeyMod.Ctrl, KeyMod.Alt, KeyMod.Shift),
				WinVK.Q_KEY);
	}

	@Override
	public boolean suppots(OutputMode mode) {
		return mode == OutputMode.OneNote || mode == OutputMode.Text;
	}

	@Override
	public void execute(OutputMode mode) {
		if (mode == OutputMode.Text)
			new TextRadicalDialog(mode).setVisible(true);
		else if (mode == OutputMode.OneNote)
			new RadicalDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return Character.toString((char) 0x221A);
	}

	private class RadicalDialog extends PartPopupDialog {
		private JTextField degreeField;
		private JTextField contentField;

		public RadicalDialog(OutputMode mode) {
			super("Radical", mode);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			if (mode == OutputMode.OneNote) {
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;

				addRow(contentPanel, c, "Degree:",
						degreeField = new JTextField("1"));
				addRow(contentPanel, c, "Content:",
						contentField = new JTextField(""));
				return contentPanel;
			}
			return null;
		}

		@Override
		protected void insert(OutputMode mode) {
			if (mode == OutputMode.OneNote) {
				KeySequenceBuilder builder = new KeySequenceBuilder()
						.type("\\sqrt(");
				if (!degreeField.getText().isEmpty()
						&& !degreeField.getText().equals("1"))
					builder = builder.type(degreeField.getText()).type("&");

				builder.type(contentField.getText()).type(") ").build()
						.execute();
			}
		}
	}

	private class TextRadicalDialog extends PartPopupDialog {
		private JComboBox radicalTypeField;

		public TextRadicalDialog(OutputMode mode) {
			super("Radical", mode);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			if (mode == OutputMode.Text) {
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;

				addRow(contentPanel, c, "Type:",
						radicalTypeField = new JComboBox(new String[] {
								"\u221A", "\u221B", "\u221C" }));
				return contentPanel;
			}
			return null;
		}

		@Override
		protected void insert(OutputMode mode) {
			if (mode == OutputMode.Text) {
				new KeySequenceBuilder()
						.type((String) radicalTypeField.getSelectedItem())
						.build().execute();
			}
		}
	}

}
