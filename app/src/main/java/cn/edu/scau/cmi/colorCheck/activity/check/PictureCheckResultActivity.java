package cn.edu.scau.cmi.colorCheck.activity.check;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.view.CheckResultView;

public class PictureCheckResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check_result);
       FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frameLayoutCheckResult);
//       获取context， 估计有问题！！！


       //TODO  这个方法估计有问题！！！java.lang.NullPointerException，空指针异常
        if(frameLayout == null){
            Toast.makeText(PictureCheckResultActivity.this, "frameLayout对象是空", Toast.LENGTH_SHORT).show();
        }
        else{
//            frameLayout.addView(new CheckResultView(this.getApplicationContext()));
            frameLayout.addView(new CheckResultView(PictureCheckResultActivity.this));
        }





    }

}
