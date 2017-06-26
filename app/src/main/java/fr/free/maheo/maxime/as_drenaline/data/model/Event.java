package fr.free.maheo.maxime.as_drenaline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class Event {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("location")
    private String location;

    @Expose
    @SerializedName("date")
    private  String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
