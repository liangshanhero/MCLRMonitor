package cn.edu.scau.cmi.colorCheck.dao.mysql;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class CheckAsyncTask extends AsyncTask<Void, Void, String> {
    private List<String> checkList;

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String jsonData = HttpUtil.getJsonDataFromWeb(HttpUtil.getGetRequest("/Check"));
            checkList = new Gson().fromJson(jsonData,new TypeToken<List<String>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}