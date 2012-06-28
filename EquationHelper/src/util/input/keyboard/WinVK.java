package util.input.keyboard;

import java.util.HashMap;
import java.util.Map;

public enum WinVK{

	/**
	 * Left mouse button
	 */
	LBUTTON(0x01),
	/**
	 * Right mouse button
	 */
	RBUTTON(0x02),
	/**
	 * Control-break processing
	 */
	CANCEL(0x03),
	/**
	 * Middle mouse button (three-button mouse)
	 */
	MBUTTON(0x04),
	/**
	 * X1 mouse button
	 */
	XBUTTON1(0x05),
	/**
	 * X2 mouse button
	 */
	XBUTTON2(0x06),

	/**
	 * BACKSPACE key
	 */
	BACK(0x08),
	/**
	 * TAB key
	 */
	TAB(0x09),

	/**
	 * CLEAR key
	 */
	CLEAR(0x0C),
	/**
	 * ENTER key
	 */
	RETURN(0x0D),

	/**
	 * SHIFT key
	 */
	SHIFT(0x10),
	/**
	 * CTRL key
	 */
	CONTROL(0x11),
	/**
	 * ALT key
	 */
	MENU(0x12),
	/**
	 * PAUSE key
	 */
	PAUSE(0x13),
	/**
	 * CAPS LOCK key
	 */
	CAPITAL(0x14),

	/**
	 * ESC key
	 */
	ESCAPE(0x1B),
	/**
	 * IME convert
	 */
	CONVERT(0x1C),
	/**
	 * IME nonconvert
	 */
	NONCONVERT(0x1D),
	/**
	 * IME accept
	 */
	ACCEPT(0x1E),
	/**
	 * IME mode change request
	 */
	MODECHANGE(0x1F),
	/**
	 * SPACEBAR
	 */
	SPACE(0x20),
	/**
	 * PAGE UP key
	 */
	PRIOR(0x21),
	/**
	 * PAGE DOWN key
	 */
	NEXT(0x22),
	/**
	 * END key
	 */
	END(0x23),
	/**
	 * HOME key
	 */
	HOME(0x24),
	/**
	 * LEFT ARROW key
	 */
	LEFT(0x25),
	/**
	 * UP ARROW key
	 */
	UP(0x26),
	/**
	 * RIGHT ARROW key
	 */
	RIGHT(0x27),
	/**
	 * DOWN ARROW key
	 */
	DOWN(0x28),
	/**
	 * SELECT key
	 */
	SELECT(0x29),
	/**
	 * PRINT key
	 */
	PRINT(0x2A),
	/**
	 * EXECUTE key
	 */
	EXECUTE(0x2B),
	/**
	 * PRINT SCREEN key
	 */
	SNAPSHOT(0x2C),
	/**
	 * INS key
	 */
	INSERT(0x2D),
	/**
	 * DEL key
	 */
	DELETE(0x2E),
	/**
	 * HELP key
	 */
	HELP(0x2F),

	/**
	 * 0 key
	 */
	_0_KEY(0x30),
	/**
	 * 1 key
	 */
	_1_KEY(0x31),
	/**
	 * 2 key
	 */
	_2_KEY(0x32),
	/**
	 * 3 key
	 */
	_3_KEY(0x33),
	/**
	 * 4 key
	 */
	_4_KEY(0x34),
	/**
	 * 5 key
	 */
	_5_KEY(0x35),
	/**
	 * 6 key
	 */
	_6_KEY(0x36),
	/**
	 * 7 key
	 */
	_7_KEY(0x37),
	/**
	 * 8 key
	 */
	_8_KEY(0x38),
	/**
	 * 9 key
	 */
	_9_KEY(0x39),

	/**
	 * A key
	 */
	A_KEY(0x41),
	/**
	 * B key
	 */
	B_KEY(0x42),
	/**
	 * C key
	 */
	C_KEY(0x43),
	/**
	 * D key
	 */
	D_KEY(0x44),
	/**
	 * E key
	 */
	E_KEY(0x45),
	/**
	 * F key
	 */
	F_KEY(0x46),
	/**
	 * G key
	 */
	G_KEY(0x47),
	/**
	 * H key
	 */
	H_KEY(0x48),
	/**
	 * I key
	 */
	I_KEY(0x49),
	/**
	 * J key
	 */
	J_KEY(0x4A),
	/**
	 * K key
	 */
	K_KEY(0x4B),
	/**
	 * L key
	 */
	L_KEY(0x4C),
	/**
	 * M key
	 */
	M_KEY(0x4D),
	/**
	 * N key
	 */
	N_KEY(0x4E),
	/**
	 * O key
	 */
	O_KEY(0x4F),
	/**
	 * P key
	 */
	P_KEY(0x50),
	/**
	 * Q key
	 */
	Q_KEY(0x51),
	/**
	 * R key
	 */
	R_KEY(0x52),
	/**
	 * S key
	 */
	S_KEY(0x53),
	/**
	 * T key
	 */
	T_KEY(0x54),
	/**
	 * U key
	 */
	U_KEY(0x55),
	/**
	 * V key
	 */
	V_KEY(0x56),
	/**
	 * W key
	 */
	W_KEY(0x57),
	/**
	 * X key
	 */
	X_KEY(0x58),
	/**
	 * Y key
	 */
	Y_KEY(0x59),
	/**
	 * Z key
	 */
	Z_KEY(0x5A),

	/**
	 * Left Windows key (Natural keyboard)
	 */
	LWIN(0x5B),
	/**
	 * Right Windows key (Natural keyboard)
	 */
	RWIN(0x5C),
	/**
	 * Applications key (Natural keyboard)
	 */
	APPS(0x5D),

	/**
	 * Computer Sleep key
	 */
	SLEEP(0x5F),

	/**
	 * Numeric keypad 0 key
	 */
	NUMPAD0(0x60),
	/**
	 * Numeric keypad 1 key
	 */
	NUMPAD1(0x61),
	/**
	 * Numeric keypad 2 key
	 */
	NUMPAD2(0x62),
	/**
	 * Numeric keypad 3 key
	 */
	NUMPAD3(0x63),
	/**
	 * Numeric keypad 4 key
	 */
	NUMPAD4(0x64),
	/**
	 * Numeric keypad 5 key
	 */
	NUMPAD5(0x65),
	/**
	 * Numeric keypad 6 key
	 */
	NUMPAD6(0x66),
	/**
	 * Numeric keypad 7 key
	 */
	NUMPAD7(0x67),
	/**
	 * Numeric keypad 8 key
	 */
	NUMPAD8(0x68),
	/**
	 * Numeric keypad 9 key
	 */
	NUMPAD9(0x69),

	/**
	 * Multiply key
	 */
	MULTIPLY(0x6A),
	/**
	 * Add key
	 */
	ADD(0x6B),
	/**
	 * Separator key
	 */
	SEPARATOR(0x6C),
	/**
	 * Subtract key
	 */
	SUBTRACT(0x6D),
	/**
	 * Decimal key
	 */
	DECIMAL(0x6E),
	/**
	 * Divide key
	 */
	DIVIDE(0x6F),

	/**
	 * F1 key
	 */
	F1(0x70),
	/**
	 * F2 key
	 */
	F2(0x71),
	/**
	 * F3 key
	 */
	F3(0x72),
	/**
	 * F4 key
	 */
	F4(0x73),
	/**
	 * F5 key
	 */
	F5(0x74),
	/**
	 * F6 key
	 */
	F6(0x75),
	/**
	 * F7 key
	 */
	F7(0x76),
	/**
	 * F8 key
	 */
	F8(0x77),
	/**
	 * F9 key
	 */
	F9(0x78),
	/**
	 * F10 key
	 */
	F10(0x79),
	/**
	 * F11 key
	 */
	F11(0x7A),
	/**
	 * F12 key
	 */
	F12(0x7B),
	/**
	 * F13 key
	 */
	F13(0x7C),
	/**
	 * F14 key
	 */
	F14(0x7D),
	/**
	 * F15 key
	 */
	F15(0x7E),
	/**
	 * F16 key
	 */
	F16(0x7F),

	/**
	 * NUM LOCK key
	 */
	NUMLOCK(0x90),
	/**
	 * SCROLL LOCK key
	 */
	SCROLL(0x91),

	/**
	 * Left SHIFT key
	 */
	LSHIFT(0xA0),
	/**
	 * Right SHIFT key
	 */
	RSHIFT(0xA1),
	/**
	 * Left CONTROL key
	 */
	LCONTROL(0xA2),
	/**
	 * Right CONTROL key
	 */
	RCONTROL(0xA3),
	/**
	 * Left MENU key
	 */
	LMENU(0xA4),
	/**
	 * Right MENU key
	 */
	RMENU(0xA5),
	/**
	 * Browser Back key
	 */
	BROWSER_BACK(0xA6),
	/**
	 * Browser Forward key
	 */
	BROWSER_FORWARD(0xA7),
	/**
	 * Browser Refresh key
	 */
	BROWSER_REFRESH(0xA8),
	/**
	 * Browser Stop key
	 */
	BROWSER_STOP(0xA9),
	/**
	 * Browser Search key
	 */
	BROWSER_SEARCH(0xAA),
	/**
	 * Browser Favorites key
	 */
	BROWSER_FAVORITES(0xAB),
	/**
	 * Browser Start and Home key
	 */
	BROWSER_HOME(0xAC),
	/**
	 * Volume Mute key
	 */
	VOLUME_MUTE(0xAD),
	/**
	 * Volume Down key
	 */
	VOLUME_DOWN(0xAE),
	/**
	 * Volume Up key
	 */
	VOLUME_UP(0xAF),
	/**
	 * Next Track key
	 */
	MEDIA_NEXT_TRACK(0xB0),
	/**
	 * Previous Track key
	 */
	MEDIA_PREV_TRACK(0xB1),
	/**
	 * Stop Media key
	 */
	MEDIA_STOP(0xB2),
	/**
	 * Play/Pause Media key
	 */
	MEDIA_PLAY_PAUSE(0xB3),
	/**
	 * Start Mail key
	 */
	LAUNCH_MAIL(0xB4),
	/**
	 * Select Media key
	 */
	LAUNCH_MEDIA_SELECT(0xB5),
	/**
	 * Start Application 1 key
	 */
	LAUNCH_APP1(0xB6),
	/**
	 * Start Application 2 key
	 */
	LAUNCH_APP2(0xB7),

	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the ';:' key
	 */
	OEM_1(0xBA),
	/**
	 * For any country/region, the '+' key
	 */
	OEM_PLUS(0xBB),
	/**
	 * For any country/region, the ',' key
	 */
	OEM_COMMA(0xBC),
	/**
	 * For any country/region, the '-' key
	 */
	OEM_MINUS(0xBD),
	/**
	 * For any country/region, the '.' key
	 */
	OEM_PERIOD(0xBE),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the '/?' key
	 */
	OEM_2(0xBF),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the '`~' key
	 */
	OEM_3(0xC0),

	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the '[{' key
	 */
	OEM_4(0xDB),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the '\|' key
	 */
	OEM_5(0xDC),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the ']}' key
	 */
	OEM_6(0xDD),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard. For the US
	 * standard keyboard, the 'single-quote/double-quote' key
	 */
	OEM_7(0xDE),
	/**
	 * Used for miscellaneous characters; it can vary by keyboard.
	 */
	OEM_8(0xDF),

	/**
	 * Either the angle bracket key or the backslash key on the RT 102-key
	 * keyboard
	 */
	OEM_102(0xE2),

	/**
	 * IME PROCESS key
	 */
	PROCESSKEY(0xE5),

	/**
	 * Used to pass Unicode characters as if they were keystrokes. The VK_PACKET
	 * key is the low word of a 32-bit Virtual Key value used for non-keyboard
	 * input methods. For more information, see Remark in KEYBDINPUT, SendInput,
	 * WM_KEYDOWN, and WM_KEYUP
	 */
	PACKET(0xE7),

	/**
	 * Attn key
	 */
	ATTN(0xF6),
	/**
	 * CrSel key
	 */
	CRSEL(0xF7),
	/**
	 * ExSel key
	 */
	EXSEL(0xF8),
	/**
	 * Erase EOF key
	 */
	EREOF(0xF9),
	/**
	 * Play key
	 */
	PLAY(0xFA),
	/**
	 * Zoom key
	 */
	ZOOM(0xFB),
	/**
	 * Reserved
	 */
	NONAME(0xFC),
	/**
	 * PA1 key
	 */
	PA1(0xFD),
	/**
	 * Clear key
	 */
	OEM_CLEAR(0xFE);
	
	
	private final int vkCode;
	private WinVK(int vkCode){
		this.vkCode = vkCode;
	}
	
	public int getVKCode() {
		return vkCode;
	}
	
	public String toString(){
		return name()+" (0x"+Integer.toHexString(getVKCode())+")";
	}

	private static Map<Integer, WinVK> vkMap = null;
	public static synchronized WinVK getVK(int vkCode){
		if(vkMap==null){
			vkMap = new HashMap<Integer, WinVK>();
			for(WinVK vk:values())
				vkMap.put(vk.getVKCode(), vk);
		}
		return vkMap.get(vkCode);
	}

	public static WinVK getNumpadKey(int i){
		switch(i){
		case 0:
			return WinVK.NUMPAD0;
		case 1:
			return WinVK.NUMPAD1;
		case 2:
			return WinVK.NUMPAD2;
		case 3:
			return WinVK.NUMPAD3;
		case 4:
			return WinVK.NUMPAD4;
		case 5:
			return WinVK.NUMPAD5;
		case 6:
			return WinVK.NUMPAD6;
		case 7:
			return WinVK.NUMPAD7;
		case 8:
			return WinVK.NUMPAD8;
		case 9:
			return WinVK.NUMPAD9;
		}
		return null;
	}
	public static WinVK getNumberKey(int i){
		switch(i){
		case 0:
			return WinVK._0_KEY;
		case 1:
			return WinVK._1_KEY;
		case 2:
			return WinVK._2_KEY;
		case 3:
			return WinVK._3_KEY;
		case 4:
			return WinVK._4_KEY;
		case 5:
			return WinVK._5_KEY;
		case 6:
			return WinVK._6_KEY;
		case 7:
			return WinVK._7_KEY;
		case 8:
			return WinVK._8_KEY;
		case 9:
			return WinVK._9_KEY;
		}
		return null;
	}

	public static WinVK getHotkeyVK(String hotkeyString) {
		if(hotkeyString.length()==0)
			return null;
		switch(hotkeyString.charAt(hotkeyString.length()-1)){
		case 'A':
			return WinVK.A_KEY;
		case 'B':
			return WinVK.B_KEY;
		case 'C':
			return WinVK.C_KEY;
		case 'D':
			return WinVK.D_KEY;
		case 'E':
			return WinVK.E_KEY;
		case 'F':
			return WinVK.F_KEY;
		case 'G':
			return WinVK.G_KEY;
		case 'H':
			return WinVK.H_KEY;
		case 'I':
			return WinVK.I_KEY;
		case 'J':
			return WinVK.J_KEY;
		case 'K':
			return WinVK.K_KEY;
		case 'L':
			return WinVK.L_KEY;
		case 'M':
			return WinVK.M_KEY;
		case 'N':
			return WinVK.N_KEY;
		case 'O':
			return WinVK.O_KEY;
		case 'P':
			return WinVK.P_KEY;
		case 'Q':
			return WinVK.Q_KEY;
		case 'R':
			return WinVK.R_KEY;
		case 'S':
			return WinVK.S_KEY;
		case 'T':
			return WinVK.T_KEY;
		case 'U':
			return WinVK.U_KEY;
		case 'V':
			return WinVK.V_KEY;
		case 'W':
			return WinVK.W_KEY;
		case 'X':
			return WinVK.X_KEY;
		case 'Y':
			return WinVK.Y_KEY;
		case 'Z':
			return WinVK.Z_KEY;
		case '1':
			return WinVK._1_KEY;
		case '2':
			return WinVK._2_KEY;
		case '3':
			return WinVK._3_KEY;
		case '4':
			return WinVK._4_KEY;
		case '5':
			return WinVK._5_KEY;
		case '6':
			return WinVK._6_KEY;
		case '7':
			return WinVK._7_KEY;
		case '8':
			return WinVK._8_KEY;
		case '9':
			return WinVK._9_KEY;
		case '0':
			return WinVK._0_KEY;
		case '-':
			return WinVK.OEM_MINUS;
		case '=':
			return WinVK.OEM_PLUS;
		}
		throw new RuntimeException("Unknown key in hotkey string: "+hotkeyString);
	}
	
}