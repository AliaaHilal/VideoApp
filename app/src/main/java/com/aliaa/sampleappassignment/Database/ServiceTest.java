package com.aliaa.sampleappassignment.Database;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Aliaa on 21/01/2019.
 */

public class ServiceTest extends AsyncTask<String,Void,String> {
private String res;
    @Override
    protected String doInBackground(String... params) {
        res = null;
        PutUtility put = new PutUtility();
        try {
            res = put.getData("http://stg.api.fawasell.com/v1/test?app_id="+params[0].toString()+"&app_secret="+params[1].toString());
            Log.v("res", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
