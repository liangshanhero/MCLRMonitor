package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;

public class FeatureAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckActivity pictureCheckActivity;
    private String fileName;

    public FeatureAsyncTask(PictureCheckActivity activity,String fileName){
        this.fileName=fileName;
        this.pictureCheckActivity=activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
//        TODO 获取特征


        return null;
    }
    @Override
    protected void onPostExecute(String result){

    }
}
