package eh.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lisp.runtime.LispJava;
import lisp.runtime.LispRuntimeException;
import eh.OutputMode;
import eh.util.WindowPosition;


public class LispPartDialog extends JDialog {
	private JPanel contentPanel = new JPanel();
	private GridBagConstraints c = new GridBagConstraints();

	private JButton insertButton;
	private JButton cancelButton;
	
	private LispPart part;

	public LispPartDialog(LispPart part, String title, OutputMode mode) {
		this(part, title, mode, true);
	}
	public LispPartDialog(LispPart part, String title, final OutputMode mode, boolean useInset) {
		this.part = part;
		setTitle(title);
		setAlwaysOnTop(true);
		setModal(true);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));

		contentPanel.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		add(contentPanel);

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

	protected void addRow(String label, Component comp) {
		c.gridx = 0;
		c.gridy++;
		c.weightx = 0.0;
		contentPanel.add(new JLabel(label), c);
		c.weightx = 1.0;
		c.gridx = 1;
		contentPanel.add(comp, c);
		pack();
	}
	
	private Map<String, Object> fields = new HashMap<String, Object>();
	public void addTextField(String label, String name, String defaultValue){
		JTextField field = new JTextField(defaultValue);
		addRow(label, field);
		fields.put(name, field);
	}

	protected void insert(OutputMode mode){
		try {
			LispJava.callLispFunction(part.getContext(), "insert", this, mode.name());
		} catch (LispRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}