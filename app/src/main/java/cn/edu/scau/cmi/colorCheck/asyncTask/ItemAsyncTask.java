package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.ItemActivity;
import cn.edu.scau.cmi.colorCheck.activity.PictureCheckActivity;


import cn.edu.scau.cmi.colorCheck.adapter.SqlLiteProjectAdapter;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class ItemAsyncTask extends AsyncTask <String,Void,String>{
//    定义一个网络任务完成后的接口，在节目调用这个异步任务时，实现界面元素的值的改变！！！
    public interface HttpFinishedListener {
        void doSomething(List<Item> itemList);
        void doNothing();
    }

    private HttpFinishedListener httpFinishedListenerListener;
    private static List<Item> allItemList;
    private PictureCheckActivity pictureCheckActivity;
    private ItemActivity itemActivity;
    SqlLiteProjectAdapter sqlLiteProjectAdapter;

    public ItemAsyncTask(PictureCheckActivity pictureCheckActivity, HttpFinishedListener httpFinishedListener) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.httpFinishedListenerListener = httpFinishedListener;
    }

    public ItemAsyncTask(ItemActivity itemActivity, HttpFinishedListener httpFinishedListener) {
        this.itemActivity = itemActivity;
        this.httpFinishedListenerListener=httpFinishedListener;
    }

    public  static List<Item> getAllItemList(){return allItemList;}

    @Override
    protected String doInBackground(String... strings) {
        try {
            String responseString = HttpUtil.gainJsonResultFromServer("Item");
            allItemList = new Gson().fromJson(responseString,new TypeToken<List<Item>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }

@Override
    protected void onPostExecute(String doInBackgroundResult){
        if(doInBackgroundResult.equals("exception")){
            httpFinishedListenerListener.doNothing();
        }else{
            httpFinishedListenerListener.doSomething(allItemList);
        }
    }
}