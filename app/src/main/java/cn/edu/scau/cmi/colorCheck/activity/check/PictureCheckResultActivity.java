package cn.edu.scau.cmi.colorCheck.activity.check;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.view.CheckResultFigureView;

public class PictureCheckResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check_result);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frameLayoutCheckResult);
        frameLayout.addView(new CheckResultFigureView(PictureCheckResultActivity.this));//在view上画图形。
    }
}