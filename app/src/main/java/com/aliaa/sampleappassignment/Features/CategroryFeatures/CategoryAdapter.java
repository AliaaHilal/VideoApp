package com.aliaa.sampleappassignment.Features.CategroryFeatures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliaa.sampleappassignment.R;
import com.aliaa.sampleappassignment.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aliaa on 21/01/2019.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    private List<Category> categoryList;
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
        categoryList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View categoryItemView= LayoutInflater.from(getContext()).inflate(R.layout.categories_list_item,null);
        TextView txtv_name=categoryItemView.findViewById(R.id.category_name_txt);
        txtv_name.setText(categoryList.get(position).getName());
        ImageView imgv_category = categoryItemView.findViewById(R.id.category_image);
        Picasso.get().load(categoryList.get(position).getImage()).into(imgv_category);
       /* ServiceImageDownloader serviceImageDownloader=new ServiceImageDownloader();
        serviceImageDownloader.execute(categoryList.get(position).getImage());
        Bitmap image= null;
        try {
            image = (Bitmap) serviceImageDownloader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       imgv_category.setImageBitmap(image);
*/
       return categoryItemView;
    }
}
