package com.aliaa.sampleappassignment.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aliaa on 17/01/2019.
 */

public class Category {


    private int id;
    private String name;
    private String image;
    private String description;
    public Category()
    {

    }
    public Category(int id,String name,String image,String description)
    {
        this.id=id;
        this.name=name;
        this.image=image;
        this.description=description;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
    public static Category fromJSON(JSONObject categoryObject) {
        Category item = new Category();
        try {
            item.id = categoryObject.getInt("id");
            item.name = categoryObject.getString("name");
            item.image=categoryObject.getString("image");
            item.description=categoryObject.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
