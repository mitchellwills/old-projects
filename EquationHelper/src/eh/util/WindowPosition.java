package eh.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import options.OptionProvider;

public enum WindowPosition {
	BOTTOM_RIGHT("Bottom Right") {
		@Override
		public void setPosition(Window window) {
			Rectangle monitorBounds = getScreenBounds();
			window.setLocation(monitorBounds.x+monitorBounds.width-window.getBounds().width, monitorBounds.y+monitorBounds.height-window.getBounds().height);
		}
	},
	TOP_RIGHT("Top Right") {
		@Override
		public void setPosition(Window window) {
			Rectangle monitorBounds = getScreenBounds();
			window.setLocation(monitorBounds.x+monitorBounds.width-window.getBounds().width, monitorBounds.y);
		}
	},
	BOTTOM_LEFT("Bottom Left") {
		@Override
		public void setPosition(Window window) {
			Rectangle monitorBounds = getScreenBounds();
			window.setLocation(monitorBounds.x, monitorBounds.y+monitorBounds.height-window.getBounds().height);
		}
	},
	TOP_LEFT("Top Left") {
		@Override
		public void setPosition(Window window) {
			Rectangle monitorBounds = getScreenBounds();
			window.setLocation(monitorBounds.x, monitorBounds.y);
		}
	},
	FLOATING("Floating") {
		@Override
		public void setPosition(Window window) {
			//do nothing
		}
	};

	private String displayName;
	private WindowPosition(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public String toString(){
		return getDisplayName();
	}
	
	public abstract void setPosition(Window window);
	
	private static String[] positionNames = new String[WindowPosition.values().length];
	static{
		for(int i = 0; i<WindowPosition.values().length; ++i)
			positionNames[i] = WindowPosition.values()[i].getDisplayName();
	}
	public static String[] positionNames() {
		return positionNames;
	}
	
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice[] gs = ge.getScreenDevices();
	private static GraphicsConfiguration gc = gs[0].getDefaultConfiguration();
	private static Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
	private static Rectangle screenBounds = gc.getBounds();
	private static Rectangle useableScreen = new Rectangle(screenBounds.x+screenInsets.left, screenBounds.y+screenInsets.top, screenBounds.width-screenInsets.left-screenInsets.right, screenBounds.height-screenInsets.top-screenInsets.bottom);
	public static Rectangle getScreenBounds(){
		return useableScreen;
	}
	
	public static void center(Window window){
		window.setLocation(useableScreen.width/2-window.getWidth()/2, useableScreen.height/2-window.getHeight()/2);
	}
	
	public static WindowPosition getOption(String name) {
		Object o = OptionProvider.getField(name).get();
		if(o instanceof WindowPosition)
			return (WindowPosition) o;
		return WindowPosition.FLOATING;
	}

}
