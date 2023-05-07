package model;

import java.awt.*;

public class Photographer {
    private int photographerId;
    private String name;
    private Boolean awarded;

    public Photographer(int photographerId, String name, Boolean awarded) {
        this.photographerId = photographerId;
        this.name = name;
        this.awarded = awarded;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAwarded() {
        return awarded;
    }

    public void setAwarded(Boolean awarded) {
        this.awarded = awarded;
    }
}
