package common.exception;;

/**
 * The InvalidDeliveryInfoException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class InvalidDeliveryInfoException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public InvalidDeliveryInfoException() {

	}
	// data coupling do truyền sử dụng hết dữ liệu
	public InvalidDeliveryInfoException(String message) {
		super(message);
	}

}