package controller;

import java.util.List;

import entity.cart.Cart;
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
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CartItem checkMediaInCart(Media media){
    	// common coupling do sá»­ dá»¥ng biáº¿n toÃ n cá»¥c SessionIformation
    	 return SessionInformation.cart.checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List getListCartMedia(){
    	// common coupling do sá»­ dá»¥ng biáº¿n toÃ n cá»¥c SessionIformation
    	return SessionInformation.cart.getListMedia();
    }
}
