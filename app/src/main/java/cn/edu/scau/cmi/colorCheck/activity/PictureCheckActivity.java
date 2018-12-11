package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.asyncTask.FileAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.PhotoAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.ProjectAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.RuleAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.util.FileUtil;
import cn.edu.scau.cmi.colorCheck.view.CameraPictureSurfaceView;

public class PictureCheckActivity extends AppCompatActivity {
    CameraPictureSurfaceView cameraPictureSurfaceView;
    Spinner projectSpinner;
    Spinner ruleSpinner;
    Project selectProject;
    Rule selectRule;
    File currentCheckBitmapFile;//当前的检测文件
    String currentCheckBitmapFileName;
    private static  List<Project> allProjectList;
    private static List<Rule> ruleList;
    private SharedPreferences sharePreferencesEditor;

    ArrayAdapter<Project> projectArrayAdapter;
    ArrayAdapter<Rule> ruleArrayAdapter;


    public void setProjectList(List<Project> projectList){
        this.allProjectList =projectList;
    }
    private ArrayAdapter<Project> projectAdapter;
    private ArrayAdapter<Rule> ruleAdapter;
    public ArrayAdapter getProjectAdapter(){
        return this.projectAdapter;
    }
    public Spinner getProjectSpinner(){
        return projectSpinner;
    }
    public Spinner getRuleSpinner(){
        return ruleSpinner;
    }
    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        界面绘制
        setContentView(R.layout.activity_picture_check);
        initCameraView();
        initProjectSpinner();
        initRuleSpinner();
        //       获取sharedPreferences
        sharePreferencesEditor=getSharedPreferences("colorCheckBitmaps",MODE_PRIVATE);
    }

    private void initRuleSpinner() {
       ruleSpinner=findViewById(R.id.picture_check_rule_spinner);
       ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectRule = (Rule) projectSpinner.getSelectedItem();
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });
    }

    private void initProjectSpinner() {
        projectSpinner=findViewById(R.id.picture_check_project_spinner);
//        获取所有的检测项目。
        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this, new ProjectAsyncTask.HttpFinishedListener() {
            @Override
            public void doSomething(List<Project> projectList) {
               projectArrayAdapter = new ArrayAdapter<Project>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, projectList);
               projectSpinner.setAdapter(projectArrayAdapter);
            }
            @Override
            public void doNothing() {
            }
        });
        projectAsyncTask.execute();

        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//              选中的项目
//              得到选中的项目，测试一下看能否得到Project对象。
                Project selectedProject = (Project) parent.getItemAtPosition(position);
                selectProject = (Project) projectSpinner.getSelectedItem();
//                获取了规则后，在spinner中显示规则
                RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(PictureCheckActivity.this, selectProject, new RuleAsyncTask.HttpFinishedListener() {
                    @Override
                    public void doSomething(List<Rule> ruleList) {
                        ruleArrayAdapter = new ArrayAdapter<Rule>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, ruleList);
                        ruleSpinner.setAdapter(ruleArrayAdapter);
                    }

                    @Override
                    public void doNothing() {
                    }
                });
                ruleAsyncTask.execute();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initCameraView() {
        //        （1）摄像头处理
        cameraPictureSurfaceView =findViewById(R.id.picture_check_surface);
        //                在这里添加图片的显示部分，在结果界面中显示这个图表。
//                保持图片文件，如过不能保存图片，需要在手机中设置读取权限
        cameraPictureSurfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void showPictureCheckResult(Bitmap bitmap) {
//              (1)点击图片，等待聚焦后，将保存图片到本地,文件名称是yyyyMMddhhmmss，OK。
                currentCheckBitmapFileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

                currentCheckBitmapFile =FileUtil.getCurrentFile("PH","Check",currentCheckBitmapFileName,"");
                try {
                    FileUtil.saveColorCheckBitmap(bitmap, currentCheckBitmapFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                （2）启动结果页面
//                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
//                getAndTransiteFeatureToPictureCheckResultActivity(currentCheckBitmapFile, intent);
//                startActivity(intent);
            }
        });
    }



    //点击初始化按钮，Spinner设置初始值
    public void onPictureCheckGainData(View view){
        allProjectList =ProjectAsyncTask.getAllProjectList();
        projectAdapter=new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1, allProjectList);
        projectSpinner.setAdapter(projectAdapter);

        ruleList=RuleAsyncTask.getAllRules();
        ruleAdapter=new ArrayAdapter<Rule>(this, android.R.layout.simple_list_item_1,ruleList);
        ruleSpinner.setAdapter(ruleAdapter);
    }
    //上传最后一次点击的相片，还没有结果，因此结果result赋值为-1
    public void onUploadPictureCheckBitMap(View view){
        FileAsyncTask fileAsyncTask=new FileAsyncTask(PictureCheckActivity.this,sharePreferencesEditor, currentCheckBitmapFile);
        fileAsyncTask.execute();
    }

    //上传所有的没上传的图片文件，调用PhotoAsyncTask，PhotoAsyncTask调用HttpUtil完成图像上传工作。OK
    public void onUploadAllPictureCheckBitMapFile(View view){
        FileAsyncTask fileAsyncTask=new FileAsyncTask(PictureCheckActivity.this,sharePreferencesEditor,null);
        fileAsyncTask.execute();
    }
    // 显示检测结果
    public void onShowPictureCheckResult(View view){
        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
//        bundle.putSerializable("currentCheckBitmapFile", currentCheckBitmapFile);
//        文件的时间名称，
        bundle.putString("currentCheckBitmapFileName",currentCheckBitmapFileName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //上传所有的没上传的图片文件，调用PhotoAsyncTask，PhotoAsyncTask调用HttpUtil完成图像上传工作。OK
    public void onUploadAllPictureCheckBitMap(View view){
        PhotoAsyncTask photoAsyncTask=new PhotoAsyncTask(PictureCheckActivity.this,sharePreferencesEditor,null);
        photoAsyncTask.execute();
    }

//点击图片后，显示结果界面，
//    public void onPictureCheck(View checkResultFigureView){
////      在这里将图片的检测结果作为参数在结果界面中显示这个图表。
////        获取图片！！！
//        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
////        将选中的项目和规则以及图片传到后台
//
//        startActivity(intent);
//    }
}