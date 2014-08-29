package net.ui;

import java.awt.*;

import javax.swing.*;

import net.*;

public class NetworkingWindow extends JWindow{
	private PresetsPanel presetsPanel;
	private InterfacesPanel interfacesPanel;

	public NetworkingWindow(){
		setAlwaysOnTop(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(interfacesPanel = new InterfacesPanel());
		add(presetsPanel = new PresetsPanel(this));
	}

	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(visible){
			updateInfo();
			relayout();
		}
	}
	
	public void relayout(){
		pack();
		
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		
		setLocation(screenDim.width-screenInsets.right-getWidth(), screenDim.height-screenInsets.bottom-getHeight());
	}

	public void updateInfo() {
		interfacesPanel.updateInterfaces(Networking.getInterfaces());
	}
}
