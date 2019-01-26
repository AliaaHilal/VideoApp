package com.aliaa.sampleappassignment.Features.CategroryFeatures;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.aliaa.sampleappassignment.AppStatus;
import com.aliaa.sampleappassignment.Database.DataBaseManager;
import com.aliaa.sampleappassignment.Features.PostFeatures.MainPostFragment;
import com.aliaa.sampleappassignment.MainActivity;
import com.aliaa.sampleappassignment.R;
import com.aliaa.sampleappassignment.model.Category;

import java.util.List;

import app.youkai.placeholdertextview.PlaceholderTextView;

/**
 * Created by Aliaa on 21/01/2019.
 */

public class CategoryFragment extends Fragment {
    DataBaseManager dataBaseManager;
    List<Category> allCategories;
    ListView lst_categories;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View categoryView=inflater.inflate(R.layout.categories_layout,null);
        lst_categories=categoryView.findViewById(R.id.categories_list);
        dataBaseManager=new DataBaseManager();
        if(AppStatus.getInstance(getContext()).isOnline()) {
            allCategories=dataBaseManager.getCategories();
            CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(),0,allCategories);
            lst_categories.setAdapter(categoryAdapter);
            categoryAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
        lst_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category item=allCategories.get(i);
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MainPostFragment mainPostFragment = new MainPostFragment();
                mainPostFragment.setCategoryId(item.getId());
                fragmentTransaction.replace(R.id.frame_layout, mainPostFragment);
                fragmentTransaction.commit();
                for (ImageButton imgItem: MainActivity.controls) {
                    imgItem.setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.gray3));
                }
                MainActivity.controls.get(0).setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),
                        R.color.colorHeader));
            }
        });
        loadNoCategories(categoryView);
        return categoryView;
    }
    void loadNoCategories(View fragment)
    {
        PlaceholderTextView placeholderTextView = fragment.findViewById(R.id.msg_no_categories);
        if(allCategories==null) {
            placeholderTextView.setText(R.string.no_internet);
            placeholderTextView.setVisibility(View.VISIBLE);

        }
        else
            if (allCategories.size() != 0)
                placeholderTextView.setVisibility(View.GONE);
        else
        placeholderTextView.setVisibility(View.VISIBLE);
    }

}
