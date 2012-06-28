package eh.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import options.OptionListener;
import options.OptionProvider;
import eh.EquationHelper;
import eh.OutputMode;
import eh.options.GeneralOptions;

public class MainMenuBar extends JMenuBar implements OptionListener {
	public MainMenuBar() {
		JMenu fileMenu = new JMenu("File");
		add(fileMenu);

		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquationHelper.showOptions();
			}
		});
		fileMenu.add(mntmOptions);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquationHelper.exit();
			}
		});
		fileMenu.add(mntmExit);

		JMenu helpMenu = new JMenu("Help");
		add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainMenuBar.this, "Equation Helper\nBy Mitchell Wills", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);
		
		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainMenuBar.this,
						"Equation Helper Help\n" +
								"Greek Letters: Ctrl+Alt+Shift+G\n" +
								"Sigma: Ctrl+Alt+Shift+S\n" +
								"Unicode: Ctrl+Alt+Shift+U\n" +
								"Derivative: Ctrl+Alt+Shift+D\n" +
								"Integral: Ctrl+Alt+Shift+I\n" +
								"Limit: Ctrl+Alt+Shift+L\n" +
								"Radical: Ctrl+Alt+Shift+Q\n" +
								"Matrix: Ctrl+Alt+Shift+M",
						"Help", JOptionPane.PLAIN_MESSAGE);
			}
		});
		helpMenu.add(helpMenuItem);
		

		add(Box.createGlue());
		add(outputModeLabel = new JLabel(OutputMode.getOption(GeneralOptions.OUTPUT_MODE).toString()));
		outputModeLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2)
						EquationHelper.showOptions();//TODO make this toggle instead
			}
		});
		OptionProvider.addOptionListener(this);
	}
	private JLabel outputModeLabel = null;
	@Override
	public void optionUpdated(String name, Object newValue) {
		if(name.equals(GeneralOptions.OUTPUT_MODE))
			outputModeLabel.setText(OutputMode.getOption(GeneralOptions.OUTPUT_MODE).toString());
	}
}
