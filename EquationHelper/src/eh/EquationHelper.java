package eh;

import javax.swing.UIManager;

import options.OptionProvider;
import eh.main.MainSystemIcon;
import eh.main.MainWindow;
import eh.options.GeneralOptions;
import eh.parts.GreekLetterPart;
import eh.parts.MatrixPart;
import eh.parts.PartLoader;
import eh.parts.PartManager;
import eh.parts.RadicalPart;
import eh.parts.UnderOverBracePart;
import eh.parts.UnicodePart;
import eh.util.WindowPosition;

public class EquationHelper {
	private static MainWindow mainWindow;
	private static MainSystemIcon icon;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Error Setting Look and Feel");
		}
		PartManager.register(new GreekLetterPart());
		PartManager.register(new UnicodePart());
		PartManager.register(new RadicalPart());
		PartManager.register(new MatrixPart());
		PartManager.register(new UnderOverBracePart());
		PartLoader.load();
		PartManager.printSupport();

		OptionProvider.registerSet(new GeneralOptions());
		OptionProvider.init("settings.ini");


		
		icon = new MainSystemIcon();
		mainWindow = new MainWindow();
	}

	public static MainWindow getMainWindow() {
		return mainWindow;
	}

	public static void showOptions() {
		WindowPosition.center(OptionProvider.getOptionsWindow());
		OptionProvider.getOptionsWindow().setVisible(true);
	}

	public static void exit() {
		icon.cleanup();
		System.exit(0);
	}

}
