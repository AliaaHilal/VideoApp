package com.aliaa.sampleappassignment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.aliaa.sampleappassignment.Database.DataBaseManager;
import com.aliaa.sampleappassignment.Features.CategroryFeatures.CategoryFragment;
import com.aliaa.sampleappassignment.Features.PostFeatures.MainPostFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private   ImageButton imgbtnMain,imgbtnCategory,imgbtnAccount,imgbtnConfig;
    public static List<ImageButton> controls;
    private FragmentManager fragmentManager ;
    private FragmentTransaction fragmentTransaction;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findControls();
        setActiveControl(0);
        setClickListener();
        dataBaseManager = new DataBaseManager();
        loadMainPosts(1);
    }

    private void setClickListener() {
        imgbtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMainPosts(1);
            }
        });
        imgbtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCategories();
            }
        });
    }

    private void loadCategories() {
        setActiveControl(1);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        CategoryFragment categoryFragment = new CategoryFragment();
        fragmentTransaction.replace(R.id.frame_layout, categoryFragment);
        fragmentTransaction.commit();
    }

    private void loadMainPosts(int categoryId) {
        setActiveControl(0);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        MainPostFragment mainPostFragment = new MainPostFragment();
        mainPostFragment.setCategoryId(categoryId);
        fragmentTransaction.replace(R.id.frame_layout, mainPostFragment);
        fragmentTransaction.commit();
    }


    private void setActiveControl(int i) {
        for (ImageButton item:controls) {
          item.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.gray3));
        }
        controls.get(i).setColorFilter(ContextCompat.getColor(getApplicationContext(),
                R.color.colorHeader));

    }

    private void findControls() {
        controls=new ArrayList<ImageButton>();
        imgbtnMain=findViewById(R.id.imgbtn_main);
        controls.add(imgbtnMain);
        imgbtnCategory=findViewById(R.id.imgbtn_categories);
        controls.add(imgbtnCategory);
        imgbtnAccount=findViewById(R.id.imgbtn_account);
        controls.add(imgbtnAccount);
        imgbtnConfig=findViewById(R.id.imgbtn_config);
        controls.add(imgbtnConfig);
    }

}
