package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;
//相片异步上传到服务器中。上传成功后在通知栏中显示消息

public class PhotoAsyncTask extends AsyncTask  <Void,Void,String>{
    private MainActivity mainActivity;
    private PictureCheckActivity pictureCheckActivity;
    private SharedPreferences sharePreferences;


    public PhotoAsyncTask(MainActivity mainActivity) {
        this.mainActivity =mainActivity;
    }

    public PhotoAsyncTask(PictureCheckActivity pictureCheckActivity) {
        this.pictureCheckActivity=pictureCheckActivity;
    }

    public PhotoAsyncTask(PictureCheckActivity pictureCheckActivity, SharedPreferences sharePreferences) {
        this.pictureCheckActivity=pictureCheckActivity;
        this.sharePreferences=sharePreferences;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            HttpUtil.uploadAllBitmapInColorCheckDirectory(sharePreferences);
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