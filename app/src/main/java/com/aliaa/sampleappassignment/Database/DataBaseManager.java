package com.aliaa.sampleappassignment.Database;

import android.view.View;

import com.aliaa.sampleappassignment.model.Category;
import com.aliaa.sampleappassignment.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Aliaa on 18/01/2019.
 */

public class DataBaseManager {
    private static final String APPId = "14378174";
    private static final String APPSecret = "cFJiGxZVTGf3rPNMK1o08WQWWpnr8M9RmZOyGxcM";
    private static final String HASHKEY = "diBIGrtVCAH00GtMVLupirbNdFEjooqk8YPJUtUw";
    private String Signature;


    private View view;
    public List<Category> getCategories() {
        ArrayList<Category> categoriesList = new ArrayList<Category>();
        String result = null;
        Signature = MD5(APPId + APPSecret + HASHKEY);
        ServiceGetCategories serviceGetCategories = new ServiceGetCategories();
        serviceGetCategories.execute(APPId, APPSecret, Signature);
        try {
            if (serviceGetCategories.get() != null)
                result = serviceGetCategories.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (result != null) {

            JSONArray array = null;
            try {
                JSONObject obj = new JSONObject(result);

                array = obj.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = null;
                    jsonObject = (JSONObject) array.get(i);
                    categoriesList.add(Category.fromJSON(jsonObject));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categoriesList;
    }


    public List<Post> getPosts(int category, int page, int limit, String querry) {
        ArrayList<Post> postsList = new ArrayList<Post>();
        String result = null;
        Signature = MD5(String.valueOf(category) + String.valueOf(page) + String.valueOf(limit) + APPId + APPSecret + querry + HASHKEY);
        ServiceGetPosts serviceGetPosts = new ServiceGetPosts();
        serviceGetPosts.execute(String.valueOf(category), String.valueOf(page), String.valueOf(limit),
                APPId, APPSecret, Signature, querry);
        try {
            if (serviceGetPosts.get() != null)
                result = serviceGetPosts.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (result != null) {

            JSONArray array = null;
            try {
                JSONObject obj = new JSONObject(result);

                array = obj.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = null;
                    jsonObject = (JSONObject) array.get(i);
                    postsList.add(Post.fromJSON(jsonObject));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return postsList;
    }

    public String get_Status() {

        String result = null;
        ServiceTest serviceTest = new ServiceTest();
        serviceTest.execute(APPId, APPSecret);
        try {
            if (serviceTest.get() != null)
                result = serviceTest.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (result != null) {
            try {
                JSONObject obj = new JSONObject(result);

                JSONObject obj2 = obj.getJSONObject("data");
                result = obj2.getString("echo");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }

    public void setView(View view) {
        this.view = view;
    }
}
