package util.input.keyboard;

public enum GreekLetter {
	Alpha(0x0391, 0x03B1), Beta(0x0392, 0x03B2), Gamma(0x0393, 0x03B3), Delta(
			0x0394, 0x03B4), Epsilon(0x0395, 0x03B5), Zeta(0x0396, 0x03B6), Eta(
			0x0397, 0x03B7), Theta(0x0398, 0x03B8), Iota(0x0399, 0x03B9), Kappa(
			0x039A, 0x03BA), Lambda(0x039B, 0x03BB), Mu(0x039C, 0x03BC), Nu(
			0x039D, 0x03BD), Xi(0x039E, 0x03BE), Omicron(0x039F, 0x03BF), Pi(
			0x03A0, 0x03C0), Rho(0x03A1, 0x03C1), Sigma(0x03A3, 0x03C3), Tau(
			0x03A4, 0x03C4), Upsilon(0x03A5, 0x03C5), Phi(0x03A6, 0x03C6), Chi(
			0x03A7, 0x03C7), Psi(0x03A8, 0x03C8), Omega(0x03A9, 0x03C9);

	private char upperCase;
	private char lowerCase;

	private GreekLetter(char upperCase, char lowerCase) {
		this.upperCase = upperCase;
		this.lowerCase = lowerCase;
	}

	private GreekLetter(int upperCase, int lowerCase) {
		this((char) upperCase, (char) lowerCase);
	}

	public char getUpperCase() {
		return upperCase;
	}

	public char getLowerCase() {
		return lowerCase;
	}

}