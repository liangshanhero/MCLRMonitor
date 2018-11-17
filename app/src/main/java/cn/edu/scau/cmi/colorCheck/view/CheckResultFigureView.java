package cn.edu.scau.cmi.colorCheck.view;
//检测结果的图形描述
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;

public class CheckResultFigureView extends View {
    public CheckResultFigureView(Context context) {
        super(context);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);


        canvas.drawColor(Color.GREEN);
        Paint paint=new Paint();

        paint.setStrokeWidth(3 );
        paint.setColor(Color.RED );

        Point xAxisStartPoint =new Point(50,800);
        Point xAxisEndPoint=new Point(1000,800);

        Point yAxisStartPoint =new Point(50,50);
        Point yAxisEndPoint=new Point(50,800);
//结果来源，现在暂时模拟，后面应该从数据库中获取或者其他地方获取
//        float[] points=new float[2000];
        float[] points=new float[2000];
        for(int i=0;i<1000;i=i+2){
            points[i]=i+50;
//            points[i+1]=(int) Math.random()*800;
            points[i+1]=(50+i*2)%800;
        }

        canvas.drawLine(xAxisStartPoint.x,xAxisStartPoint.y,xAxisEndPoint.x,xAxisEndPoint.y,paint);//横坐标
        canvas.drawLine(yAxisStartPoint.x,yAxisStartPoint.y,yAxisEndPoint.x,yAxisEndPoint.y,paint);//纵坐标

        paint.setColor(Color.BLACK);
        canvas.drawPoints(points,paint);
//        canvas.drawPoints(new float[]{10,10,15,10,20,15,25,10,30,10},paint);
        paint.setTextSize(24);
        canvas.drawText("华南农业大学",20,20,paint);

//        paint.setShadowLayer(2,3,3,Color.rgb(90,90,90));
//        canvas.drawRect(40,40,1200,1000,paint);
    }
}
