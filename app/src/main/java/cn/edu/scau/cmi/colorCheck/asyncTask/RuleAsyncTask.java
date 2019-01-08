package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.CheckActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;


public class RuleAsyncTask extends AsyncTask<String,Void,String> {
    private static List<Rule> allRule;
    private CheckActivity checkActivity;
    private Item selectItem;
    private HttpFinishedListener httpFinishedListener;

    public interface HttpFinishedListener {
        void doSomething(List<Rule> ruleList);
        void doNothing();
    }

    public RuleAsyncTask(CheckActivity checkActivity) {
        this.checkActivity = checkActivity;
    }


    public RuleAsyncTask(CheckActivity checkActivity, Item selectItem, HttpFinishedListener httpFinishedListener) {
        this.checkActivity = checkActivity;
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