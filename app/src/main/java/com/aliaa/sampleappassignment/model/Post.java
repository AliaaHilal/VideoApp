package com.aliaa.sampleappassignment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aliaa on 22/01/2019.
 */

public class Post {
    private int postId;
    private int categoryId;
    private String userName;
    private String name;
    private String caption;
    private String type;
    private ArrayList<Tag> tagArrayList;
    private String url;
    private ArrayList<Media> mediaArrayList;
    private int viewCount;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;
    private String coutryCode2;
    private int rankingScore;
    private long createdAt;

    public int getPostId() {
        return postId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Tag> getTagArrayList() {
        return tagArrayList;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<Media> getMediaArrayList() {
        return mediaArrayList;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getCoutryCode2() {
        return coutryCode2;
    }

    public int getRankingScore() {
        return rankingScore;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public static Post fromJSON(JSONObject postObject) {
        Post item = new Post();
        try {
            item.postId = Integer.valueOf(postObject.getString("id_post"));
            item.categoryId = Integer.valueOf(postObject.getString("category_id"));
            item.userName = postObject.getString("username");
            item.name=postObject.getString("name");
            item.caption=postObject.getString("caption");
            item.type=postObject.getString("type");
            item.tagArrayList=new ArrayList<Tag>();
            JSONArray tages= postObject.getJSONArray("tag");
            for(int i=0;i<tages.length();i++)
            {
                item.tagArrayList.add(Tag.fromJSONDictionary(tages.getJSONObject(i)));
            }
            item.url=postObject.getString("url");
            item.mediaArrayList=new ArrayList<>();
            JSONArray media= postObject.getJSONArray("media");
            for(int i=0;i<media.length();i++)
            {
                item.mediaArrayList.add(Media.fromJSONDictionary(media.getJSONObject(i)));
            }
            item.viewCount=postObject.getInt("view_count");
            item.likeCount=postObject.getInt("like_count");
            item.dislikeCount=postObject.getInt("dislike_count");
            item.commentCount=postObject.getInt("comment_count");
            item.coutryCode2=postObject.getString("coutrycode2");
            item.rankingScore=postObject.getInt("ranking_score");
            item.createdAt=postObject.getLong("created_at");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }




}
