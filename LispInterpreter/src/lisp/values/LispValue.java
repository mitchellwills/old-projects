package lisp.values;

import lisp.runtime.LispContext;
import lisp.runtime.LispRuntimeException;

public interface LispValue {
	public LispValue eval(LispContext context) throws LispRuntimeException;
	
	public static final LispVoid VOID = LispVoid.getInstance();

	public class NumberValue implements LispValue {
		private double value;

		public NumberValue(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}
		@Override
		public String toString(){
			return Double.toString(value);
		}

		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(value);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			NumberValue other = (NumberValue) obj;
			if (Double.doubleToLongBits(value) != Double
					.doubleToLongBits(other.value))
				return false;
			return true;
		}

	}

	public class StringValue implements LispValue {
		private String value;

		public StringValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return "\"" + value + "\"";
		}

		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			StringValue other = (StringValue) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
	}

	public class CharacterValue implements LispValue {
		private char value;

		public CharacterValue(char value) {
			this.value = value;
		}

		public char getValue() {
			return value;
		}

		public String toString() {
			return "#\\" + value;
		}

		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
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
			CharacterValue other = (CharacterValue) obj;
			if (value != other.value)
				return false;
			return true;
		}
		
	}

	public class BooleanValue implements LispValue {
		public static final BooleanValue TRUE = new BooleanValue(true);
		public static final BooleanValue FALSE = new BooleanValue(false);
		
		private boolean value;

		public BooleanValue(boolean value) {
			this.value = value;
		}

		public boolean getValue() {
			return value;
		}

		public String toString() {
			return value?"#t":"#f";
		}

		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (value ? 1231 : 1237);
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
			BooleanValue other = (BooleanValue) obj;
			if (value != other.value)
				return false;
			return true;
		}
		
	}

	public class SymbolValue implements LispValue {
		private String value;

		public SymbolValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return "#%"+value;
		}

		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			SymbolValue other = (SymbolValue) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
	}

	public class KeywordValue implements LispValue {
		private String value;

		public KeywordValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return "#:"+value;
		}
		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			KeywordValue other = (KeywordValue) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
	}

	public class BoxValue implements LispValue {
		private String value;

		public BoxValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return "#&"+value;
		}
		@Override
		public LispValue eval(LispContext context) {
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			BoxValue other = (BoxValue) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
		
	}
}
