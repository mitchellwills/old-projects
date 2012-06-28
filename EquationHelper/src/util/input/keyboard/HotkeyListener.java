package util.input.keyboard;

import java.util.Set;

public interface HotkeyListener {
	void hotkey(Set<KeyMod> modifiers, WinVK vk);
}
