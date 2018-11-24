package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.activity.MainActivity;
//相片异步上传到服务器中。上传成功后在通知栏中显示消息

public class PhotoAsyncTask extends AsyncTask  <Void,Void,String>{
    private MainActivity mainActivity;

    public PhotoAsyncTask(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return "暂时还没有完成上传的功能";
    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(mainActivity, result, Toast.LENGTH_SHORT).show();
    }
}