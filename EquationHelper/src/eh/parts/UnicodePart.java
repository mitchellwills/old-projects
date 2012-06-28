package eh.parts;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumSet;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import util.input.keyboard.KeyMod;
import util.input.keyboard.Keyboard;
import util.input.keyboard.WinVK;
import eh.OutputMode;

public class UnicodePart extends Part {

	public UnicodePart() {
		super(EnumSet
				.of(KeyMod.NOREPEAT, KeyMod.Ctrl, KeyMod.Alt, KeyMod.Shift),
				WinVK.U_KEY);
	}

	@Override
	public boolean suppots(OutputMode mode) {
		return true;
	}

	@Override
	public void execute(OutputMode mode) {
		new UnicodeDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		return null;
	}

	@Override
	public String getName() {
		return "Unicode Character";
	}

	private class UnicodeDialog extends PartPopupDialog {

		private JTextField characterCodeField;
		private JLabel previewLabel;

		public UnicodeDialog(OutputMode mode) {
			super("Unicode", mode);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			Box contentBox = Box.createVerticalBox();
			contentBox.add(new JLabel("Hex Character Code:"));
			contentBox.add(characterCodeField = new JTextField(5));
			characterCodeField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					updatePreview();
				}
			});
			contentBox.add(previewLabel = new JLabel());
			updatePreview();
			return contentBox;
		}

		@Override
		protected void insert(OutputMode mode) {
			try {
				int code = Integer.parseInt(characterCodeField.getText(), 16);
				Keyboard.sendUnicodeEvent((char) code);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this,
						"'" + characterCodeField.getText()
								+ "' is not a valid hex number",
						"Invalid Character Code", JOptionPane.ERROR_MESSAGE);
			}
		}

		public void updatePreview() {
			if (characterCodeField.getText().isEmpty())
				previewLabel.setText("Enter a hex value");
			else
				try {
					int code = Integer.parseInt(characterCodeField.getText(),
							16);
					previewLabel.setText("Preview: "
							+ Character.toString((char) code));
				} catch (NumberFormatException e) {
					previewLabel.setText("Not a valid hex number");
				}
		}

	}

}
