package options.field;

import java.io.Serializable;

import options.gui.OptionEditComponent;


public abstract class ValueField<T> implements Serializable{
	
	private final String name;
	private final Class<T> type;

	protected ValueField(String name, Class<T> type) {
		this.name = name;
		this.type = type;
	}
	public String getName(){
		return name;
	}
	public Class<T> getType(){
		return type;
	}
	
	public abstract int getInt();
	public abstract double getDouble();
	public abstract boolean getBoolean();
	public abstract String getString();
	public abstract T get();


	public abstract void set(int value);
	public abstract void set(double value);
	public abstract void set(boolean value);
	public abstract void set(String value);
	public abstract void setRaw(T value);
	
	private OptionEditComponent<T> editComponent = null;
	public OptionEditComponent<T> getEditComponent(){
		if(editComponent==null)
			editComponent = buildEditComponent();
		return editComponent;
	}
	protected abstract OptionEditComponent<T> buildEditComponent();
}