package cn.edu.scau.cmi.colorCheck.dao.mysql;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class MyHttpClient {
    public static String getUsers(String httpRequestString) {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(httpRequestString).get().build();//默认就是GET请求，可以不写
        try {
            Response  response= okHttpClient.newCall(request).execute();
            Log.e("从HttpClient中获取用户信息","成功，内容是："+response.body().string());
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;


    }
}
