package options.field;

public abstract class DefaultValueField<T> extends ValueField<T> {

	protected DefaultValueField(String name, Class<T> type) {
		super(name, type);
	}
	
	public int getInt(){
		throw new UnsupportedOperationException("Cannot get as integer");
	}
	public double getDouble(){
		throw new UnsupportedOperationException("Cannot get as double");
	}
	public boolean getBoolean() {
		throw new UnsupportedOperationException("Cannot get as boolean");
	}


	public void set(int value){
		throw new UnsupportedOperationException("Cannot set as integer");
	}
	public void set(double value){
		throw new UnsupportedOperationException("Cannot set as double");
	}
	public void set(boolean value) {
		throw new UnsupportedOperationException("Cannot set as boolean");
	}
	public void setRaw(T value) {
		throw new UnsupportedOperationException("Cannot set as raw");
	}



}
