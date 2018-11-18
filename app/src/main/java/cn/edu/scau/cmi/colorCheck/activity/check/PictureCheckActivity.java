package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.util.FileUtil;
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
//                保持图片文件
                try {
                    FileUtil.saveMyBitmap(bitmap,"20181118");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
                startActivity(intent);
            }
        });
    }

    // TODO  待完成：点击界面上的检测按钮后打开结果页面
    private void picture_check(View view){
//      在这里将图片的检测结果作为参数在结果界面中显示这个图表。
//        获取图片！！！
        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
        startActivity(intent);
    }
}