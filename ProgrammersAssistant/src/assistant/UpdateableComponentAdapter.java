package assistant;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class UpdateableComponentAdapter implements ComponentListener {

	private final Updateable updateable;

	public UpdateableComponentAdapter(Updateable updateable){
		this.updateable = updateable;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		updateable.update();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		updateable.update();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		updateable.update();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		updateable.update();
	}

}
