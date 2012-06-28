package util.input.keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import util.input.keyboard.KeySequence.KeySequenceStep;

public class KeySequenceBuilder {
	private List<KeySequenceStep> steps = new ArrayList<KeySequenceStep>();

	public KeySequenceBuilder typeVK(WinVK vk, Set<KeyMod> modifiers) {
		if(vk==null)
			return this;
		
		if (modifiers.contains(KeyMod.Ctrl))
			pressVK(WinVK.CONTROL);
		if (modifiers.contains(KeyMod.Alt))
			pressVK(WinVK.MENU);
		if (modifiers.contains(KeyMod.Win))
			pressVK(WinVK.LWIN);
		if (modifiers.contains(KeyMod.Shift))
			pressVK(WinVK.SHIFT);

		pressVK(vk);
		releaseVK(vk);

		if (modifiers.contains(KeyMod.Ctrl))
			releaseVK(WinVK.CONTROL);
		if (modifiers.contains(KeyMod.Alt))
			releaseVK(WinVK.MENU);
		if (modifiers.contains(KeyMod.Win))
			releaseVK(WinVK.LWIN);
		if (modifiers.contains(KeyMod.Shift))
			releaseVK(WinVK.SHIFT);

		return this;
	}

	public KeySequenceBuilder typeUnicode(char unicode) {
		steps.add(new UnicodeKeyEvent(unicode));
		return this;
	}

	public KeySequenceBuilder typeVK(WinVK vk) {
		typeVK(vk, Collections.<KeyMod> emptySet());
		return this;
	}

	public KeySequenceBuilder pressVK(WinVK vk) {
		addVKStep(vk, false);
		return this;
	}

	public KeySequenceBuilder releaseVK(WinVK vk) {
		addVKStep(vk, true);
		return this;
	}

	public KeySequenceBuilder addVKStep(WinVK vk, boolean keyUp) {
		steps.add(new KeyEvent(vk, keyUp));
		return this;
	}

	public KeySequenceBuilder waitTime(long time) {// in ms
		steps.add(new KeySequence.WaitStep(time));
		return this;
	}

	public KeySequence build() {
		return new KeySequence(steps.toArray(new KeySequenceStep[steps.size()]));
	}

	public KeySequenceBuilder type(String text) {
		for (int i = 0; i < text.length(); ++i)
			typeChar(text.charAt(i));
		return this;
	}

	public KeySequenceBuilder typeChar(char c) {
		ASCII ascii = ASCII.getASCII(c);
		if(ascii!=null)
			return append(ascii.getTypeSequence());
		else
			return typeUnicode(c);
	}

	public KeySequenceBuilder append(KeySequence sequence) {
		for (KeySequenceStep step : sequence.getSteps())
			steps.add(step);
		return this;
	}
}
