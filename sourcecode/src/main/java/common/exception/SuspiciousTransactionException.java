package common.exception;;

public class SuspiciousTransactionException extends PaymentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
	}
}
