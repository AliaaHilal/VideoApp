package com.aliaa.sampleappassignment.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aliaa on 18/01/2019.
 */

public class Media {
    private String type;
    private String url;
    private String title;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public static Media fromJSONDictionary(JSONObject mediaObject)
    {
        Media item=new Media();
        try
        {
            item.type=mediaObject.getString("type");
            item.url=mediaObject.getString("url");
            item.title=mediaObject.getString("title");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return item;
    }
}
