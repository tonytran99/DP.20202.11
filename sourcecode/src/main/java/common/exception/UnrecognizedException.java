package common.exception;;

public class UnrecognizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnrecognizedException() {
		super("ERROR: Something went wrowng!");
	}
}
