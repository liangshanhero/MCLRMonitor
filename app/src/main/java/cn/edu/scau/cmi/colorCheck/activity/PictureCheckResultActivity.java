package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.asyncTask.ResultAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Feature;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Picture;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Result;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rgb;

//建立一个内部类，可以让view使用Activity中的数据！！！，否则数据传不进去！！！！！！
public class PictureCheckResultActivity extends AppCompatActivity {
    private CheckResultFigureView checkResultFigureView;
    private TextView resultTextView;
    private String[] checkResults;
    private String pictureName;
    private List<Result> results;
    //    result elements for display on activity
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    private Picture picture;

    public List<Rgb> getRgbs() {
        return rgbs;
    }

    public void setRgbs(List<Rgb> rgbs) {
        this.rgbs = rgbs;
    }

    private List<Rgb> rgbs;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    private List<Feature> features;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        (1) 构造界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check_result);
        resultTextView=findViewById(R.id.resultTextView);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frameLayoutCheckResult);
        checkResultFigureView=new CheckResultFigureView(PictureCheckResultActivity.this);
        frameLayout.addView(getCheckResultFigureView());
//      (2) 获取PictureCheckActivity传来的检测文件
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        pictureName =bundle.getString("currentCheckBitmapFileName");
        System.out.println("检测的文件名字是："+ pictureName);
//      (3) ******图片作为检测的参数，异步获取检测结果
        ResultAsyncTask resultAsyncTask =new ResultAsyncTask(this, pictureName);
        resultAsyncTask.execute();

//        assert redFeature != null;
//        checkResultFigureView.addExtraDataToAccessibilityNodeInfo(redFeature,"redFeature",bundle);
    }

    public TextView getResultTextView() {
        return resultTextView;
    }

    public void setResultTextView(TextView resultTextView) {
        this.resultTextView = resultTextView;
    }

    public CheckResultFigureView getCheckResultFigureView() {
        return checkResultFigureView;
    }

    public void setCheckResultFigureView(CheckResultFigureView checkResultFigureView) {
        this.checkResultFigureView = checkResultFigureView;
    }

    public String[] getCheckResults() {
        return checkResults;
    }

    public void setCheckResults(String[] checkResults) {
        this.checkResults = checkResults;
    }

    //******内部类，用于显示结果！！！
    public class CheckResultFigureView extends View {
        public CheckResultFigureView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas){
            super.onDraw(canvas);

            canvas.drawColor(Color.WHITE);
            Paint paint=new Paint();

            paint.setStrokeWidth(3 );
            paint.setColor(Color.BLACK );
//        画坐标轴
            Point xAxisStartPoint =new Point(50,800);
            Point xAxisEndPoint=new Point(1000,800);
            Point yAxisStartPoint =new Point(50,50);
            Point yAxisEndPoint=new Point(50,800);
            canvas.drawLine(xAxisStartPoint.x,xAxisStartPoint.y,xAxisEndPoint.x,xAxisEndPoint.y,paint);//横坐标
            canvas.drawLine(yAxisStartPoint.x,yAxisStartPoint.y,yAxisEndPoint.x,yAxisEndPoint.y,paint);//纵坐标

            paint.setTextSize(60);
            canvas.drawText("检测的特征曲线：",50,50,paint);

//          后台的数据应该先判断是否存在才能显示，
            if(features != null){
                canvas.drawText("Feature值的大小是"+String.valueOf(features.size()),50,150,paint);
            }
            if(rgbs != null){
                canvas.drawText("Rgb值的大小是"+String.valueOf(rgbs.size()),50,250,paint);
            }
            if(results != null){
                canvas.drawText("结果值的大小是"+String.valueOf(results.size()),50,350,paint);
            }
        }
    }
}