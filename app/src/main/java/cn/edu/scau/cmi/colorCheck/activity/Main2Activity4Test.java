package cn.edu.scau.cmi.colorCheck.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.scau.cmi.colorCheck.R;

public class Main2Activity4Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView iv = findViewById(R.id.image);
        byte[] data = getIntent().getByteArrayExtra("pic");
        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        iv.setImageBitmap(bitmap);
        TextView textView = findViewById(R.id.text);
        textView.setText("width="+bitmap.getWidth()+" "+iv.getWidth()+"height="+bitmap.getWidth()+" "+iv.getHeight() );
    }
}
