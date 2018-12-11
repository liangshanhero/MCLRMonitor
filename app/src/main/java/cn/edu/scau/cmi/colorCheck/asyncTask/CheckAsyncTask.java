package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.PictureCheckResultActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Check;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class CheckAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckResultActivity pictureCheckResultActivity;
    private String  checkName;

    private List<Check> allCheck;

    public CheckAsyncTask(PictureCheckResultActivity activity, String checkName){
        this.checkName =checkName;
        this.pictureCheckResultActivity =activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String postfixUrl="Check/checkName/"+checkName;
            Request request=HttpUtil.getGetRequest(postfixUrl);
            String responseString = HttpUtil.gainJsonResultFromServer("Check/bitmap/"+checkName);
            allCheck = new Gson().fromJson(responseString,new TypeToken<List<Check>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){
//        将结果check返回到activiey,每个不同的检测名称只有一个检测，因此这个集合数量是1个。
        Check check=allCheck.get(0);
        pictureCheckResultActivity.getResultTextView().setText(check.getResult().toString());//如果没有检测，这个值就是默认的输入的-1.
        pictureCheckResultActivity.setCheck(check);
        //非UI线程重新绘制界面，晕死，写错了我view的名称，折腾了一个晚上。
        pictureCheckResultActivity.getCheckResultFigureView().postInvalidate();
    }
}
