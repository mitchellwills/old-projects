package eh.main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import options.OptionListener;
import options.OptionProvider;
import eh.EquationHelper;
import eh.OutputMode;
import eh.options.GeneralOptions;
import eh.util.Util;

public class MainSystemIcon {
	private TrayIcon icon;
	private PopupMenu menu;
	private SystemTray tray;

	public MainSystemIcon() {
		tray = SystemTray.getSystemTray();

		menu = buildMenu();
		icon = new TrayIcon(Util.solidImage(Color.RED, 50, 50),
				"Equation Helper", menu);
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					EquationHelper.getMainWindow().setVisible(
							!EquationHelper.getMainWindow().isVisible());
			}
		});

		try {
			tray.add(icon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MenuItem currentModeItem;
	private MenuItem optionItem;
	private MenuItem exitItem;

	private PopupMenu buildMenu() {
		PopupMenu menu = new PopupMenu();

		menu.add(currentModeItem = new MenuItem(OutputMode.getOption(
				GeneralOptions.OUTPUT_MODE).toString()));
		OptionProvider.addOptionListener(new OptionListener() {
			@Override
			public void optionUpdated(String name, Object newValue) {
				if (name.equals(GeneralOptions.OUTPUT_MODE))
					currentModeItem.setLabel(newValue.toString());
			}
		});
		currentModeItem.setEnabled(false);

		menu.add(optionItem = new MenuItem("Options"));
		optionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EquationHelper.showOptions();
			}
		});

		menu.add(exitItem = new MenuItem("Exit"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EquationHelper.exit();
			}
		});

		return menu;
	}

	public void cleanup() {
		tray.remove(icon);
	}
}
