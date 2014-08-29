package net.ui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import net.*;

public class InterfacesPanel extends JComponent{
	public InterfacesPanel(){
		setLayout(new GridBagLayout());
	}
	public void updateInterfaces(List<NetworkInterface> interfaces){
		removeAll();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 10;
		c.ipady = 5;
		int y = 0;
		for(NetworkInterface i:interfaces){
			c.gridy = y++;
			JLabel label = new JLabel(i.getInterfaceName()+": "+i.getIPAddress());
			label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
			add(label, c);
		}
	}
}
