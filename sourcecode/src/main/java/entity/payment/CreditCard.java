package entity.payment;

/**
 * @author
 */
public class CreditCard implements CardStrategy  {

    private String cardCode;
    private String owner;
    private String dateExpired;
    // Nen de dang private neu khong cac module con co the truy cap truc tiep va thay doi gia tri
    protected int cvvCode;
    // data coupling do truyen va su dung het du lieu
    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
