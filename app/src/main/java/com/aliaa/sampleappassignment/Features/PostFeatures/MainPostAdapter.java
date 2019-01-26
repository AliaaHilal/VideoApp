package com.aliaa.sampleappassignment.Features.PostFeatures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliaa.sampleappassignment.R;
import com.aliaa.sampleappassignment.model.Post;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Aliaa on 22/01/2019.
 */

public class MainPostAdapter extends ArrayAdapter<Post> {
    private List<Post> posts;
    private ImageView imgv_post,imgv_user;
    private TextView txtv_caption,txtv_user_name,txtv_like_count,txtv_comment_count;
    private Post currentPost;
    public MainPostAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        posts=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItemView= LayoutInflater.from(getContext()).inflate(R.layout.posts_list_item,null);
        findViews(postItemView);
        currentPost=posts.get(position);
        Picasso.get().load(currentPost.getMediaArrayList().get(1).getUrl()).into(imgv_post);
        //retriveVideoFrameFromVideo(imgv_post,currentPost.getUrl());
        Picasso.get().load(currentPost.getMediaArrayList().get(0).getUrl()).into(imgv_user);
        txtv_caption.setText(currentPost.getCaption());
        txtv_user_name.setText(currentPost.getUserName());
        txtv_like_count.setText(String.valueOf(currentPost.getLikeCount()/1000)+"K");
        txtv_comment_count.setText(String.valueOf(currentPost.getCommentCount()));
        return postItemView;
    }

    private void findViews(View postItemView) {
        imgv_post=postItemView.findViewById(R.id.post_image);
        txtv_caption=postItemView.findViewById(R.id.caption_txt);
        imgv_user=postItemView.findViewById(R.id.user_image);
        txtv_user_name=postItemView.findViewById(R.id.user_name_txt);
        txtv_like_count=postItemView.findViewById(R.id.like_count_txt);
        txtv_comment_count=postItemView.findViewById(R.id.comment_count_txt);
    }
    public  void retriveVideoFrameFromVideo(ImageView view,String videoUrl)
    {

        try
        {
            String  videoId=extractYoutubeId(videoUrl);

            Log.e("VideoId is->","" + videoId);

            String img_url="http://img.youtube.com/vi/"+videoId+"/0.jpg"; // this is link which will give u thumnail image of that video

            // picasso jar file download image for u and set image in imagview
            Picasso.get()
                    .load(img_url)
                    .fit()
                    .into(view);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

}
