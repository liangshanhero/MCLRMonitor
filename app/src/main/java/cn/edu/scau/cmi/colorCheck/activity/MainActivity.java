package cn.edu.scau.cmi.colorCheck.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.dao.DAO;
import cn.edu.scau.cmi.colorCheck.dao.DatabaseHelper;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.CheckType;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
        }
        DatabaseHelper.getInstance(this,"rgb.db",null,1);
        DAO.insert(new CheckType("定量"));
        DAO.insert(new CheckType("定性"));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1 :
            {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this, "禁止用摄像头将导致程序功能异常", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
//基于像素点采集样本数据
    public void pointCollectData(View view){
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }
    //基于图片采集样本数据
    public void pictureCollectData(View view){
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }
    public void machineLearning(View view){
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
//基于像素点检测
    public void pointCheck(View view){
        Intent intent = new Intent(MainActivity.this, PointCheckActivity.class);
        startActivity(intent);
    }
//    基于图片检测
    public void pictureCheck(View view){
        Intent intent = new Intent(MainActivity.this, PictureCheckActivity.class);
        startActivity(intent);
    }

    public void usage(View view){
        Intent intent = new Intent(MainActivity.this, ManualActivity.class);
        startActivity(intent);
    }
    public void seeProject(View view){
        startActivity(new Intent(this, ProjectActivity.class));
    }
}
