package util.input.keyboard;

import util.input.keyboard.KeySequence.KeySequenceStep;

public class KeyEvent implements KeySequenceStep{
	private WinVK vk;
	private boolean keyUp;
	public KeyEvent(WinVK vk, boolean keyUp){
		this.vk = vk;
		this.keyUp = keyUp;
	}
	public String toString(){
		return keyUp?("Release "+vk):("Press "+vk);
	}
	public WinVK getVK() {
		return vk;
	}
	public boolean isKeyUp() {
		return keyUp;
	}
	@Override
	public void execute() {
		Keyboard.send(this);
	}
}