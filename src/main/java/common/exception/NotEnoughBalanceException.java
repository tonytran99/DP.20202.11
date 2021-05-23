package common.exception;

public class NotEnoughBalanceException extends PaymentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
