package subsystem.interbank;
import entity.payment.CardStrategy;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

    // Stamp coupling : Truyen doi tuong CreditCard card nhung khong su dung
	public PaymentTransaction refund(CardStrategy cardStrategy , int amount, String contents) {
		return null;
	}

    // Stamp coupling : Truyen doi tuong CreditCard card nhung khong su dung
	public PaymentTransaction payOrder(CardStrategy cardStrategy, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(cardStrategy, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
