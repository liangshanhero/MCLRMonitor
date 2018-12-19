package cn.edu.scau.cmi.colorCheck.activity.toBeDone;
//异步获取后台数据，
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class UserAsyncTask extends AsyncTask<String,Void,String> {
    private MainActivity mainActivity;
    private static List<User> userList;

    public UserAsyncTask(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public static List<User> getUsers() {
        return userList;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Request request=HttpUtil.getGetRequest("User");
            String responseString = HttpUtil.gainJsonResultFromServer(request);
            userList = new Gson().fromJson(responseString,new TypeToken<List<User>>(){}.getType());
            return "获取数据成功";
        } catch (Exception e) {
            return "错误";
        }
    }
}