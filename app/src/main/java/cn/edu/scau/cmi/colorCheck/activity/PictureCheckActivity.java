package cn.edu.scau.cmi.colorCheck.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.view.PointSurfaceView;


public class PictureCheckActivity extends AppCompatActivity {
    PointSurfaceView surfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView(){
        surfaceView=findViewById(R.id.picture_check_surface);
        surfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void displayRgb(int color){

            }
            @Override
            public void showPicture(Bitmap bitmap) {
//                在这里添加图片的显示部分
                System.out.println("显示图片，暂时没有添加功能");
                Toast.makeText(PictureCheckActivity.this, "显示图片，暂时没有添加功能", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showPicture(byte[] data) {

            }

        });

    }


}
