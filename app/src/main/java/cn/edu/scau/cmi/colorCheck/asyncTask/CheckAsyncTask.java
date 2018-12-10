package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckResultActivity;
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
//        将结果check返回到activiey
        Check check=allCheck.get(0);
        pictureCheckResultActivity.getResultTextView().setText(check.getResult().toString());
        pictureCheckResultActivity.setCheck(check);
        //非UI线程重新绘制界面，晕死，写错了我view的名称，折腾了一个晚上。
        pictureCheckResultActivity.getCheckResultFigureView().postInvalidate();
    }
}
