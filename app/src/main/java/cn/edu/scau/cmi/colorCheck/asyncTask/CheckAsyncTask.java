package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckResultActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Check;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class CheckAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckResultActivity pictureCheckResultActivity;
    private File checkBitmapFile;

    private List<Check> allCheck;

    public CheckAsyncTask(PictureCheckResultActivity activity, File file){
        this.checkBitmapFile =file;
        this.pictureCheckResultActivity =activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String responseString = HttpUtil.gainJsonResultFromServer("Check/bitmap/"+checkBitmapFile.getName());
            allCheck = new Gson().fromJson(responseString,new TypeToken<List<Check>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){
//        TODO 结果返回给PcitureCheckResultActivity显示，？？？

    }
}
