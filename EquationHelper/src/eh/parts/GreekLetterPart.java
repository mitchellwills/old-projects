package eh.parts;

import java.awt.Component;
import java.util.EnumSet;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import util.input.keyboard.GreekLetter;
import util.input.keyboard.KeyMod;
import util.input.keyboard.Keyboard;
import util.input.keyboard.WinVK;
import eh.OutputMode;

public class GreekLetterPart extends Part {

	public GreekLetterPart() {
		super(EnumSet.of(KeyMod.NOREPEAT, KeyMod.Ctrl, KeyMod.Alt, KeyMod.Shift), WinVK.G_KEY);
	}
	
	@Override
	public boolean suppots(OutputMode mode) {
		return mode == OutputMode.Text;
	}

	@Override
	public void execute(OutputMode mode) {
		new GreekLetterDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		return null;
	}

	@Override
	public String getName() {
		return "Greek Letter";
	}

	private class GreekLetterDialog extends PartPopupDialog {

		private JCheckBox capitalCheckBox;
		private JComboBox letterBox;

		public GreekLetterDialog(OutputMode mode) {
			super("Greek Letters", mode);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			Box contentBox = Box.createVerticalBox();
			contentBox.add(letterBox = new JComboBox(GreekLetter.values()));
			contentBox.add(capitalCheckBox = new JCheckBox("Capital", true));
			return contentBox;
		}

		@Override
		protected void insert(OutputMode mode) {
			GreekLetter letter = (GreekLetter) letterBox.getSelectedItem();

			if (capitalCheckBox.isSelected())
				Keyboard.sendUnicodeEvent(letter.getUpperCase());
			else
				Keyboard.sendUnicodeEvent(letter.getLowerCase());
		}

	}

}
