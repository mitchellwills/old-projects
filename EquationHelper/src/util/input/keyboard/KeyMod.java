package util.input.keyboard;

import java.util.EnumSet;
import java.util.Set;

import com.sun.jna.platform.win32.WinUser;

public enum KeyMod {
	Ctrl(WinUser.MOD_CONTROL, '^'), Alt(WinUser.MOD_ALT, '!'), Win(WinUser.MOD_WIN, '#'), Shift(WinUser.MOD_SHIFT, '+'), NOREPEAT(WinUser.MOD_NOREPEAT);

	private int modValue;
	private char hotkeyStringSymbol;
	private KeyMod(int modValue){
		this(modValue, '\0');
	}
	private KeyMod(int modValue, char hotkeyStringSymbol){
		this.modValue = modValue;
		this.hotkeyStringSymbol = hotkeyStringSymbol;
	}
	public int getModValue() {
		return modValue;
	}
	public char getHotkeyStringSymbol() {
		return hotkeyStringSymbol;
	}
	public static Set<KeyMod> getHotkeyModifiers(String hotkeyString) {
		Set<KeyMod> modifiers = EnumSet.noneOf(KeyMod.class);
		for(KeyMod mod:KeyMod.values())
			if(hotkeyString.contains(Character.toString(mod.getHotkeyStringSymbol())))
				modifiers.add(mod);
		return modifiers;
	}
}
