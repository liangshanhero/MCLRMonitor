package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;


public class RuleAsyncTask extends AsyncTask<String,Void,String> {
    private static List<Rule> allRule;
    private PictureCheckActivity pictureCheckActivity;
    private Item selectItem;
    private HttpFinishedListener httpFinishedListener;

    public interface HttpFinishedListener {
        void doSomething(List<Rule> ruleList);
        void doNothing();
    }

    public RuleAsyncTask(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }


    public RuleAsyncTask(PictureCheckActivity pictureCheckActivity, Item selectItem, HttpFinishedListener httpFinishedListener) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.selectItem = selectItem;
        this.httpFinishedListener=httpFinishedListener;
    }


    public  static List<Rule> getAllRules(){return  allRule;}

    @Override
    protected String doInBackground(String... strings) {

        String ruleRequest;
        if(selectItem ==null) {//没有项目，表示查找到所有的规则
            ruleRequest = "Rule";
        }else {
            ruleRequest="Item/" + selectItem.getId() + "/rules";
        }

        try {
            String responseString = HttpUtil.gainJsonResultFromServer(ruleRequest);
            allRule = new Gson().fromJson(responseString,new TypeToken<List<Rule>>(){}.getType());
                return "获取数据成功";
            } catch (Exception e) {
                return "错误";
            }
        }



    protected void onPostExecute(String doInBackgroundResult){
        if(doInBackgroundResult.equals("exception")){
            httpFinishedListener.doNothing();
        }else{
            httpFinishedListener.doSomething(allRule);
        }
    }
}