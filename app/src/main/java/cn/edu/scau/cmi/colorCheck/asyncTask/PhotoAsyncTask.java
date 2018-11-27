package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//相片异步上传到服务器中。上传成功后在通知栏中显示消息

public class PhotoAsyncTask extends AsyncTask  <Void,Void,String>{
    private MainActivity mainActivity;
    private PictureCheckActivity pictureCheckActivity;


    public PhotoAsyncTask(MainActivity mainActivity) {
        this.mainActivity =mainActivity;
    }

    public PhotoAsyncTask(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpUtil.uploadMultiFile();
//        String address=null;
//        Callback callback=null;
//        Map<String, String> map=new HashMap<>();
////   两种方式上传图片，这是第一种；
//        HttpUtil.postFile( callback, map);
////   两种方式上传图片，这是第二种；
//        HttpUtil.postFile2Server();
        return "后台检测一下，看是否成功上传图片文件";
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