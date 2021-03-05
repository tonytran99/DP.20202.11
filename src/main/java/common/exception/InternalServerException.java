package common.exception;

public class InternalServerException extends PaymentException {
	// data coupling do truyền sử dụng hết dữ liệu
    public InternalServerException(String message) {
        super(message);
    }
}
