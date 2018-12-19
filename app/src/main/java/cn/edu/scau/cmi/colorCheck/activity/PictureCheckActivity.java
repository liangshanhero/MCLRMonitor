package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.asyncTask.FileAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.ItemAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.RuleAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.util.FileUtil;
import cn.edu.scau.cmi.colorCheck.view.CameraPictureSurfaceView;

public class PictureCheckActivity extends AppCompatActivity {
    CameraPictureSurfaceView cameraPictureSurfaceView;
    Spinner itemSpinner;
    Spinner ruleSpinner;
    Item selectedItem;
    Rule selectedRule;
    File currentCheckBitmapFile;//当前的检测文件
    String currentCheckBitmapFileName;
    private static  List<Item> allItemList;
    private static List<Rule> ruleList;
    private SharedPreferences sharePreferencesEditor;

    ArrayAdapter<Item> itemArrayAdapter;
    ArrayAdapter<Rule> ruleArrayAdapter;
    EditText sampleResultEditText;


    public void setProjectList(List<Item> itemList){
        this.allItemList = itemList;
    }
    private ArrayAdapter<Item> projectAdapter;
    private ArrayAdapter<Rule> ruleAdapter;
    public ArrayAdapter getProjectAdapter(){
        return this.projectAdapter;
    }
    public Spinner getItemSpinner(){
        return itemSpinner;
    }
    public Spinner getRuleSpinner(){
        return ruleSpinner;
    }
    Bundle bundle=new Bundle();
    String function;//使用的功能，是检测还是样本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        界面绘制
        Intent intent=getIntent();
        function = intent.getExtras().getString("function");
//        不同的功能显示不同的界面
       setContentView(R.layout.activity_picture_check);
        View sampleResultLine = findViewById(R.id.sample_result);
        sampleResultEditText=findViewById(R.id.editText_sample_result);


        if(function.equals("check")){
            sampleResultLine.setVisibility(View.INVISIBLE);
        }

        initCameraView();
        initItemSpinner();
        initRuleSpinner();
        //       获取sharedPreferences
        sharePreferencesEditor=getSharedPreferences("colorCheckBitmaps",MODE_PRIVATE);
    }

    private void initRuleSpinner() {
       ruleSpinner=findViewById(R.id.picture_check_rule_spinner);
       ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectedRule = (Rule) ruleSpinner.getSelectedItem();
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });
    }

    private void initItemSpinner() {
        itemSpinner =findViewById(R.id.picture_check_item_spinner);
//        获取所有的检测项。
        ItemAsyncTask itemAsyncTask =new ItemAsyncTask(this, new ItemAsyncTask.HttpFinishedListener() {
            @Override
            public void doSomething(List<Item> itemList) {
               itemArrayAdapter = new ArrayAdapter<Item>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, itemList);
               itemSpinner.setAdapter(itemArrayAdapter);
            }
            @Override
            public void doNothing() {
            }
        });
        itemAsyncTask.execute();

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//              选中的项目
//              得到选中的项目，测试一下看能否得到Item对象。
                Item selectedItem = (Item) parent.getItemAtPosition(position);
                PictureCheckActivity.this.selectedItem = (Item) itemSpinner.getSelectedItem();
//                获取了规则后，在spinner中显示出这个项目具有的规则
                RuleAsyncTask ruleAsyncTask=new RuleAsyncTask(PictureCheckActivity.this, PictureCheckActivity.this.selectedItem, new RuleAsyncTask.HttpFinishedListener() {
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
                currentCheckBitmapFileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

                if(selectedRule!=null){
                    if(function.equals("sample")){
                        if(sampleResultEditText.getText()==null){
                            Toast.makeText(PictureCheckActivity.this,"请输入合适的结果",Toast.LENGTH_LONG);
                        }else{//文件名是：检测项目名+功能名称（检测还是样本）+时间戳+结果
                            currentCheckBitmapFile =FileUtil.getCurrentFile(selectedItem.getName(),function,currentCheckBitmapFileName,sampleResultEditText.getText().toString());
                        }

                    }else{//check
                        currentCheckBitmapFile =FileUtil.getCurrentFile(selectedItem.getName(),function,currentCheckBitmapFileName,"-1");
                    }
                    try {
                        FileUtil.saveColorCheckBitmap(bitmap, currentCheckBitmapFile);
                        Toast.makeText(PictureCheckActivity.this,"文件已经保存，你可以上传文件并检查结果了。",Toast.LENGTH_LONG);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(PictureCheckActivity.this,"请选中合适的规则",Toast.LENGTH_LONG);
                }


//                （2）启动结果页面
//                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
//                getAndTransiteFeatureToPictureCheckResultActivity(currentCheckBitmapFile, intent);
//                startActivity(intent);
            }
        });
    }

    //上传最后一次点击的相片，还没有结果，因此结果result赋值为-1
//    如果是样本，就有结果，类型也是sample
    public void onUploadBitMapAndResult(View view){
        FileAsyncTask fileAsyncTask=new FileAsyncTask(PictureCheckActivity.this,sharePreferencesEditor, currentCheckBitmapFile);
        fileAsyncTask.execute();
    }

    //上传所有的没上传的图片文件，调用PhotoAsyncTask，PhotoAsyncTask调用HttpUtil完成图像上传工作。OK
    public void onUploadAllBitMap(View view){
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
        FileAsyncTask fileAsyncTask =new FileAsyncTask(PictureCheckActivity.this,sharePreferencesEditor,null);
        fileAsyncTask.execute();
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