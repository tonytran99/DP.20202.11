package common.exception;

public class InternalServerException extends PaymentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Data coupling do truyen su dung het du lieu
    public InternalServerException(String message) {
        super(message);
    }
}
