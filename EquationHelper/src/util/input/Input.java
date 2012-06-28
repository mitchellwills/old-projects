package util.input;

import util.input.keyboard.Keyboard;
import util.input.keyboard.WinVK;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

public final class Input {
	private Input() {
	}

	private static INPUT[] inputArray = { null };
	private static DWORD nInputs = new DWORD(inputArray.length);

	public static synchronized void sendInput(INPUT input) {
		inputArray[0] = input;
		User32.INSTANCE.SendInput(nInputs, inputArray, input.size());

	}

	public static void initNativeListener() {
		messageLoopThread = new MessageLoopThread();
		messageLoopThread.start();
	}

	public static void cleanupNativeListener() {
		User32.INSTANCE.UnhookWindowsHookEx(messageLoopThread.getHook());
	}

	private static MessageLoopThread messageLoopThread = null;

	private static class MessageLoopThread extends Thread {
		public MessageLoopThread() {
			setDaemon(true);
		}

		private HHOOK hHook = null;
		private LRESULT blockResult = new LRESULT(1);

		private LowLevelKeyboardProc keyboardCallback = new LowLevelKeyboardProc() {
			@Override
			public LRESULT callback(int nCode, WPARAM wParam,
					KBDLLHOOKSTRUCT lParam) {
				switch (wParam.intValue()) {
				case User32.WM_KEYDOWN:
				case User32.WM_SYSKEYDOWN:
					Keyboard.processKey(WinVK.getVK(lParam.vkCode), true);
					break;
				case User32.WM_KEYUP:
				case User32.WM_SYSKEYUP:
					Keyboard.processKey(WinVK.getVK(lParam.vkCode), false);
				}
				if (Keyboard.isBlockKeyboard())
					return blockResult;
				return User32.INSTANCE.CallNextHookEx(hHook, nCode, wParam,
						lParam.getPointer());
			}
		};

		public void run() {
			HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
			hHook = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL,
					keyboardCallback, hMod, 0);

			int result;
			MSG msg = new MSG();
			while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
				if (result == -1) {
					break;
				} else {
					User32.INSTANCE.TranslateMessage(msg);
					User32.INSTANCE.DispatchMessage(msg);
				}
			}
			User32.INSTANCE.UnhookWindowsHookEx(hHook);
			hHook = null;
		}

		public HHOOK getHook() {
			return hHook;
		}
	}
}