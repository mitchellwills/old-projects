package eh.parts;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.input.keyboard.Keyboard;
import eh.OutputMode;

public class UnicodeMapPart extends Part {
	public static final Font CHARACTER_FONT = new Font(Font.MONOSPACED,
			Font.PLAIN, 16);

	private char[] unicodeValues;
	private int width;
	private String label;

	public UnicodeMapPart(String label, String keyboardShortcut, int width, char... unicodeValues) {
		super(keyboardShortcut);
		this.label = label;
		this.width = width;
		this.unicodeValues = unicodeValues;
	}

	@Override
	public boolean suppots(OutputMode mode) {
		return true;
	}

	@Override
	public void execute(OutputMode mode) {
		new UnicodeMapDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return label;
	}

	private class UnicodeMapDialog extends PartPopupDialog {

		public UnicodeMapDialog(OutputMode mode) {
			super(label, mode, false);
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(new GridLayout(
					unicodeValues.length % width == 0 ? unicodeValues.length
							/ width : unicodeValues.length / width + 1, width));

			for (int i = 0; i < unicodeValues.length; ++i) {
				final char c = unicodeValues[i];
				JButton button;
				contentPanel.add(button = new JButton(Character.toString(c)));
				button.setFont(CHARACTER_FONT);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Keyboard.sendUnicodeEvent((char) c);
					}
				});
			}

			return contentPanel;
		}

		@Override
		protected void insert(OutputMode mode) {
		}
	}

}
