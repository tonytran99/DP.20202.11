package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {
	// content coupling do de du lieu o dang public dan den co the thay doi tu bat cu dau
    public static User mainUser;
    public static Cart cart = Cart.getInstance();     // using SINGLETON
    public static LocalDateTime expiredTime;

}
