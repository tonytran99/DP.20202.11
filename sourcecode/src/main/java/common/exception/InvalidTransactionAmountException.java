package common.exception;;

public class InvalidTransactionAmountException extends PaymentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
