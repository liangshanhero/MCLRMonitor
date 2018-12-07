package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckResultActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Featureextramethod;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class CheckAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckResultActivity pictureCheckResultActivity;
    private File file;

    private List<Featureextramethod> allFeature;

    public CheckAsyncTask(PictureCheckResultActivity activity, File file){
        this.file =file;
        this.pictureCheckResultActivity =activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String responseString = HttpUtil.gainJsonResultFromServer("Feature");
            allFeature = new Gson().fromJson(responseString,new TypeToken<List<Featureextramethod>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){

    }
}
