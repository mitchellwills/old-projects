package net.ui;

import java.awt.event.*;

import javax.swing.*;

import net.*;

public class PresetsPanel extends JComponent{
	public PresetsPanel(final NetworkingWindow window){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton resetButton = new JButton("Reset Interfaces");
		resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Networking.getInterface("Wireless Network Connection").setDhcp();
				Networking.getInterface("Local Area Connection").setDhcp();
				Firewall.enable();
				window.updateInfo();
			}
		});
		add(resetButton);
		
		JButton wiredFRCButton = new JButton("Wired FRC");
		wiredFRCButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Networking.getInterface("Local Area Connection").setIp("10.1.90.6", "255.0.0.0", "");
				Firewall.disable();
				window.updateInfo();
			}
		});
		add(wiredFRCButton);
		
		JButton wirelessFRCButton = new JButton("Wireless FRC");
		wirelessFRCButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Networking.getInterface("Wireless Network Connection").setIp("10.1.90.6", "255.0.0.0", "");
				Firewall.disable();
				window.updateInfo();
			}
		});
		add(wirelessFRCButton);
	}
}
