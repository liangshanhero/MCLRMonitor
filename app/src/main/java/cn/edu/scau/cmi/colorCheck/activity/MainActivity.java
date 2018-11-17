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
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.activity.check.PictureCheckActivity;
import cn.edu.scau.cmi.colorCheck.activity.check.PointCheckActivity;
import cn.edu.scau.cmi.colorCheck.activity.collect.PictureSampleCollectActivity;
import cn.edu.scau.cmi.colorCheck.activity.collect.PointSampleCollectActivity;
import cn.edu.scau.cmi.colorCheck.dao.asyncTask.UserAsyncTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//异步获取用户的数据测试

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
        }
//        数据库暂时关闭
//        DatabaseHelper.getInstance(this,"rgb.db",null,1);
//        DAO.insert(new CheckType("定量"));
//        DAO.insert(new CheckType("定性"));
    }

    private void init() {
        UserAsyncTask userAsyncTask=new UserAsyncTask(this);
        userAsyncTask.execute();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1 :
            {                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this, "禁止用摄像头将导致程序功能异常", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
//    （1）查看所有的项目
    public void listProjects(View view){
        startActivity(new Intent(this, ProjectListActivity.class));
    }
    //（2）基于像素点检测
    public void pointCheck(View view){
        Intent intent = new Intent(MainActivity.this, PointCheckActivity.class);
        startActivity(intent);
    }
    // （3）基于图片检测
    public void pictureCheck(View view){
        Intent intent = new Intent(MainActivity.this, PictureCheckActivity.class);
        startActivity(intent);
    }
//（4）基于像素点采集样本数据
    public void pointCollectData(View view){
        Intent intent = new Intent(MainActivity.this, PointSampleCollectActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }
    //（5）基于图片采集样本数据
    public void pictureCollectData(View view){
        Intent intent = new Intent(MainActivity.this, PictureSampleCollectActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }
//    (6)人工定义规则生成
    public void customizeRule(View view){
        Intent intent = new Intent(MainActivity.this, CustomizeRuleActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
    //    (7)机器学习规则生成
    public void machineLearnRule(View view){
        Intent intent = new Intent(MainActivity.this, MachineLearningRuleActivity.class);
        intent.putExtra("flag",2);
        startActivity(intent);
    }

// 使用说明
    public void manual(View view){
        Intent intent = new Intent(MainActivity.this, ManualActivity.class);
        startActivity(intent);
    }
    // 测试数据异步采集
    public void testJsonDataFromWeb(View view){
        EditText editText=(EditText)findViewById(R.id.editText);
        editText.setText("准备采集数据"+UserAsyncTask.getUsers());

    }

}
