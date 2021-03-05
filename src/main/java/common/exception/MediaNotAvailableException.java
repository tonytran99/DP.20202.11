package common.exception;;

/**
 * The MediaNotAvailableException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class MediaNotAvailableException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public MediaNotAvailableException() {

	}
	// data coupling do truyền sử dụng hết dữ liệu
	public MediaNotAvailableException(String message) {
		super(message);
	}

}