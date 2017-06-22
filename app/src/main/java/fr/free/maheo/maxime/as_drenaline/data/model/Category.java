package fr.free.maheo.maxime.as_drenaline.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class Category {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("image")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
