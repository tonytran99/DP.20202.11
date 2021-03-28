package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

//Communicational Cohesion vi cac phuong thuc cung thuc hien tren lstCartItem
public class Cart {
	private static Cart cart;
    private List<CartItem> lstCartItem;// cÃ¯Â¿Â½c cart trong gi? hÃ¯Â¿Â½ng

    public static Cart getInstance() {
    	if(cart == null) cart = new Cart();
    	return cart;
    }
    
    private Cart() {
        lstCartItem = new ArrayList<>();
    }


    // data coupling do truyá»?n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public void addCartMedia(CartItem cm){
        lstCartItem.add(cm);
    }
    // data coupling do truyá»?n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public void removeCartMedia(CartItem cm){
        lstCartItem.remove(cm);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }
  
    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }
    // stamp coupling do chÃ¡Â»â€° dÃƒÂ¹ng phÃ†Â°Ã†Â¡ng thÃ¡Â»Â©c getId cÃ¡Â»Â§a Media 
    public CartItem checkMediaInCart(Media media){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
        }
        return null;
    }

}
