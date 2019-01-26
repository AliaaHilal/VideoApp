package com.aliaa.sampleappassignment.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by Aliaa on 19/01/2019.
 */

class ServiceGetCategories extends AsyncTask<String, Void, String> {
    public String res;
    private Context context;
    private ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       /* progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();*/
    }

    @Override
    protected String doInBackground(String... params) {
        res = null;
        PutUtility put = new PutUtility();
        try {
            res = put.getData("http://stg.api.fawasell.com/v1/categories?app_id="+
                    params[0].toString()+"&app_secret="+params[1].toString()+"&signature="+params[2].toString());
            Log.v("res", res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    protected void onPostExecute(String res)
    {
       /* progressDialog.dismiss();*/
    }

}
