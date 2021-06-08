package controller;

import java.util.List;

import entity.cart.CartItem;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */

public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    // data coupling do truyen va su dung het du lieu
    public CartItem checkMediaInCart(int idMedia){
    	// common coupling do su dung bien toan cuc SessionIformation
        return SessionInformation.cart.checkMediaInCart(idMedia);

    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List<?> getListCartMedia(){
    	// common coupling do su dung bien toan cuc SessionIformation
    	return SessionInformation.cart.getListMedia();
    }
}
