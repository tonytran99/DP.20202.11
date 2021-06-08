package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class DVD extends Media {
	// commom va content coupling do khong de thuoc tinh private
    String discType;
    String director;
    int runtime;
    String studio;
    String subtitles;
    Date releasedDate;
    String filmType;

    public DVD() throws SQLException{

    }
    // Data coupling do truyen va su dung het du lieu
    public DVD(int id, String title, String category, int price, int quantity, String type, String discType,
            String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitles = subtitles;
        this.releasedDate = releasedDate;
        this.filmType = filmType;
    }

    public String getDiscType() {
        return this.discType;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setDiscType(String discType) {
        this.discType = discType;
        return this;
    }

    public String getDirector() {
        return this.director;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setDirector(String director) {
        this.director = director;
        return this;
    }

    public int getRuntime() {
        return this.runtime;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setRuntime(int runtime) {
        this.runtime = runtime;
        return this;
    }

    public String getStudio() {
        return this.studio;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public String getSubtitles() {
        return this.subtitles;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setSubtitles(String subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getFilmType() {
        return this.filmType;
    }
    // Data coupling do truyen va su dung het du lieu
    public DVD setFilmType(String filmType) {
        this.filmType = filmType;
        return this;
    }

    @Override
    public String toString() {
        String basicInformation = getBasicInformation();
        return "{" + basicInformation + " discType='" + discType + "'" + ", director='" + director + "'" + ", runtime='"
                + runtime + "'" + ", studio='" + studio + "'" + ", subtitles='" + subtitles + "'" + ", releasedDate='"
                + releasedDate + "'" + ", filmType='" + filmType + "'" + "}";
    }
}
