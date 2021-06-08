package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.*;



/**
 * @author
 */
//SOLID: Vi phạm nguyên lý SRP vì vừa login logout lại vừa có hàm dùng để mã hóa ở đây, dẫn đến class có nhiều lý do để thay đổi
// Temporal cohesion do cac phuong thuc thuc hien theo thu tu khi login
public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }
    public User getMainUser() throws ExpiredSessionException {

    	// common coupling do truy cập trực tiếp và0 các dữ liệu của sessionInformation
        if (isLogin()) {
            logout();
            throw new ExpiredSessionException();
         // common coupling do truy cập trực tiếp và0 các dữ liệu của sessionInformation
        } else return SessionInformation.mainUser.cloneInformation();
    }

    public boolean isLogin(){
        boolean isNullMainUser = SessionInformation.mainUser == null;
        boolean isNullExpiredTime = SessionInformation.expiredTime == null;
        boolean isExpiredTime = SessionInformation.expiredTime.isBefore(LocalDateTime.now());
        return isNullMainUser || isNullExpiredTime || isExpiredTime;
    }


    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, utils.Encryption.md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            // common coupling do truy cập trực tiếp và0 các dữ liệu của sessionInformation
            SessionInformation.mainUser = user;									// content coupling do thay đổi dữ liệu của SessionInformation
            SessionInformation.expiredTime = LocalDateTime.now().plusHours(24); // content coupling do thay đổi dữ liệu của SessionInformation
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
    	// common coupling do truy cập trực tiếp vào các dữ liệu của sessionInformation
        SessionInformation.mainUser = null;		// content coupling do thay đổi dữ liệu của SessionInformation
        SessionInformation.expiredTime = null;// content coupling do thay đổi dữ liệu của SessionInformation
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    
//    private String md5(String message) {
//        String digest = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
//            // converting byte array to Hexadecimal String
//            StringBuilder sb = new StringBuilder(2 * hash.length);
//            for (byte b : hash) {
//                sb.append(String.format("%02x", b & 0xff));
//            }
//            digest = sb.toString();
//        } catch (NoSuchAlgorithmException ex) {
//            Utils.getLogger(Utils.class.getName());
//            digest = "";
//        }
//        return digest;
//    }

}
