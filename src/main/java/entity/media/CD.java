package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class CD extends Media {
	// commom vÃ  content coupling do Ä‘á»ƒ dá»¯ liá»‡u á»Ÿ dáº¡ng public Ä‘áº«n Ä‘áº¿n cÃ³ thá»ƒ láº¥y vÃ  thay Ä‘á»•i báº¥t cá»© lÃºc nÃ o
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;

    public CD() throws SQLException{

    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CD(int id, String title, String category, int price, int quantity, String type, String artist,
            String recordLabel, String musicType, Date releasedDate) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    public String getArtist() {
        return this.artist;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CD setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CD setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public String getMusicType() {
        return this.musicType;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CD setMusicType(String musicType) {
        this.musicType = musicType;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }
    // data coupling do truyá»�n vÃ  sá»­ dá»¥ng háº¿t dá»¯ liá»‡u
    public CD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    @Override
    public String toString() {
        String basicInformation = "{" +
                " id='" + id + "'" +
                ", title='" + title + "'" +
                ", category='" + category + "'" +
                ", price='" + price + "'" +
                ", quantity='" + quantity + "'" +
                ", type='" + type + "'" +
                ", imageURL='" + imageURL + "'" +
                "}";
        return "{" + basicInformation + " artist='" + artist + "'" + ", recordLabel='" + recordLabel + "'"
                + "'" + ", musicType='" + musicType + "'" + ", releasedDate='"
                + releasedDate + "'" + "}";
    }
}
