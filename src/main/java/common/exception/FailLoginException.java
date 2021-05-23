package common.exception;

/**
 * @author
 */
public class FailLoginException extends AimsException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailLoginException() {
        super("ERROR: Fail to Login. Please try again!");
    }
}
