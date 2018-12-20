package cn.edu.scau.cmi.colorCheck.activity.toBeDone;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Feature;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class FeatureAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckActivity pictureCheckActivity;
    private File file;

    private List<Feature> allFeature;

    public FeatureAsyncTask(PictureCheckActivity activity,File file){
        this.file =file;
        this.pictureCheckActivity=activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String responseString = HttpUtil.gainJsonResultFromServer("Feature");
            allFeature = new Gson().fromJson(responseString,new TypeToken<List<Feature>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){

    }
}
