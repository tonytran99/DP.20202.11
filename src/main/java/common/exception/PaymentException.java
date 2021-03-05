package common.exception;;

public class PaymentException extends RuntimeException {
	// data coupling do truyền sử dụng hết dữ liệu
	public PaymentException(String message) {
		super(message);
	}
}
