package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */
//SOLID: Vi phạm nguyên lý SRP do class chứa hàm dùng để thanh toán vừa chứa hàm để lấy ngày
//SOLID: Vi phạm nguyên lý OCP do phương thức payOrder sẽ phải thay đổi khi có thêm các kiểu thanh toán mới
// Functional cohension do cac phuong thuc lien quan control gio hang
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private CreditCard creditCard;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbankInterface;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link String String} represents the input date
	 * @return {@link String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
    // Data coupling do truyen va su dung het du lieu
	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(strs[0]);
			year = Integer.parseInt(strs[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = strs[0] + strs[1];

		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
    //Data coupling do truyen va su dung het du lieu
	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.creditCard  = new CreditCard(
					cardNumber,
					cardHolderName,
					getExpirationDate(expirationDate),
					Integer.parseInt(securityCode));


			this.interbankInterface = new InterbankSubsystem();
			interbankInterface.payOrder(creditCard, amount, contents);


			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	public void emptyCart(){
		// content coupling do thay doi bien toan cuc SessionInformaition
		SessionInformation.cart.emptyCart();
    }
}