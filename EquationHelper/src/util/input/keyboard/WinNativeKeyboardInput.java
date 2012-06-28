package util.input.keyboard;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;

class WinNativeKeyboardInput extends INPUT{//will be freed with GC
	public WinNativeKeyboardInput(){
		init();
	}
	public WinNativeKeyboardInput(int vkCode, boolean keyUp){
		init();
		vkEvent(vkCode, keyUp);
	}
	public WinNativeKeyboardInput(char c){
		init();
		unicodeEvent(c);
	}
	public void init(){
		type = new DWORD(INPUT.INPUT_KEYBOARD);
		input.setType("ki");
		input.ki.wVk = new WORD(0);
		input.ki.dwFlags = new DWORD(0);
		input.ki.wScan = new WORD(0);
		input.ki.time = new DWORD(0);
		input.ki.dwExtraInfo = new ULONG_PTR(0);
		write();
		input.ki.write();
	}
	public void set(int wVK, int dwFlags, int wScan){
		input.ki.wVk.setValue(wVK);
		input.ki.dwFlags.setValue(dwFlags);
		input.ki.wScan.setValue(wScan);
		input.ki.time.setValue(0);
		input.ki.write();
	}
	public void vkEvent(int vkCode, boolean keyUp) {
		set(vkCode, keyUp?KEYBDINPUT.KEYEVENTF_KEYUP:0, 0);
	}
	public void unicodeEvent(char c) {
		set(0, KEYBDINPUT.KEYEVENTF_UNICODE, c);
	}
}