package entity.payment;

/**
 * @author
 */
public class CreditCard {

    private String cardCode;
    private String owner;
    private String dateExpired;
    // Nên để ở dạng private vì nếu không các modun con có thể truy cập trực tiếp và thay đổi giá trị
    protected int cvvCode;
    // data coupling do truyền và sử dụng hết dữ liệu
    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
