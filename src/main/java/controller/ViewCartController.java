package controller;

import java.sql.SQLException;

import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 * @author nguyenlm
 */
// Logical cohesion 
public class ViewCartController extends BaseController{
    
    /**
     * This method checks the available products in Cart
     * @throws SQLException
     */
    public void checkAvailabilityOfProduct() throws SQLException{
    	// common coupling do sử dụng biến toàn cục SessionIformation
    	SessionInformation.cart.checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     * @return subtotal
     */
    public int getCartSubtotal(){
    	// common coupling do sử dụng biến toàn cục SessionIformation
    	int subtotal = SessionInformation.cart.calSubtotal();
        return subtotal;
    }

}
