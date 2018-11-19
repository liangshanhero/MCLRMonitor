package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void setProjectList(List<Project> projectList){
        this.projectList=projectList;
    }

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
        initCamera();

        projectSpinner=findViewById(R.id.picture_check_project_spinner);
//TODO        选择了project后应该在选择项目对应的规则，
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

        ruleSpinner=findViewById(R.id.picture_check_rule_spinner);

        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this);
        projectAsyncTask.execute();
        RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(this);
        ruleAsyncTask.execute();

    }

    private void initCamera() {
        //        （1）摄像头处理
        cameraPictureSurfaceView =findViewById(R.id.picture_check_surface);
        //                在这里添加图片的显示部分，在结果界面中显示这个图表。
//                保持图片文件，如过不能保存图片，需要在手机中设置读取权限
        cameraPictureSurfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void showPicture(Bitmap bitmap) {
//                保存图片到本地,文件名称是yyyyMMddhhmmss，OK。
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
    public void picture_check(View view){
//      在这里将图片的检测结果作为参数在结果界面中显示这个图表。
//        获取图片！！！
        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
        startActivity(intent);
    }
//Spinner设置初始值
    public void onPictureCheckGainData(View view){
        projectList=ProjectAsyncTask.getAllProject();
        System.out.println("***********************"+projectList);
        projectAdapter=new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projectList);
        projectSpinner.setAdapter(projectAdapter);

        ruleList=RuleAsyncTask.getAllRules();
        System.out.println("----------------------"+ruleList);
        ruleAdapter=new ArrayAdapter<Rule>(this, android.R.layout.simple_list_item_1,ruleList);
        ruleSpinner.setAdapter(ruleAdapter);
    }
}