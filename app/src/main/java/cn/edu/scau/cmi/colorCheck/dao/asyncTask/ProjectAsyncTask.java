package cn.edu.scau.cmi.colorCheck.dao.asyncTask;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;


import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class ProjectAsyncTask extends AsyncTask <String,Void,String>{
    private static List<Project> allProject;
    private PictureCheckActivity pictureCheckActivity;

    public ProjectAsyncTask(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }

    public  static List<Project> getAllProject(){return  allProject;}

    @Override
    protected String doInBackground(String... strings) {
        try {
            Request request=HttpUtil.getGetRequest("Project");
            String responseString = HttpUtil.getJsonDataFromWeb(request);
            System.out.println("ProjectAsyncTask的响应字符串是："+responseString);
            allProject = new Gson().fromJson(responseString,new TypeToken<List<Project>>(){}.getType());
            return "获取数据成功";
        } catch (Exception e) {
            return "错误";
        }
    }

//利用该方法，可以更新界面的内容，更简单的方法
    protected void onPostExecute(String str){
        System.out.println("异步任务完成后所获取的所有的项目是："+allProject.toString());
        TextView textView=pictureCheckActivity.findViewById(R.id.picture_check_random);
        textView.setText("异步任务完成后所获取的所有的项目是："+allProject.toString());
    }
}

