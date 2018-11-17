package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.view.PictureSurfaceView;


public class PictureCheckActivity extends AppCompatActivity {
    PictureSurfaceView surfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check);
        initView();
    }

    private void initView(){
        surfaceView=findViewById(R.id.picture_check_surface);
        surfaceView.setTouchListener(new TouchListenerAdapter(){

            @Override
            public void showPicture(Bitmap bitmap) {
//                在这里添加图片的显示部分，在结果界面中显示这个图表。

                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);

                startActivity(intent);

                System.out.println("显示图片，暂时没有添加功能");
                Toast.makeText(PictureCheckActivity.this, "显示图片，暂时没有添加功能", Toast.LENGTH_SHORT).show();
            }
        });
    }
}