package assistant;

import javafx.beans.value.*;

public class UpdateableChangeAdapter<T> implements ChangeListener<T> {

	private final Updateable updateable;

	public UpdateableChangeAdapter(Updateable updateable){
		this.updateable = updateable;
	}

	@Override
	public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
		updateable.update();
	}

}
