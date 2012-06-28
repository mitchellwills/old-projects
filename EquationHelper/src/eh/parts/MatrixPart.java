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

public class MatrixPart extends Part {

	public MatrixPart() {
		super(EnumSet
				.of(KeyMod.NOREPEAT, KeyMod.Ctrl, KeyMod.Alt, KeyMod.Shift),
				WinVK.M_KEY);
	}

	@Override
	public boolean suppots(OutputMode mode) {
		return mode == OutputMode.OneNote;
	}

	@Override
	public void execute(OutputMode mode) {
		if (mode == OutputMode.OneNote)
			new MatrixDialog(mode).setVisible(true);
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Matrix";
	}

	private class MatrixDialog extends PartPopupDialog {
		private JTextField rowsField;
		private JTextField colsField;
		private JTextField fillField;
		private JComboBox outlineField;

		public MatrixDialog(OutputMode mode) {
			super("Matrix", mode);
		}

		private class OutlineType {
			public OutlineType(String outline) {
				this(outline, outline);
			}

			private String start;
			private String end;

			public OutlineType(String start, String end) {
				this.start = start;
				this.end = end;
			}

			public String getStart() {
				return start;
			}

			public String getEnd() {
				return end;
			}

			public String toString() {
				return getStart() + " " + getEnd();
			}
		}

		@Override
		protected Component buildContentPane(OutputMode mode) {
			if (mode == OutputMode.OneNote) {
				JPanel contentPanel = new JPanel();
				contentPanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;

				addRow(contentPanel, c, "Rows:",
						rowsField = new JTextField("2"));
				addRow(contentPanel, c, "Cols:",
						colsField = new JTextField("2"));
				addRow(contentPanel, c, "Fill:", fillField = new JTextField(""));
				addRow(contentPanel, c, "Outline:",
						outlineField = new JComboBox(
								new OutlineType[] { new OutlineType(""),
										new OutlineType("[", "]"),
										new OutlineType("|"),
										new OutlineType("(", ")") }));

				return contentPanel;
			}
			return null;
		}

		@Override
		protected void insert(OutputMode mode) {
			int rows = Integer.parseInt(rowsField.getText());
			int cols = Integer.parseInt(colsField.getText());
			OutlineType outline = (OutlineType) outlineField.getSelectedItem();

			if (mode == OutputMode.OneNote) {
				KeySequenceBuilder builder = new KeySequenceBuilder();
				builder = builder.type(outline.getStart());
				builder = builder.type("\\matrix(");
				for (int r = 0; r < rows; ++r) {
					for (int c = 0; c < cols; ++c) {
						builder = builder.type(fillField.getText());
						if (c < cols - 1)
							builder = builder.type("&");
					}
					if (r < rows - 1)
						builder = builder.type("@");
				}

				builder = builder.type(")");
				builder = builder.type(outline.getEnd());
				builder = builder.type(" ");
				builder.build().execute();
			}
		}
	}

}
