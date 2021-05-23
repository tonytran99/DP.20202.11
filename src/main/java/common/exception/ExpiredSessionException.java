package common.exception;

/**
 * @author
 */
public class ExpiredSessionException extends AimsException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpiredSessionException() {
        super("ERROR: Your session has expired. Please login again!");
    }
}
//public class