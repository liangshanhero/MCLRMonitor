package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.activity.collect.PictureSampleCollectActivity;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.view.CheckResultView;
import cn.edu.scau.cmi.colorCheck.view.PictureSurfaceView;
import cn.edu.scau.cmi.colorCheck.view.PointSurfaceView;


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
//                在这里添加图片的显示部分
                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
                startActivity(intent);

                FrameLayout frameLayout=findViewById(R.id.frameLayoutCheckResult);
//TODO  这个方法估计有问题！！！
                frameLayout.addView(new CheckResultView(getApplicationContext()));

                System.out.println("显示图片，暂时没有添加功能");
                Toast.makeText(PictureCheckActivity.this, "显示图片，暂时没有添加功能", Toast.LENGTH_SHORT).show();
            }
        });
    }
}