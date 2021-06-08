package common.exception;

public class NotEnoughTransactionInfoException extends PaymentException {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public NotEnoughTransactionInfoException() {
	super("ERROR: Not Enough Transcation Information");
}
}
