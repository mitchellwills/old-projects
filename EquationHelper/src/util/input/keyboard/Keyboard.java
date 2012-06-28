package util.input.keyboard;

import java.util.ArrayList;
import java.util.Set;

import util.input.Input;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.MSG;

public class Keyboard {
	public static void send(KeyEvent event) {
		sendVKEvent(event.getVK().getVKCode(), event.isKeyUp());
	}

	public static void send(UnicodeKeyEvent event) {
		sendUnicodeEvent(event.getUnicode());
	}

	public static void typeKey(WinVK vk) {
		sendVKEvent(vk.getVKCode(), false);
		sendVKEvent(vk.getVKCode(), true);
	}

	public static void sendVKEvent(int vkCode, boolean keyup) {
		Input.sendInput(new WinNativeKeyboardInput(vkCode, keyup));
	}

	public static void sendUnicodeEvent(char c) {
		Input.sendInput(new WinNativeKeyboardInput(c));
	}


	private static boolean blockKeyboard = false;

	public static void setBlockKeyboard(boolean block) {
		blockKeyboard = block;
	}
	public static boolean isBlockKeyboard(){
		return blockKeyboard;
	}

	public static void processKey(WinVK vk, boolean pressed) {
		fireListeners(new KeyEvent(vk, !pressed));
	}
	private static final ArrayList<KeyboardListener> listeners = new ArrayList<KeyboardListener>();
	public static void addListener(KeyboardListener listener){
		listeners.add(listener);
	}
	public static void removeListener(KeyboardListener listener){
		listeners.remove(listener);
	}
	public static void fireListeners(KeyEvent e){
		for(KeyboardListener l:listeners)
			l.keyEvent(e);
	}

	

	public static synchronized void registerHotkey(Set<KeyMod> modifiers, WinVK vk, HotkeyListener listener) {
		new HotkeyMessageLoopThread(modifiers, vk, listener).start();
	}

	private static class HotkeyMessageLoopThread extends Thread {
		private static int nextHotkeyId = 1;
		
		private final int hotkeyId;
		private final Set<KeyMod> modifiers;
		private final WinVK vk;
		private final HotkeyListener listener;
		public HotkeyMessageLoopThread(Set<KeyMod> modifiers, WinVK vk, HotkeyListener listener) {
			setDaemon(true);
			hotkeyId = nextHotkeyId++;
			this.modifiers = modifiers;
			this.vk = vk;
			this.listener = listener;
		}

		public void run() {
			int modifierInt = 0;
			for(KeyMod mod:modifiers)
				modifierInt |= mod.getModValue();
			if(!User32.INSTANCE.RegisterHotKey(null, hotkeyId, modifierInt, vk.getVKCode())){
				System.err.println("Failed to register hotkey "+modifiers+" - "+vk+"   Code: "+Kernel32.INSTANCE.GetLastError());
				return;
			}
			
			int result;
			MSG msg = new MSG();
			while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
				if (msg.message == WinUser.WM_HOTKEY) {
					listener.hotkey(modifiers, vk);
		        }
				if (result == -1) {
					break;
				} else {
					User32.INSTANCE.TranslateMessage(msg);
					User32.INSTANCE.DispatchMessage(msg);
				}
			}
			User32.INSTANCE.UnregisterHotKey(null, hotkeyId);
		}
	}
	
	
	
}
