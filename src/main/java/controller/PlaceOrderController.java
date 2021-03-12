package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderItem;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;
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
// Logical cohension do co nhieu ham tuong tu nhau 

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
    	// common coupling do sÃ¡Â»Â­ dÃ¡Â»Â¥ng biÃ¡ÂºÂ¿n toÃƒÂ n cÃ¡Â»Â¥c SessionIformation
        SessionInformation.cartInstance.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
    	// common coupling do sÃ¡Â»Â­ dÃ¡Â»Â¥ng biÃ¡ÂºÂ¿n toÃƒÂ n cÃ¡Â»Â¥c SessionIformation
        return new Order(SessionInformation.cartInstance);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
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
    //stamp coupling do truyÃ¡Â»ï¿½n cÃ¡ÂºÂ£ Ã„â€˜Ã¡Â»â€˜i tÃ†Â°Ã†Â¡ng info vÃƒÂ  khÃƒÂ´ng sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
        || validateName(info.get("name"))
        || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
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
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    // data coupling do truyÃ¡Â»ï¿½n vÃƒÂ  sÃ¡Â»Â­ dÃ¡Â»Â¥ng hÃ¡ÂºÂ¿t dÃ¡Â»Â¯ liÃ¡Â»â€¡u
    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
