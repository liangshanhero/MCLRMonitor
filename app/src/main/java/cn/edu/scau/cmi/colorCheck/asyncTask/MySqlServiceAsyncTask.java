package cn.edu.scau.cmi.colorCheck.asyncTask;
//手机调用，从网络获取数据，必须使用Asynctask才能和界面交互
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.edu.scau.cmi.colorCheck.activity.ProjectListActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Request;

public class MySqlServiceAsyncTask<T> extends AsyncTask<Void,Void,String> {
    private ProjectListActivity projectListActivity;

    public MySqlServiceAsyncTask(ProjectListActivity projectListActivity){this.projectListActivity=projectListActivity;
    }


//TODO 测试泛型方法成功后，删除该方法
    public static List<Project> getAllProject() {
//        HttpUtil httpUtil=new HttpUtil();
        Set<Project> allProjectSet=HttpUtil.getAllProjectData("Project");
        List<Project> allProjectList=new ArrayList<Project>();
        allProjectList.addAll(allProjectSet);
        return  allProjectList;
    }
    //    TODO ***使用泛型得到所有的基本数据
    // 例如：如果需要的是Project数据，就用Project
    //
    // ***
    public  List<T> getAllDate(T requiredDataType) {
        HttpUtil httpUtil=new HttpUtil();
        Set<T> allRequiredTypeDataSet=httpUtil.getAllRequiredTypeData(requiredDataType.getClass().getName());
        List<T> allRequiredTypeDataList=new ArrayList<T>();
        allRequiredTypeDataList.addAll(allRequiredTypeDataSet);
        return  allRequiredTypeDataList;
    }

    @Override
    protected String doInBackground(Void... voids) {

//        获取数据请求的


        Request request=HttpUtil.getGetRequest("Project");
        String responseString = null;
        try {
            responseString = HttpUtil.gainJsonResultFromServer(request);
//            allRule = new Gson().fromJson(responseString,new TypeToken<List<Rule>>(){}.getType());
            return "获取数据成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "获取数据成功";

    }

    protected void onPostExecute(String str){

    }


}