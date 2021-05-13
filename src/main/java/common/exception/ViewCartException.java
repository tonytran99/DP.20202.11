package common.exception;;

/**
 * The ViewCartException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class ViewCartException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ViewCartException() {

	}
	// Data coupling do truyen su dung het du lieu
	public ViewCartException(String message) {
		super(message);
	}

}
