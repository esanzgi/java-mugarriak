package model;

import java.io.File;
import java.sql.Date;

public class Picture {
    private int pictureId;
    private String title;
    private Date date;
    private String file;
    private int visits;
    private int photographerId;

    public Picture(int pictureId, String title,Date date , String file, int visits, int photographerId) {
        this.pictureId = pictureId;
        this.title = title;
        this.date = date;
        this.file = file;
        this.visits = visits;
        this.photographerId = photographerId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }
}
