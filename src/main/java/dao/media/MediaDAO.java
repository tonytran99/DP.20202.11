package dao.media;

import entity.db.AIMSDB;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
//Fuctional cohension do cac phuong thuc lien quan ho tro thao tac DAO
public class MediaDAO {

    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("quantity"),
                    res.getString("category"),
                    res.getString("imageUrl"),
                    res.getInt("price"),
                    res.getString("type"));
            medium.add(media);
        }
        return medium;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media ;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            return new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("quantity"),
                    res.getString("category"),
                    res.getString("imageUrl"),
                    res.getInt("price"),
                    res.getString("type"));
        }
        return null;
    }
    // stamp coupling do truyá»�n vÃ  khÃ´ng sá»­ dá»¥ng háº¿t dá»¯ liá»‡u (tbname)
    // content coupling do Ä‘Ã£ thay Ä‘á»•i giÃ¡ trá»‹ cá»§a Ä‘á»‘i tÆ°á»£ng khÃ¡c vÃ  Ä‘á»ƒ á»Ÿ dáº¡ng public lÃ m cÃ³ thá»ƒ thay Ä‘á»•i giÃ¡ trá»‹ táº¡i báº¥t cá»© Ä‘Ã¢u khÃ´ng kiá»ƒm soÃ¡t Ä‘Æ°á»£c
    public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        if (value instanceof String){
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update Media set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
    }
}
