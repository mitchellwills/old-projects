package eh.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import options.OptionListener;
import options.OptionProvider;
import eh.OutputMode;
import eh.options.GeneralOptions;
import eh.parts.Part;
import eh.parts.PartManager;
import eh.util.WindowPosition;

public class MainWindow extends JFrame implements OptionListener,
		ComponentListener {
	public MainWindow() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		setFocusableWindowState(false);

		setJMenuBar(new MainMenuBar());

		OptionProvider.addOptionListener(this);
		addComponentListener(this);

		add(partPanel = new JPanel());
		partPanel.setLayout(new BoxLayout(partPanel, BoxLayout.Y_AXIS));

		updateTitleBar();
		updateSize();
		updatePosition();
		updatePartList();
	}

	@Override
	public void optionUpdated(String name, Object newValue) {
		if (name.equals(GeneralOptions.MAIN_WINDOW_POSITION)
				|| name.equals(GeneralOptions.MAIN_WINDOW_TITLE_BAR)
				|| name.equals(GeneralOptions.MAIN_WINDOW_WIDTH)
				|| name.equals(GeneralOptions.MAIN_WINDOW_HEIGHT)
				|| name.equals(GeneralOptions.OUTPUT_MODE)) {
			updateTitleBar();
			updateSize();
			updatePosition();
			updatePartList();
		}
	}

	private static final Border undectoratedBorder = BorderFactory
			.createEtchedBorder(EtchedBorder.RAISED);
	private static final Border windowedBorder = BorderFactory
			.createEmptyBorder();

	private void updateTitleBar() {
		boolean undecorated = !OptionProvider
				.getBoolean(GeneralOptions.MAIN_WINDOW_TITLE_BAR);
		if (undecorated)
			getRootPane().setBorder(undectoratedBorder);
		else
			getRootPane().setBorder(windowedBorder);
		if (undecorated != isUndecorated()) {
			if (isDisplayable()) {
				boolean visible = isVisible();
				dispose();
				setUndecorated(undecorated);
				setVisible(visible);
			} else
				setUndecorated(undecorated);
		}
	}

	private void updatePosition() {
		WindowPosition.getOption(GeneralOptions.MAIN_WINDOW_POSITION)
				.setPosition(this);
	}

	private void updateSize() {
		setSize(OptionProvider.getInt(GeneralOptions.MAIN_WINDOW_WIDTH),
				OptionProvider.getInt(GeneralOptions.MAIN_WINDOW_HEIGHT));
	}

	private JPanel partPanel;

	private void updatePartList() {
		partPanel.removeAll();
		Set<Part> parts = PartManager.getParts();
		OutputMode mode = OutputMode.getOption(GeneralOptions.OUTPUT_MODE);
		for (Part part : parts) {
			if (part.suppots(mode)) {
				JButton button = new JButton(part.getName(), part.getIcon());
				partPanel.add(button);
				button.addActionListener(new PartActionListener(part));
			}
		}
		partPanel.validate();
		repaint();
	}

	private class PartActionListener implements ActionListener {
		private Part part;

		public PartActionListener(Part part) {
			this.part = part;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			part.execute(OutputMode.getOption(GeneralOptions.OUTPUT_MODE));
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		updatePosition();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// updatePosition();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		updatePosition();
		updateSize();
	}
}
