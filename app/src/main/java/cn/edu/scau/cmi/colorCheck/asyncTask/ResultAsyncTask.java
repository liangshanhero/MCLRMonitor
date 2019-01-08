package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.CheckResultActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Feature;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Picture;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Result;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rgb;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class ResultAsyncTask extends AsyncTask<Void,Void,String> {
    private CheckResultActivity checkResultActivity;

    private String pictureName;
    private List<Picture> pictures;
    private Picture picture;
    private List<Rgb> rgbs;
    private List<Feature> features;
    private List<Result> results;

    public ResultAsyncTask(CheckResultActivity activity, String pictureName){
        this.pictureName =pictureName;
        this.checkResultActivity =activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
//TODO TODO TODO            Pictures 对象没有封装成功！！！！！！原因不明
//            String responsePictureString = HttpUtil.gainJsonResultFromServer("Result/pictures/"+ pictureName);
//            pictures= new Gson().fromJson(responsePictureString,new TypeToken<List<Picture>>(){}.getType());
//            System.out.println("在ResultAsyncTask.java中获取的图片对象的数量是："+pictures.size());

            System.out.print("ResultAsyncTask中的检测的文件名是："+pictureName);
            String responseRgbsString = HttpUtil.gainJsonResultFromServer("Result/rgbs/"+ pictureName);
            rgbs = new Gson().fromJson(responseRgbsString,new TypeToken<List<Rgb>>(){}.getType());

            String responseFeaturesString = HttpUtil.gainJsonResultFromServer("Result/features/"+ pictureName);
            features= new Gson().fromJson(responseFeaturesString,new TypeToken<List<Feature>>(){}.getType());
//
            String responseResultsString = HttpUtil.gainJsonResultFromServer("Result/results/"+ pictureName);
            results= new Gson().fromJson(responseResultsString,new TypeToken<List<Result>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){
//        将结果check返回到activiey,每个不同的检测名称只有一个检测，因此这个集合数量是1个。
//        Picture picture = pictures.get(0);
//        picture=pictures.get(0);
//        TODO 20181219 结果的处理方式
//        checkResultActivity.getResultTextView().setText(picture.getResults().getResult().toString());//如果没有检测，这个值就是默认的输入的-1.
//        checkResultActivity.setPicture(picture);
        checkResultActivity.setRgbs(rgbs);
        checkResultActivity.setFeatures(features);
        checkResultActivity.setResults(results);
        //非UI线程重新绘制界面，晕死，写错了我view的名称，折腾了一个晚上。
        checkResultActivity.getCheckResultFigureView().postInvalidate();
    }
}
