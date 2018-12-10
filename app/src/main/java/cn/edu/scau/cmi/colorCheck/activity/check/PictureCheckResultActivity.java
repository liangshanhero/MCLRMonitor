package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.asyncTask.CheckAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Check;

//建立一个内部类，可以让view使用Activity中的数据！！！，否则数据传不进去！！！！！！
public class PictureCheckResultActivity extends AppCompatActivity {
    private CheckResultFigureView checkResultFigureView;
    private TextView resultTextView;
    private String[] checkResults;
    private Check check;
    private String checkName;


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
        String checkName=bundle.getString("currentCheckBitmapFileName");

        //      (3) ******图片作为检测的参数，异步获取检测结果
        CheckAsyncTask checkAsyncTask=new CheckAsyncTask(this, checkName);
        checkAsyncTask.execute();

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
            if(check != null){
                canvas.drawText(check.getName(),50,250,paint);
            }
        }
    }
}