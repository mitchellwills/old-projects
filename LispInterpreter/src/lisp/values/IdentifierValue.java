package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public class IdentifierValue implements LispValue {
	private String name;

	public IdentifierValue(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString(){
		return "{var:"+name+"}";
	}
	@Override
	public LispValue eval(LispContext context) throws LispRuntimeException {
		return context.lookup(name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifierValue other = (IdentifierValue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}