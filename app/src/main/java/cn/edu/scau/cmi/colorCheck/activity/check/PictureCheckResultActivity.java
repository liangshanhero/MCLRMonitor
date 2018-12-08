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

//建立一个内部类，可以让view使用Activity中的数据！！！，否则数据传不进去！！！！！！
public class PictureCheckResultActivity extends AppCompatActivity {
    private CheckResultFigureView checkResultFigureView;
    private TextView resultTextView;
    private String[] checkResults;
    private String testFromAsynaTask="还没有从AsyncTask中获取数据";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        (1) 构造界面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check_result);

        resultTextView=findViewById(R.id.resultTextView);

        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frameLayoutCheckResult);

        checkResultFigureView=new CheckResultFigureView(PictureCheckResultActivity.this);
//        checkResultFigureView.invalidate();
//        checkResultFigureView.
//        setCheckResultFigureView();
        frameLayout.addView(getCheckResultFigureView());//在view上画图形。数据怎么传过去呢？




//      (2) 获取前面页面传来的检测标志
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        File checkBitmap = (File) bundle.getSerializable("checkBitmap");// 是否可以转换呢，需要测试
        Log.e("---传过来的文件名是：-----",checkBitmap.getAbsolutePath());


//        (3) 图片作为检测的参数，异步获取检测结果
        CheckAsyncTask checkAsyncTask=new CheckAsyncTask(this, checkBitmap);
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

    public String getTestFromAsynaTask() {
        return testFromAsynaTask;
    }

    public void setTestFromAsynaTask(String testFromAsynaTask) {
        this.testFromAsynaTask = testFromAsynaTask;
    }

    //内部类，用于显示结果！！！
    public class CheckResultFigureView extends View {




//        PictureCheckResultActivity pictureCheckResultActivity;
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
            getResult();//获取检测的像素点的值，目前是从文本文件中获取，下次从web中获取。
            drawRed();

//            paint.setColor(Color.RED);
//            canvas.drawPoints(redFeature,paint);
//
//            paint.setColor(Color.GREEN);
//            canvas.drawPoints(greenFeature,paint);
//
//            paint.setColor(Color.BLUE );
//            canvas.drawPoints(blueFeature,paint);
//
//            paint.setColor(Color.GRAY );
//            canvas.drawPoints(grayFeature,paint);

            paint.setTextSize(60);
            canvas.drawText(testFromAsynaTask,50,50,paint);
            Toast.makeText(PictureCheckResultActivity.this,"返回到PictureCheckResultActivity",Toast.LENGTH_LONG);
//            if(null!=checkResults){
//                canvas.drawText(checkResults.toString(),100,100,paint);
//            }

            invalidate();//重新绘制一次




        }
        //简单消除误差的方法，后面应该用真实的环境误差解决
        private void minusEnviroment(float[] points, int enviroment) {
            for(int i=0;i<points.length;i++){
                if(i%2==0){
                    points[i]=points[i]+50;
                }
                else{
                    points[i]=(points[i]-enviroment)*5;
                }
            }
        }

        private void getResult() {
        }

        private void drawRed() {
        }
    }
}