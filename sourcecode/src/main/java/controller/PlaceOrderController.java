package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.invoice.WaitingInvoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import org.example.DistanceCalculator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place order usecase in our AIMS project

 * @author nguyenlm
 */

//SOLID: Vi pham nguyen ly SRP do class chua nhieu ham validate
//SOLID: Vi pham nguyen ly OCP do phuong thuc validateDeliveryInfo thay doi khi info nguoi dung thay doi
//SOLID: vi pham nguyen ly OCP OCP do phuong thuc processDeliveryInfo
// logical cohesion, cac phuong thuc validate nhu validateDeliveryInfo , validatePhoneNumber, validateName, validateAddress can duoc tach rieng vao mot lop
public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());
    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
    	// common coupling do su dung bien toan cuc SessionIformation
    	SessionInformation.cart.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
    	// common coupling do su dung bien toan cuc SessionIformation
    	return new Order(SessionInformation.cart);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    // Data coupling do truyen va su dung het du lieu
    public Invoice createInvoice(Order order) {
        return new Invoice(order, new WaitingInvoice());
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    //Data coupling do truyen va su dung het du lieu
    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    //stamp coupling
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        boolean isValidatePhoneNumber = utils.Validate.validatePhoneNumber(info.get("phone"));
        boolean isValidateName = utils.Validate.validateName(info.get("name"));
        boolean isValidateAddress = utils.Validate.validateAddress(info.get("address"));
//        boolean isValidateName = validateName(info.get("name"));
//        boolean isValidateAddress = validateAddress(info.get("address"));
        if (isValidatePhoneNumber|| isValidateName || isValidateAddress) return;
        else throw new InvalidDeliveryInfoException();
    }


    // 3 function validate đã được chuyển đến utils để sử dụng
//    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
//    public boolean validatePhoneNumber(String phoneNumber) {
//        if (phoneNumber.length() != 10 || !phoneNumber.startsWith("0")) return false;
//        try {
//            Integer.parseInt(phoneNumber);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        return true;
//    }
//    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
//    public boolean validateName(String name) {
//        if (Objects.isNull(name)) return false;
//        String patternString = "^[a-zA-Z\\s]*$";
//        Pattern pattern = Pattern.compile(patternString);
//        Matcher matcher = pattern.matcher(name);
//        return matcher.matches();
//    }
//    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
//    public boolean validateAddress(String address) {
//        if (Objects.isNull(address)) return false;
//        String patternString = "^[a-zA-Z\\s]*$";
//        Pattern pattern = Pattern.compile(patternString);
//        Matcher matcher = pattern.matcher(address);
//        return matcher.matches();
//    }
}
