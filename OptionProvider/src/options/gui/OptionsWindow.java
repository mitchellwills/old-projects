package options.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import options.OptionProvider;
import options.OptionSet;

public class OptionsWindow extends JDialog {

	private JTabbedPane setTabs;

	private JButton saveButton;
	private JButton cancelButton;

	public OptionsWindow() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				OptionProvider.revert();
			}
		});
		setTitle("Options");
		setModal(true);
		
		setAlwaysOnTop(true);

		getContentPane().add(setTabs = new JTabbedPane());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton = new JButton("Save"));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionProvider.save();
				setVisible(false);
			}

		});
		getRootPane().setDefaultButton(saveButton);
		
		buttonPanel.add(cancelButton = new JButton("Cancel"));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionProvider.revert();//TODO revert when window becomes visible
				setVisible(false);
			}

		});
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		pack();
	}

	public void optionSetAdded(OptionSet set) {
		setTabs.addTab(set.getName(), set.getEditComponent());
		pack();
	}
}
