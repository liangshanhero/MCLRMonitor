package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.dao.asyncTask.ProjectAsyncTask;
import cn.edu.scau.cmi.colorCheck.dao.asyncTask.RuleAsyncTask;


import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.util.FileUtil;
import cn.edu.scau.cmi.colorCheck.view.CameraPictureSurfaceView;


public class PictureCheckActivity extends AppCompatActivity {
    CameraPictureSurfaceView cameraPictureSurfaceView;
    Spinner projectSpinner;
    Spinner ruleSpinner;
    private static  List<Project> projectList;
    private static List<Rule> ruleList;
    private Handler handler;
    private ArrayAdapter<Project> projectAdapter;
    private ArrayAdapter<Rule> ruleAdapter;
    private TextView tv_random;
    private Looper looper;

    public ArrayAdapter getProjectAdapter(){
        return this.projectAdapter;
    }

    public Spinner getProjectSpinner(){
        return projectSpinner;
    }

    public Spinner getRuleSpinner(){
        return ruleSpinner;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check);
        initView();

//异步获取数据，并在异步任务中显示输出，设置界面
        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this);
        projectAsyncTask.execute();
//       暂时还么有rule数据
        RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(this);
        ruleAsyncTask.execute();
        initSpinner();

    }




    private void initView(){
//        第一步：初始化摄像头
        initCamera();
//第二部：初始化project和rule的spinner。
//        initSpinner();

//TODO (4)点击项目后，ruleSpinner的内容应该改变

//        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ruleList = Service.getRulesOfProject(ruleList.get(position));
//                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, ruleList);
//                ruleSpinner.setAdapter(ruleAdapter);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
    }

    private void initSpinner() {
        projectAdapter=new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projectList);
        projectSpinner.setAdapter(projectAdapter);

        ruleList=RuleAsyncTask.getAllRules();
        ruleAdapter=new ArrayAdapter<Rule>(this, android.R.layout.simple_list_item_1,ruleList);
//        ruleSpinner.setAdapter(ruleAdapter);
    }



    private void initCamera() {
        //        （1）摄像头处理
        cameraPictureSurfaceView =findViewById(R.id.picture_check_surface);
        //                在这里添加图片的显示部分，在结果界面中显示这个图表。
//                保持图片文件，如过不能保存图片，需要在手机中设置读取权限
        cameraPictureSurfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void showPicture(Bitmap bitmap) {
//                保存图片到本地,文件名称是yyyyMMddhhmmss
                String fileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                try {
                    FileUtil.saveMyBitmap(bitmap,fileName);
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
// 启用线程，调用异步任务获取数据，并利用hanlder更新spinner
//    @Override
//    public void run() {
//
//        //TODO        （2）所有检测项目的获取,将项目列表给projectSpinner选择。
////TODO 好像也可以不用传context进去，最后通过静态方法获取值应该可以，待测试!!!!!!
////        TODO 也应该可以使用一个自定义的service类，这个service再来调用AsyncTask任务，待测试？？？？？？这样应该可以简化代码
//        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this);
//        projectAsyncTask.execute();
//
//        //TODO        (3)应该是项目选择后，根据项目的情况出现规则的值，现在都选，后面在修改。
//        RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(this);
//
//        ruleAsyncTask.execute();
//        while (!Thread.currentThread().isInterrupted()){
//            Message m=handler.obtainMessage();
//            m.what=1;
//            handler.sendMessage(m);
//            try{
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }








}