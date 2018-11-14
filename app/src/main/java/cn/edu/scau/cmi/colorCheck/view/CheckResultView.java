package cn.edu.scau.cmi.colorCheck.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;

public class CheckResultView extends View {
    public CheckResultView(Context context) {
        super(context);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        Paint paint=new Paint();

        paint.setStrokeWidth(3 );
        paint.setColor(Color.RED );
        canvas.drawLine(50,300,1000,300,paint);
        canvas.drawLine(50,300,50,1000,paint);






//        paint.setShadowLayer(2,3,3,Color.rgb(90,90,90));
//        canvas.drawRect(40,40,1200,1000,paint);
    }
}
