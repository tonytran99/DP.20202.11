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
//SOLID: vi phạm nguyên lý DIP do 3 class DVD CD BOOK đã overide lại phương thức public getMediaByID của class MediaDAO
//SOLID: vi phạm nguyên lý SRP do class có nhiệm vụ là lấy Media theo ID và lấy Media File theo ID
//Fuctional cohension do cac phuong thuc lien quan ho tro thao tac DAO
public class MediaDAO {

    public List<Object> getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        ArrayList<Object> medium = new ArrayList<>();
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
    // data coupling do truyen va su dung het du lieu
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media where id = " + id;
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
    // stamp coupling
    // content coupling
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
