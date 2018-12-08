package cn.edu.scau.cmi.colorCheck.asyncTask;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckResultActivity;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Check;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class CheckAsyncTask extends AsyncTask<Void,Void,String> {
    private PictureCheckResultActivity pictureCheckResultActivity;
    private File checkBitmapFile;

    private List<Check> allCheck;

    public CheckAsyncTask(PictureCheckResultActivity activity, File file){
        this.checkBitmapFile =file;
        this.pictureCheckResultActivity =activity;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String responseString = HttpUtil.gainJsonResultFromServer("Check/bitmap/"+checkBitmapFile.getName());
            allCheck = new Gson().fromJson(responseString,new TypeToken<List<Check>>(){}.getType());
            return "success";
        } catch (Exception e) {
            return "exception";
        }
    }
    @Override
    protected void onPostExecute(String result){
//        TODO 结果返回给PcitureCheckResultActivity显示，？？？
        String[] test ={"1","2","3","4"};

        pictureCheckResultActivity.getResultTextView().setText("还没有");


//        pictureCheckResultActivity.getResultTextView().setc.setchsetCheckResults(test);
        pictureCheckResultActivity.setTestFromAsynaTask("后台传数据过来，纯粹做测试");

//        PictureCheckResultActivity.CheckResultFigureView view = pictureCheckResultActivity.getCheckResultFigureView();
//        view.onDraw();
//
        pictureCheckResultActivity.getCheckResultFigureView().postInvalidate();//非UI线程重新绘制界面，晕死，写错了我view的名称，折腾了一个晚上。
        Toast.makeText(pictureCheckResultActivity,"返回到首页",Toast.LENGTH_LONG);
    }
}
