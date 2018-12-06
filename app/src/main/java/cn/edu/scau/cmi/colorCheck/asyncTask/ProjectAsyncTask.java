package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.ProjectListActivity;
import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;


import cn.edu.scau.cmi.colorCheck.adapter.ProjectAdapter;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class ProjectAsyncTask extends AsyncTask <String,Void,String>{
//    定义一个网络任务完成后的接口，在节目调用这个异步任务时，实现界面元素的值的改变！！！
    public interface HttpFinishedListener {
        void doSomething(List<Project> projectList);
        void doNothing();
    }

    private HttpFinishedListener httpFinishedListenerListener;
    private static List<Project> allProject;
    private PictureCheckActivity pictureCheckActivity;
    private ProjectListActivity projectListActivity;
    ProjectAdapter projectAdapter;

    public ProjectAsyncTask(PictureCheckActivity pictureCheckActivity,HttpFinishedListener httpFinishedListener) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.httpFinishedListenerListener = httpFinishedListenerListener;
    }

    public ProjectAsyncTask(ProjectListActivity projectListActivity,HttpFinishedListener httpFinishedListener) {
        this.projectListActivity=projectListActivity;
    }

    public  static List<Project> getAllProject(){return  allProject;}

    @Override
    protected String doInBackground(String... strings) {
        try {
            Request request=HttpUtil.getGetRequest("Project");
            String responseString = HttpUtil.gainJsonResultFromServer(request);
            allProject = new Gson().fromJson(responseString,new TypeToken<List<Project>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }

//TODO 利用该方法，可以更新界面的内容。
@Override
    protected void onPostExecute(String doInBackgroundResult){
        if(doInBackgroundResult.equals("exception")){
            httpFinishedListenerListener.doNothing();
        }else{
            httpFinishedListenerListener.doSomething(allProject);
        }
    }
}