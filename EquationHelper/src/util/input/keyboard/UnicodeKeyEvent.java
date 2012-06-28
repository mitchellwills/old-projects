package util.input.keyboard;

import util.input.keyboard.KeySequence.KeySequenceStep;

public class UnicodeKeyEvent implements KeySequenceStep {

	private char unicode;
	public UnicodeKeyEvent(char unicode) {
		this.unicode = unicode;
	}
	public char getUnicode(){
		return unicode;
	}
	
	public String toString(){
		return "Type Unicode '"+unicode+"'";
	}

	@Override
	public void execute() {
		Keyboard.send(this);
	}

}
