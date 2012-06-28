package eh.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eh.OutputMode;
import eh.util.WindowPosition;

public abstract class PartPopupDialog extends JDialog {
	private JButton insertButton;
	private JButton cancelButton;

	public PartPopupDialog(String title, OutputMode mode) {
		this(title, mode, true);
	}
	public PartPopupDialog(String title, final OutputMode mode, boolean useInset) {
		setTitle(title);
		setAlwaysOnTop(true);
		setModal(true);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));

		add(buildContentPane(mode));

		JPanel buttonPanel = new JPanel();
		if (useInset) {
			buttonPanel.add(insertButton = new JButton("Inset"));
			getRootPane().setDefaultButton(insertButton);
			insertButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					insert(mode);
				}
			});
		}
		else
			setFocusableWindowState(false);
		buttonPanel.add(cancelButton = new JButton(useInset?"Cancel":"Close"));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setResizable(false);
		WindowPosition.center(this);
	}

	protected void addRow(Container container, GridBagConstraints c,
			String label, Component comp) {
		c.gridx = 0;
		c.gridy++;
		c.weightx = 0.0;
		container.add(new JLabel(label), c);
		c.weightx = 1.0;
		c.gridx = 1;
		container.add(comp, c);
	}

	protected abstract Component buildContentPane(OutputMode mode);

	protected abstract void insert(OutputMode mode);
}
