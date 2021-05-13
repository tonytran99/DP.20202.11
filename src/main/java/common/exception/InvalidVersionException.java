package common.exception;;

public class InvalidVersionException extends PaymentException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
	}
}
