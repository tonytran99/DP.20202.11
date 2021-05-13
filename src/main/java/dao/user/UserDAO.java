package dao.user;

import entity.db.AIMSDB;
import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author
 */
public class UserDAO {
    // Data coupling do truyen va su dung het du lieu
    public User authenticate(String email, String encryptedPassword) throws SQLException {
        String sql = "SELECT * FROM User " +
                "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";
        // Common coupling do su dung bien toan cuc AIMSDB
        ResultSet res =  AIMSDB.getConnection().createStatement().executeQuery(sql);
        if(res.next()) {
            return new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("address"),
                    res.getString("phone")
            );
        } else {
            throw new SQLException();
        }
    }
}
