package cn.edu.scau.cmi.rgbstudy.activity;

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

import cn.edu.scau.cmi.rgbstudy.R;
import cn.edu.scau.cmi.rgbstudy.dao.DAO;
import cn.edu.scau.cmi.rgbstudy.dao.MyDatabaseHelper;
import cn.edu.scau.cmi.rgbstudy.dao.Service;
import cn.edu.scau.cmi.rgbstudy.domain.Check_Type;
import cn.edu.scau.cmi.rgbstudy.domain.Project;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
        }
        MyDatabaseHelper.getInstance(this,"rgb.db",null,1);
        DAO.insert(new Check_Type("定量"));
        DAO.insert(new Check_Type("定性"));

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

    public void collectData(View view){
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }

    public void machineStudy(View view){
        Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
    public void check(View view){
        Intent intent = new Intent(MainActivity.this, CheckActivity.class);
        startActivity(intent);
    }
    public void usage(View view){
        Intent intent = new Intent(MainActivity.this, UseageActivity.class);
        startActivity(intent);
    }
    public void seeProject(View view){
        startActivity(new Intent(this, ProjectActivity.class));
    }
}
