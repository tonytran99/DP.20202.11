package entity.payment;
//SOLID: vi pham nguyen ly OCP vi khi can them phuong thuc thanh toan moi phai sua truc tiep ma nguon trong class
public class PaymentTransaction {
	private String errorCode;
	private CardStrategy cardStrategy;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
    // data coupling do truyền và sử dụng hết dữ liệu	
	public PaymentTransaction(String errorCode, CardStrategy cardStrategy, String transactionId, String transactionContent, int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.cardStrategy = cardStrategy;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
