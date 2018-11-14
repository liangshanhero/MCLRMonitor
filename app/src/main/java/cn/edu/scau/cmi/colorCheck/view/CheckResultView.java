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
        Paint paint=new Paint();
        paint.setColor(Color.RED );
        paint.setShadowLayer(2,3,3,Color.rgb(90,90,90));
        canvas.drawRect(40,0,200,100,paint);
    }
}
