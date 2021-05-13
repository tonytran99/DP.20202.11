package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
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
//SOLID: vi pháº¡m nguyÃªn lÃ½ SRP do class chá»©a nhiá»�u hÃ m validate
//SOLID: vi pháº¡m nguyÃªn lÃ½ OCP do phÆ°Æ¡ng thá»©c validateDeliveryInfo thay Ä‘á»•i khi info ngÆ°á»�i dÃ¹ng thay Ä‘á»•i
//SOLID: vi pháº¡m nguyÃªn lÃ½ OCP do phÆ°Æ¡ng thá»©c processDeliveryInfo thay Ä‘á»•i khi thÃ´ng tin giao hÃ ng cÃ³ thÃªm hoáº·c giáº£m bá»›t Ä‘i cÃ¡c thuá»™c tÃ­nh
// Logical cohension do co nhieu ham tuong tu nhau 

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
    	// common coupling do sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng biÃƒÂ¡Ã‚ÂºÃ‚Â¿n toÃƒÆ’Ã‚Â n cÃƒÂ¡Ã‚Â»Ã‚Â¥c SessionIformation
    	SessionInformation.cart.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
    	// common coupling do sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng biÃƒÂ¡Ã‚ÂºÃ‚Â¿n toÃƒÆ’Ã‚Â n cÃƒÂ¡Ã‚Â»Ã‚Â¥c SessionIformation
    	return new Order(SessionInformation.cart);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    // data coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n vÃƒÆ’Ã‚Â  sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t dÃƒÂ¡Ã‚Â»Ã‚Â¯ liÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¡u
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    // data coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n vÃƒÆ’Ã‚Â  sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t dÃƒÂ¡Ã‚Â»Ã‚Â¯ liÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¡u
    public DeliveryInfo processDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
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
    //stamp coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n cÃƒÂ¡Ã‚ÂºÃ‚Â£ Ãƒâ€žÃ¢â‚¬ËœÃƒÂ¡Ã‚Â»Ã¢â‚¬Ëœi tÃƒâ€ Ã‚Â°Ãƒâ€ Ã‚Â¡ng info vÃƒÆ’Ã‚Â  khÃƒÆ’Ã‚Â´ng sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
        || validateName(info.get("name"))
        || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }
    // data coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n vÃƒÆ’Ã‚Â  sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t dÃƒÂ¡Ã‚Â»Ã‚Â¯ liÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¡u
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    // data coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n vÃƒÆ’Ã‚Â  sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t dÃƒÂ¡Ã‚Â»Ã‚Â¯ liÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¡u
    public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    // data coupling do truyÃƒÂ¡Ã‚Â»Ã¯Â¿Â½n vÃƒÆ’Ã‚Â  sÃƒÂ¡Ã‚Â»Ã‚Â­ dÃƒÂ¡Ã‚Â»Ã‚Â¥ng hÃƒÂ¡Ã‚ÂºÃ‚Â¿t dÃƒÂ¡Ã‚Â»Ã‚Â¯ liÃƒÂ¡Ã‚Â»Ã¢â‚¬Â¡u
    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
