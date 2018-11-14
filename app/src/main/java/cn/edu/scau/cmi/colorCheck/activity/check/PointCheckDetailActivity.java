package cn.edu.scau.cmi.colorCheck.activity.check;

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
import cn.edu.scau.cmi.colorCheck.activity.ManualActivity;
import cn.edu.scau.cmi.colorCheck.activity.ProjectListActivity;
import cn.edu.scau.cmi.colorCheck.dao.sqlLite.DAO;
import cn.edu.scau.cmi.colorCheck.dao.sqlLite.DatabaseHelper;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.CheckType;

public class PointCheckDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(PointCheckDetailActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PointCheckDetailActivity.this, new String[]{Manifest.permission.CAMERA},1);
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

    public void collectData(View view){
        Intent intent = new Intent(PointCheckDetailActivity.this, ProjectListActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }

    public void machineStudy(View view){
        Intent intent = new Intent(PointCheckDetailActivity.this, ProjectListActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
    public void check(View view){
        Intent intent = new Intent(PointCheckDetailActivity.this, PointCheckActivity.class);
        startActivity(intent);
    }
    public void usage(View view){
        Intent intent = new Intent(PointCheckDetailActivity.this, ManualActivity.class);
        startActivity(intent);
    }
    public void seeProject(View view){
        startActivity(new Intent(this, ProjectListActivity.class));
    }
}
