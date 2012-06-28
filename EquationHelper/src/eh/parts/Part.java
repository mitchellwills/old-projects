package eh.parts;

import java.util.Set;

import javax.swing.Icon;

import util.input.keyboard.HotkeyListener;
import util.input.keyboard.KeyMod;
import util.input.keyboard.Keyboard;
import util.input.keyboard.WinVK;
import eh.OutputMode;
import eh.options.GeneralOptions;

public abstract class Part {
	public Part() {
		this(null, null);
	}
	public Part(String hotkeyString) {
		this(KeyMod.getHotkeyModifiers(hotkeyString), WinVK.getHotkeyVK(hotkeyString));
	}
	public Part(Set<KeyMod> modifiers, WinVK vk) {
		if (modifiers != null && vk != null)
			Keyboard.registerHotkey(modifiers, vk, new HotkeyListener() {
				@Override
				public void hotkey(Set<KeyMod> modifiers, WinVK vk) {
					execute(OutputMode.getOption(GeneralOptions.OUTPUT_MODE));
				}
			});
	}

	public abstract boolean suppots(OutputMode mode);

	public abstract void execute(OutputMode mode);

	public abstract Icon getIcon();

	public abstract String getName();
}
