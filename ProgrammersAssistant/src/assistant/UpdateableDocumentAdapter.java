package assistant;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class UpdateableDocumentAdapter implements DocumentListener{
	private final Updateable updateable;

	public UpdateableDocumentAdapter(Updateable updateable){
		this.updateable = updateable;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateable.update();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateable.update();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateable.update();
	}
}
