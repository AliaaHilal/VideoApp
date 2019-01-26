package com.aliaa.sampleappassignment.Database;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliaa.sampleappassignment.Features.PostFeatures.MainPostFragment;
import com.aliaa.sampleappassignment.MainActivity;
import com.aliaa.sampleappassignment.R;

/**
 * Created by Aliaa on 22/01/2019.
 */

public class ServiceGetPosts extends AsyncTask<String,Void,String> {
    private String res;



    @Override
    protected String doInBackground(String... params) {
        res = null;
        PutUtility put = new PutUtility();
        try {
            res = put.getData(" http://stg.api.fawasell.com/v1/posts?category=" +
                    params[0].toString() + "&page=" + params[1].toString() + "&limit=" + params[2].toString()
                    + "&app_id=" + params[3].toString() + "&app_secret=" + params[4].toString() +
                    "&signature=" + params[5].toString() + "&q=" + params[6].toString());
            Log.v("res", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
        }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

}
