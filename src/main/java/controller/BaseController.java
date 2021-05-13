package controller;

import java.util.List;

import entity.cart.CartItem;
import entity.media.Media;

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
    // Data coupling do truyen su dung het du lieu
    public CartItem checkMediaInCart(Media media){
    	// common coupling do su dung bien toan cuc SessionIformation
    	 return SessionInformation.cart.checkMediaInCart(media);
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
