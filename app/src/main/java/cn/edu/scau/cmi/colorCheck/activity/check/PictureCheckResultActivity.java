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
        frameLayout.addView(new CheckResultView(PictureCheckResultActivity.this));
    }
}