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
import cn.edu.scau.cmi.colorCheck.activity.toBeDone.CustomizeRuleActivity;
import cn.edu.scau.cmi.colorCheck.activity.toBeDone.MachineLearningRuleActivity;
import cn.edu.scau.cmi.colorCheck.activity.toBeDone.ManualActivity;
import cn.edu.scau.cmi.colorCheck.olderVersion.PointCheckActivity;
import cn.edu.scau.cmi.colorCheck.olderVersion.PointSampleCollectActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
        }
//        数据库暂时关闭
//        DatabaseHelper.getInstance(this,"rgb.db",null,1);
//        DAO.insert(new CheckType("定量"));
//        DAO.insert(new CheckType("定性"));
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
//    （1）查看并管理所有的项目
    public void onProjectManage(View view){
        startActivity(new Intent(this, ItemActivity.class));
    }

    // （2）基于图片检测
    public void onPictureCheck(View view){
        Intent intent = new Intent(MainActivity.this, PictureCheckActivity.class);
        intent.putExtra("function","check");
        startActivity(intent);
    }

    //（3）基于图片采集样本数据
    public void onPictureCheckSampleCollect(View view){
        Intent intent = new Intent(MainActivity.this, PictureCheckActivity.class);
        intent.putExtra("function","sample");
        startActivity(intent);
    }
//    (4)人工定义规则生成
    public void onCustomizeRuleGenerate(View view){
        Intent intent = new Intent(MainActivity.this, CustomizeRuleActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
    //    (5机器学习规则生成
    public void onMachineLearnRuleGenerate(View view){
        Intent intent = new Intent(MainActivity.this, MachineLearningRuleActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }


// 使用说明
    public void manual(View view){
        Intent intent = new Intent(MainActivity.this, ManualActivity.class);
        startActivity(intent);
    }


    //（2）基于像素点检测
    public void onPointCheck(View view){
        Intent intent = new Intent(MainActivity.this, PointCheckActivity.class);
        startActivity(intent);
    }

    //（4）基于像素点采集样本数据
    public void onPointCollectData(View view){
        Intent intent = new Intent(MainActivity.this, PointSampleCollectActivity.class);
        intent.putExtra("function","collect");
        startActivity(intent);
    }


}
