package fr.free.maheo.maxime.as_drenaline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class Actuality implements Serializable {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("author")
    private String author;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("image")
    private String imageUrl;

    @Expose
    @SerializedName("date")
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
