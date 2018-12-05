package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.os.Bundle;
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
    public interface HttpFinished {
        void doSomething(List<Project> projectList);
        void doNothing();
    }

    private HttpFinished httpFinishedListener;
    private static List<Project> allProject;
    private PictureCheckActivity pictureCheckActivity;
    private ProjectListActivity projectListActivity;
    ProjectAdapter projectAdapter;

    public ProjectAsyncTask(PictureCheckActivity pictureCheckActivity,HttpFinished httpFinished) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.httpFinishedListener=httpFinishedListener;
    }

    public ProjectAsyncTask(ProjectListActivity projectListActivity,HttpFinished httpFinished) {
        this.projectListActivity=projectListActivity;
    }

    public  static List<Project> getAllProject(){return  allProject;}

    @Override
    protected String doInBackground(String... strings) {
        try {
            Request request=HttpUtil.getGetRequest("Project");
            String responseString = HttpUtil.gainJsonResultFromServer(request);
            allProject = new Gson().fromJson(responseString,new TypeToken<List<Project>>(){}.getType());
            return "获取数据成功";
        } catch (Exception e) {
            return "错误";
        }
    }

//TODO 利用该方法，可以更新界面的内容。
@Override
    protected void onPostExecute(String str){


    httpFinishedListener.doSomething(allProject);


//TODO        判断是哪个activity调用了这个类，根据不同的来源，返回到不同的界面
    if("获取数据成功".equals(str)){
        if(pictureCheckActivity!=null){
        }
        if(projectListActivity!=null){
//            后台异步处理界面的内容，是否可以在页面起作用呢？
//            projectListActivity.setProjectAdapter();
//         项目适配器通过AsyncTask获取。
//            ProjectAdapter projectAdapter = projectListActivity.getProjectAdapter();
//            projectAdapter = new ProjectAdapter(SqlLiteService.getAllProject(),flag, this);

            RecyclerView recyclerView = projectListActivity.getRecyclerView();
//            recyclerView.setAdapter(projectAdapter);

        }
    }
//        System.out.println("异步任务完成后所获取的所有的项目是："+allProject.toString());
//        TextView textView=pictureCheckActivity.findViewById(R.id.picture_check_project_asyncTask_result);
//        textView.setText("异步任务完成后所获取的所有的项目是："+allProject.toString());
    }


}