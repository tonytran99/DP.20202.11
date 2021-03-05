package common.exception;;

/**
 * The ProcessInvoiceException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
public class ProcessInvoiceException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ProcessInvoiceException() {

	}
	// data coupling do truyền sử dụng hết dữ liệu
	public ProcessInvoiceException(String message) {
		super(message);
	}

}
