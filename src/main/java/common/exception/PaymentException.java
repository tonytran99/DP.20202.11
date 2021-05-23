package common.exception;;

public class PaymentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Data coupling do truyen su dung het du lieu
	public PaymentException(String message) {
		super(message);
	}
}
