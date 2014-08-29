package net.ui;

import java.awt.event.*;

public class MouseClickActionAdaper implements MouseListener {

	private final ActionListener actionListener;

	public MouseClickActionAdaper(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		actionListener.actionPerformed(new ActionEvent(e.getSource(), 0, "click"));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
