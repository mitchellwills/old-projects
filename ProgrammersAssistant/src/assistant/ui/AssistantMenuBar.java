package assistant.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import assistant.ProgrammersAssistant;

public class AssistantMenuBar extends JMenuBar {
	public AssistantMenuBar() {
		JMenu fileMenu = new JMenu("File");
		add(fileMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgrammersAssistant.exit();
			}
		});
		fileMenu.add(mntmExit);

		JMenu helpMenu = new JMenu("Help");
		add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(AssistantMenuBar.this, "Programmer's Assistant\nBy Mitchell Wills", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);
	}
}
