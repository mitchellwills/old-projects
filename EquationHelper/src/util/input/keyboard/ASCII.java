package util.input.keyboard;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public enum ASCII {

	/**
	 * ^@ \0 Null character
	 */
	NUL(0x00),
	/**
	 * ^A Start of Header
	 */
	SOH(0x01),
	/**
	 * ^B Start of Text
	 */
	STX(0x02),
	/**
	 * ^C End of Text
	 */
	ETX(0x03),
	/**
	 * ^D End of Transmission
	 */
	EOT(0x04),
	/**
	 * ^E Enquiry
	 */
	ENQ(0x05),
	/**
	 * ^F Acknowledgment
	 */
	ACK(0x06),
	/**
	 * ^G \a Bell
	 */
	BEL(0x07),
	/**
	 * ^H \b Backspace[d][e]
	 */
	BS(0x08, WinVK.BACK),
	/**
	 * ^I \t Horizontal Tab[f]
	 */
	HT(0x09, WinVK.TAB),
	/**
	 * ^J \n Line feed
	 */
	LF(0x0A, WinVK.RETURN),
	/**
	 * ^K \v Vertical Tab
	 */
	VT(0x0B),
	/**
	 * ^L \f Form feed
	 */
	FF(0x0C),
	/**
	 * ^M \r Carriage return[g]
	 */
	CR(0x0D),
	/**
	 * ^N Shift Out
	 */
	SO(0x0E),
	/**
	 * ^O Shift In
	 */
	SI(0x0F),
	/**
	 * ^P Data Link Escape
	 */
	DLE(0x10),
	/**
	 * ^Q Device Control 1 (oft. XON)
	 */
	DC1(0x11),
	/**
	 * ^R Device Control 2
	 */
	DC2(0x12),
	/**
	 * ^S Device Control 3 (oft. XOFF)
	 */
	DC3(0x13),
	/**
	 * ^T Device Control 4
	 */
	DC4(0x14),
	/**
	 * ^U Negative Acknowledgement
	 */
	NAK(0x15),
	/**
	 * ^V Synchronous idle
	 */
	SYN(0x16),
	/**
	 * ^W End of Transmission Block
	 */
	ETB(0x17),
	/**
	 * ^X Cancel
	 */
	CAN(0x18),
	/**
	 * ^Y End of Medium
	 */
	EM(0x19),
	/**
	 * ^Z Substitute
	 */
	SUB(0x1A),
	/**
	 * ^[ \e[h] Escape[i]
	 */
	ESC(0x1B),
	/**
	 * ^\ File Separator
	 */
	FS(0x1C),
	/**
	 * ^] Group Separator
	 */
	GS(0x1D),
	/**
	 * ^^[j] Record Separator
	 */
	RS(0x1E),
	/**
	 * ^_ Unit Separator
	 */
	US(0x1F),

	/**
	
	*/
	SPACE(0x20, WinVK.SPACE),
	/**
	 * !
	 */
	EXCLAMATION_MARK(0x21, WinVK._1_KEY, true),
	/**
	 * "
	 */
	DOUBLE_QUOTE(0x22, WinVK.OEM_7, true),
	/**
	 * #
	 */
	POUND(0x23, WinVK._3_KEY, true),
	/**
	 * $
	 */
	DOLLAR(0x24, WinVK._4_KEY, true),
	/**
	 * %
	 */
	PERCENT(0x25, WinVK._5_KEY, true),
	/**
	 * &
	 */
	AMPERSAND(0x26, WinVK._7_KEY, true),
	/**
	 * '
	 */
	SINGLE_QUOTE(0x27, WinVK.OEM_7),
	/**
	 * (
	 */
	OPEN_PAREN(0x28, WinVK._9_KEY, true),
	/**
	 * )
	 */
	CLOSE_PAREN(0x29, WinVK._0_KEY, true),
	/**
	*
	*/
	ASTERIX(0x2A, WinVK._8_KEY, true),
	/**
	 * +
	 */
	PLUS(0x2B, WinVK.OEM_PLUS, true),
	/**
	 * ,
	 */
	COMMA(0x2C, WinVK.OEM_COMMA),
	/**
	 * -
	 */
	MINUS(0x2D, WinVK.OEM_MINUS),
	/**
	 * .
	 */
	PERIOD(0x2E, WinVK.OEM_PERIOD),
	/**
	 * /
	 */
	FORWARD_SLASH(0x2F, WinVK.OEM_2),
	/**
	 * 0
	 */
	_0(0x30, WinVK._0_KEY),
	/**
	 * 1
	 */
	_1(0x31, WinVK._1_KEY),
	/**
	 * 2
	 */
	_2(0x32, WinVK._2_KEY),
	/**
	 * 3
	 */
	_3(0x33, WinVK._3_KEY),
	/**
	 * 4
	 */
	_4(0x34, WinVK._4_KEY),
	/**
	 * 5
	 */
	_5(0x35, WinVK._5_KEY),
	/**
	 * 6
	 */
	_6(0x36, WinVK._6_KEY),
	/**
	 * 7
	 */
	_7(0x37, WinVK._7_KEY),
	/**
	 * 8
	 */
	_8(0x38, WinVK._8_KEY),
	/**
	 * 9
	 */
	_9(0x39, WinVK._9_KEY),
	/**
	 * :
	 */
	COLON(0x3A, WinVK.OEM_1, true),
	/**
	 * ;
	 */
	SEMICOLON(0x3B, WinVK.OEM_1),
	/**
	 * <
	 */
	LESS_THAN(0x3C, WinVK.OEM_COMMA, true),
	/**
	 * =
	 */
	EQUALS(0x3D, WinVK.OEM_PLUS),
	/**
	 * >
	 */
	GREATER_THAN(0x3E, WinVK.OEM_PERIOD, true),
	/**
	 * ?
	 */
	QUESTION_MARK(0x3F, WinVK.OEM_2, true),
	/**
	 * @
	 */
	AT(0x40, WinVK._2_KEY, true),
	/**
	 * A
	 */
	A(0x41, WinVK.A_KEY, true),
	/**
	 * B
	 */
	B(0x42, WinVK.B_KEY, true),
	/**
	 * C
	 */
	C(0x43, WinVK.C_KEY, true),
	/**
	 * D
	 */
	D(0x44, WinVK.D_KEY, true),
	/**
	 * E
	 */
	E(0x45, WinVK.E_KEY, true),
	/**
	 * F
	 */
	F(0x46, WinVK.F_KEY, true),
	/**
	 * G
	 */
	G(0x47, WinVK.G_KEY, true),
	/**
	 * H
	 */
	H(0x48, WinVK.H_KEY, true),
	/**
	 * I
	 */
	I(0x49, WinVK.I_KEY, true),
	/**
	 * J
	 */
	J(0x4A, WinVK.J_KEY, true),
	/**
	 * K
	 */
	K(0x4B, WinVK.K_KEY, true),
	/**
	 * L
	 */
	L(0x4C, WinVK.L_KEY, true),
	/**
	 * M
	 */
	M(0x4D, WinVK.M_KEY, true),
	/**
	 * N
	 */
	N(0x4E, WinVK.N_KEY, true),
	/**
	 * O
	 */
	O(0x4F, WinVK.O_KEY, true),
	/**
	 * P
	 */
	P(0x50, WinVK.P_KEY, true),
	/**
	 * Q
	 */
	Q(0x51, WinVK.Q_KEY, true),
	/**
	 * R
	 */
	R(0x52, WinVK.R_KEY, true),
	/**
	 * S
	 */
	S(0x53, WinVK.S_KEY, true),
	/**
	 * T
	 */
	T(0x54, WinVK.T_KEY, true),
	/**
	 * U
	 */
	U(0x55, WinVK.U_KEY, true),
	/**
	 * V
	 */
	V(0x56, WinVK.V_KEY, true),
	/**
	 * W
	 */
	W(0x57, WinVK.W_KEY, true),
	/**
	 * X
	 */
	X(0x58, WinVK.X_KEY, true),
	/**
	 * Y
	 */
	Y(0x59, WinVK.Y_KEY, true),
	/**
	 * Z
	 */
	Z(0x5A, WinVK.Z_KEY, true),
	/**
	 * [
	 */
	OPEN_SQUARE_BRACE(0x5B, WinVK.OEM_4),
	/**
	 * \
	 */
	BACK_SLASH(0x5C, WinVK.OEM_5),
	/**
	 * ]
	 */
	CLOSE_SQUARE_BRACE(0x5D, WinVK.OEM_6),
	/**
	 * ^
	 */
	CARET(0x5E, WinVK._6_KEY, true),
	/**
	 * _
	 */
	UNDERSCORE(0x5F, WinVK.OEM_MINUS, true),
	/**
	 * `
	 */
	BACKQUOTE(0x60, WinVK.OEM_3),
	/**
	 * a
	 */
	a(0x61, WinVK.A_KEY),
	/**
	 * b
	 */
	b(0x62, WinVK.B_KEY),
	/**
	 * c
	 */
	c(0x63, WinVK.C_KEY),
	/**
	 * d
	 */
	d(0x64, WinVK.D_KEY),
	/**
	 * e
	 */
	e(0x65, WinVK.E_KEY),
	/**
	 * f
	 */
	f(0x66, WinVK.F_KEY),
	/**
	 * g
	 */
	g(0x67, WinVK.G_KEY),
	/**
	 * h
	 */
	h(0x68, WinVK.H_KEY),
	/**
	 * i
	 */
	i(0x69, WinVK.I_KEY),
	/**
	 * j
	 */
	j(0x6A, WinVK.J_KEY),
	/**
	 * k
	 */
	k(0x6B, WinVK.K_KEY),
	/**
	 * l
	 */
	l(0x6C, WinVK.L_KEY),
	/**
	 * m
	 */
	m(0x6D, WinVK.M_KEY),
	/**
	 * n
	 */
	n(0x6E, WinVK.N_KEY),
	/**
	 * o
	 */
	o(0x6F, WinVK.O_KEY),
	/**
	 * p
	 */
	p(0x70, WinVK.P_KEY),
	/**
	 * q
	 */
	q(0x71, WinVK.Q_KEY),
	/**
	 * r
	 */
	r(0x72, WinVK.R_KEY),
	/**
	 * s
	 */
	s(0x73, WinVK.S_KEY),
	/**
	 * t
	 */
	t(0x74, WinVK.T_KEY),
	/**
	 * u
	 */
	u(0x75, WinVK.U_KEY),
	/**
	 * v
	 */
	v(0x76, WinVK.V_KEY),
	/**
	 * w
	 */
	w(0x77, WinVK.W_KEY),
	/**
	 * x
	 */
	x(0x78, WinVK.X_KEY),
	/**
	 * y
	 */
	y(0x79, WinVK.Y_KEY),
	/**
	 * z
	 */
	z(0x7A, WinVK.Z_KEY),
	/**
	 * {
	 */
	OPEN_BRACE(0x7B, WinVK.OEM_4, true),
	/**
	 * |
	 */
	VERTICAL_BAR(0x7C, WinVK.OEM_5, true),
	/**
	 * }
	 */
	CLOSE_BRACE(0x7D, WinVK.OEM_6, true),
	/**
	 * ~
	 */
	TILDE(0x7E, WinVK.OEM_3, true),

	/**
	 * ^? Delete[k][e]
	 */
	DEL(0x7F, WinVK.DELETE);

	private ASCII(int c) {
		this(c, null, Collections.<KeyMod> emptySet());
	}

	private ASCII(int c, WinVK vk) {
		this((char) c, vk, false);
	}

	private ASCII(int c, WinVK vk, boolean shift) {
		this((char) c, vk, shift ? EnumSet.of(KeyMod.Shift) : Collections
				.<KeyMod> emptySet());
	}

	private ASCII(int c, WinVK vk, Set<KeyMod> modifiers) {
		this((char) c, vk, modifiers);
	}

	private final char rawChar;
	private final KeySequence typeSequence;

	private ASCII(char rawChar, WinVK vk, Set<KeyMod> modifiers) {
		this.rawChar = rawChar;
		if (vk == null)
			typeSequence = null;
		else {
			typeSequence = new KeySequenceBuilder().typeVK(vk, modifiers)
					.build();
		}
	}

	public char getChar() {
		return rawChar;
	}

	public KeySequence getTypeSequence() {
		return typeSequence;
	}

	private static Map<Character, ASCII> characterMap = null;

	public static synchronized ASCII getASCII(char c) {
		if (characterMap == null) {
			characterMap = new HashMap<Character, ASCII>();
			for (ASCII ascii : values())
				characterMap.put(ascii.getChar(), ascii);
		}
		return characterMap.get(c);
	}
}
