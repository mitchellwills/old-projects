package site;

public class SiteBuildException extends RuntimeException {

	private static final long serialVersionUID = 8077495360750585040L;

	public SiteBuildException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiteBuildException(String message) {
		super(message);
	}

	public SiteBuildException(Throwable cause) {
		super(cause);
	}

}
