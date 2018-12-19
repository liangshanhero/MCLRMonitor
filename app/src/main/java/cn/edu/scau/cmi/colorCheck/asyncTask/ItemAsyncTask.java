package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.toBeDone.ProjectListActivity;
import cn.edu.scau.cmi.colorCheck.activity.PictureCheckActivity;


import cn.edu.scau.cmi.colorCheck.adapter.SqlLiteProjectAdapter;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class ItemAsyncTask extends AsyncTask <String,Void,String>{
//    定义一个网络任务完成后的接口，在节目调用这个异步任务时，实现界面元素的值的改变！！！
    public interface HttpFinishedListener {
        void doSomething(List<Item> itemList);
        void doNothing();
    }

    private HttpFinishedListener httpFinishedListenerListener;
    private static List<Item> allItemList;
    private PictureCheckActivity pictureCheckActivity;
    private ProjectListActivity projectListActivity;
    SqlLiteProjectAdapter sqlLiteProjectAdapter;

    public ItemAsyncTask(PictureCheckActivity pictureCheckActivity, HttpFinishedListener httpFinishedListener) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.httpFinishedListenerListener = httpFinishedListener;
    }

    public ItemAsyncTask(ProjectListActivity projectListActivity, HttpFinishedListener httpFinishedListener) {
        this.projectListActivity=projectListActivity;
        this.httpFinishedListenerListener=httpFinishedListener;
    }

    public  static List<Item> getAllItemList(){return allItemList;}

    @Override
    protected String doInBackground(String... strings) {
        try {
//            TODO to be tested!!!利用新的简单的方法获取 HttpUtil.gainJsonResultFromServer("Item");
            Request request=HttpUtil.getGetRequest("Item");
            String responseString = HttpUtil.gainJsonResultFromServer(request);
            allItemList = new Gson().fromJson(responseString,new TypeToken<List<Item>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }

@Override
    protected void onPostExecute(String doInBackgroundResult){


//    sqlLiteProjectAdapter = new SqlLiteProjectAdapter(MySqlServiceAsyncTask.getAllItemList(),flag, this);
//    recyclerView.setAdapter(sqlLiteProjectAdapter);

        if(doInBackgroundResult.equals("exception")){
            httpFinishedListenerListener.doNothing();
        }else{
//            pictureCheckActivity.setProjectList(allItemList);
            httpFinishedListenerListener.doSomething(allItemList);

        }
    }
}