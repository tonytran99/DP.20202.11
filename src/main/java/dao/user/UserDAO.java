package dao.user;

import entity.db.AIMSDB;
import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author
 */
public class UserDAO {
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public User authenticate(String email, String encryptedPassword) throws SQLException {
        String sql = "SELECT * FROM User " +
                "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";
        // common coupling do sá»­ dá»¥ng biáº¿n toÃ n cá»¥c AIMSDB
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
