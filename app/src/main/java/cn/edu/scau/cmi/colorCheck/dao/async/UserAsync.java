package cn.edu.scau.cmi.colorCheck.dao.async;
//异步获取后台数据，
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.User;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class UserAsync extends AsyncTask<String,Void,String> {
    private MainActivity mainActivity;
    private static List<User> userList;

    public UserAsync(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public static String getUsers() {
        return userList.toString();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
//            Request request = new Request.Builder().url("http://139.159.188.31:8080/colorCheckServer/User").build();
            Request request=HttpUtil.getGetRequest("User");
            System.out.println(request.toString());
            Log.e("准备测试的地址：",request.toString());
            String responseString = HttpUtil.getJsonDataFromWeb(request);
            userList = new Gson().fromJson(responseString,new TypeToken<List<User>>(){}.getType());
            return "获取数据成功";
        } catch (Exception e) {
            return "错误";
        }
    }
}