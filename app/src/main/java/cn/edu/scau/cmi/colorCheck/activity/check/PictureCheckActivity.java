package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_check);
        initView();
    }

    private void initView(){
//        （1）摄像头处理
        cameraPictureSurfaceView =findViewById(R.id.picture_check_surface);
        //                在这里添加图片的显示部分，在结果界面中显示这个图表。
//                保持图片文件，如过不能保存图片，需要在手机中设置读取权限
        cameraPictureSurfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void showPicture(Bitmap bitmap) {
//                保存图片到本地
                try {
                    FileUtil.saveMyBitmap(bitmap,"20181118");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
                startActivity(intent);
            }
        });

//TODO        （2）所有检测项目的获取,将项目列表给projectSpinner选择。
//TODO 好像也可以不用传context进去，最后通过静态方法获取值应该可以，待测试!!!!!!
//        TODO 也应该可以使用一个自定义的service类，这个service再来调用AsyncTask任务，待测试？？？？？？这样应该可以简化代码
        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this);
        projectAsyncTask.execute();
        projectList=ProjectAsyncTask.getAllProject();
        ArrayAdapter<Project> projectAdapter=new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projectList);
        projectSpinner.setAdapter(projectAdapter);

//TODO        (3)应该是项目选择后，根据项目的情况出现规则的值，现在都选，后面在修改。
        RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(this);
        ruleAsyncTask.execute();
        ruleList=RuleAsyncTask.getAllRules();
        ArrayAdapter<Rule> ruleAdapter=new ArrayAdapter<Rule>(this, android.R.layout.simple_list_item_1,ruleList);
        ruleSpinner.setAdapter(ruleAdapter);

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

    // TODO  待完成：点击界面上的检测按钮后打开结果页面
    private void picture_check(View view){
//      在这里将图片的检测结果作为参数在结果界面中显示这个图表。
//        获取图片！！！
        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
        startActivity(intent);
    }
}