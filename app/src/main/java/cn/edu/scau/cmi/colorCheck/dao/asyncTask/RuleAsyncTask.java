package cn.edu.scau.cmi.colorCheck.dao.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;


public class RuleAsyncTask extends AsyncTask<String,Void,String> {
    private static List<Rule> allRule;
    private PictureCheckActivity pictureCheckActivity;

    public RuleAsyncTask(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }

    public  static List<Rule> getAllRules(){return  allRule;}

    @Override
    protected String doInBackground(String... strings) {
        try {
            Request request=HttpUtil.getGetRequest("Rule");
            String responseString = HttpUtil.getJsonDataFromWeb(request);
            allRule = new Gson().fromJson(responseString,new TypeToken<List<Rule>>(){}.getType());
            return "获取数据成功";
        } catch (Exception e) {
            return "错误";
        }
    }
}
