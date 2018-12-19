package cn.edu.scau.cmi.colorCheck.activity.toBeDone;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.activity.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
//相片异步上传到服务器中。上传成功后在通知栏中显示消息

public class PhotoAsyncTaskToBeDeleteUseless extends AsyncTask  <Void,Void,String>{
    private MainActivity mainActivity;
    private PictureCheckActivity pictureCheckActivity;
    private SharedPreferences sharePreferences;
    private File file;


    public PhotoAsyncTaskToBeDeleteUseless(MainActivity mainActivity) {
        this.mainActivity =mainActivity;
    }

    public PhotoAsyncTaskToBeDeleteUseless(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }

    public PhotoAsyncTaskToBeDeleteUseless(PictureCheckActivity pictureCheckActivity, SharedPreferences sharePreferences, File file) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.sharePreferences=sharePreferences;
        this.file =file;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            if(null!= file){
                Log.e("----准备上传的文件是：------", file.getAbsolutePath());
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
            Toast.makeText(pictureCheckActivity, result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mainActivity, result, Toast.LENGTH_SHORT).show();
        }
    }
}