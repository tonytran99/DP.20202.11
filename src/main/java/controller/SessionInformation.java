package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
public class SessionInformation {
	// content coupling do để dữ liệu ở dạng public dẫn đến có thể truy cập từ bất cứ đâu là thay đổi
    public static User mainUser;
    public static Cart cartInstance = new Cart();
    public static LocalDateTime expiredTime;

}
