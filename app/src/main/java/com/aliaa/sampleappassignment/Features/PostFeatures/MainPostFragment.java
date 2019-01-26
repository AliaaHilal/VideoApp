package com.aliaa.sampleappassignment.Features.PostFeatures;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.aliaa.sampleappassignment.AppStatus;
import com.aliaa.sampleappassignment.Database.DataBaseManager;
import com.aliaa.sampleappassignment.R;
import com.aliaa.sampleappassignment.model.Post;

import java.util.List;

import app.youkai.placeholdertextview.PlaceholderTextView;

/**
 * Created by Aliaa on 22/01/2019.
 */

public class MainPostFragment extends Fragment {
    private DataBaseManager dataBaseManager;
    private List<Post> postsList;
    private ListView lst_posts;
    private int categoryId;
    private ImageView imgv_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View postView=inflater.inflate(R.layout.posts_main_layout,null);
        lst_posts=postView.findViewById(R.id.posts_list);
        //imgv_search=postView.findViewById(R.id.search_image);
        dataBaseManager=new DataBaseManager();
        if(AppStatus.getInstance(getContext()).isOnline()) {
            dataBaseManager.setView(postView);
            postsList=dataBaseManager.getPosts(categoryId,1,10,"");
            MainPostAdapter mainPostAdapter=new MainPostAdapter(getContext(),0,postsList);
            lst_posts.setAdapter(mainPostAdapter);
            mainPostAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();

       /* imgv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        loadNoPosts(postView);
        return postView;
    }
    void loadNoPosts(View fragment)
    {
        PlaceholderTextView placeholderTextView = fragment.findViewById(R.id.msg_no_posts);
        if(postsList==null) {
            placeholderTextView.setText(R.string.no_internet);
            placeholderTextView.setVisibility(View.VISIBLE);

        }
        else
        if (postsList.size() != 0)
            placeholderTextView.setVisibility(View.GONE);
        else
            placeholderTextView.setVisibility(View.VISIBLE);
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
