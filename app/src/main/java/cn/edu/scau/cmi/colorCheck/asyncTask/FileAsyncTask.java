package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;

import cn.edu.scau.cmi.colorCheck.activity.CheckActivity;
import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
//相片异步上传到服务器中，并插入到相应的表格中。

public class FileAsyncTask extends AsyncTask  <Void,Void,String>{
    private MainActivity mainActivity;
    private CheckActivity checkActivity;
    private SharedPreferences sharePreferences;
    private File file;


    public FileAsyncTask(MainActivity mainActivity) {
        this.mainActivity =mainActivity;
    }

    public FileAsyncTask(CheckActivity checkActivity) {
        this.checkActivity = checkActivity;
    }

    public FileAsyncTask(CheckActivity checkActivity, SharedPreferences sharePreferences, File file) {
        this.checkActivity = checkActivity;
        this.sharePreferences=sharePreferences;
        this.file =file;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            if(null!= file){
                HttpUtil.uploadFileToServer(file);//上传指定的文件

            }else {
                HttpUtil.uploadAllBitmapInColorCheckDirectory(sharePreferences);//上传所有的没有上传的文件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "请登录到服务器后台，检测否成功上传图片文件";
    }

    @Override
    protected void onPostExecute(String result){
        if(null==mainActivity){
            Toast.makeText(checkActivity, result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mainActivity, result, Toast.LENGTH_SHORT).show();
        }
    }
}