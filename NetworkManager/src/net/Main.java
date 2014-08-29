package net;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.util.List;

import net.ui.*;


public class Main {

	/**
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		final NetworkingWindow window = new NetworkingWindow();
		
		
		
		SystemTray tray = SystemTray.getSystemTray();
		final TrayIcon trayIcon = new TrayIcon(getImage(Color.RED), "Network Manager");

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(!window.isVisible());
			}
		};

		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new MouseClickActionAdaper(actionListener));

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
		}



		window.setVisible(true);
	}

	private static Image getImage(Color c) {
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 100, 100);
		return image;
	}

}
