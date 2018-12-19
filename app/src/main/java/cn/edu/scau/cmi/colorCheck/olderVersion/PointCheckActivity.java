package cn.edu.scau.cmi.colorCheck.olderVersion;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.olderVersion.dao.sqlLite.SqlLiteService;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.QuantitativeLinearRule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.view.CameraPointSurfaceView;
import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;

public class PointCheckActivity extends AppCompatActivity {
// 用于显示选中后的背景
    LinearLayout selectedColorLinnerLayout;
    EditText redEditText;
    EditText greenEditText;
    EditText blueEditText;
    EditText resultEditText;
    TextView resultTextView;
    CameraPointSurfaceView cameraPointSurfaceView;
    Spinner projectSpinner;
    Spinner ruleSpinner;

    private List<Project> projects;
    private List<Rule> rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_check);
        initView();

    }

    private void initView() {
        selectedColorLinnerLayout = findViewById(R.id.select_color);
        redEditText = findViewById(R.id.red);
        greenEditText = findViewById(R.id.green);
        blueEditText = findViewById(R.id.blue);
        resultTextView = findViewById(R.id.result_name);
        resultEditText = findViewById(R.id.check_result);
        projectSpinner = findViewById(R.id.point_check_project);
        ruleSpinner = findViewById(R.id.check_rule);

        cameraPointSurfaceView = findViewById(R.id.point_check_surface);
//        点击后的行为,利用自定义的
        cameraPointSurfaceView.setTouchListener(new TouchListenerAdapter() {
//            前面的RGB色彩检测方法
            @Override
            public void displayRgb(int color) {
//                界面布局的背景设置为所选择点的颜色
                selectedColorLinnerLayout.setBackgroundColor(color);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                //在检测节目显示选中点的红、绿、蓝三色的值
                redEditText.setText(red+"");
                greenEditText.setText(green+"");
                blueEditText.setText(blue+"");
                if(rules.size() ==0 ){
                    if(projects.size()>0 && projects.get(projectSpinner.getSelectedItemPosition()).type_id == 2){
                        resultEditText.setText(SqlLiteService.rangeCheck(red,green,blue,projects.get(projectSpinner.getSelectedItemPosition()).id));
                    }else{
                        Toast.makeText(PointCheckActivity.this, "请先创建规则", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(rules.get(ruleSpinner.getSelectedItemPosition()).getQuantitativeLinear_rule() != null){
                        //目前只有线性规则在使用，待以后补充
                        QuantitativeLinearRule quantitativeLinearRule = rules.get(ruleSpinner.getSelectedItemPosition()).getQuantitativeLinear_rule();
                        float result =  quantitativeLinearRule.k1*red + quantitativeLinearRule.k2*green + quantitativeLinearRule.k3*blue + quantitativeLinearRule.b;
                        resultEditText.setText(new BigDecimal(result).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }
                }
            }


            @Override
            public void displayRgbOnCoordinate(Bitmap bitmap) {
//                在这里添加图片的显示部分
                System.out.println("在坐标上显示图片，暂时没有添加功能");
                Toast.makeText(PointCheckActivity.this, "显示图片，暂时没有添加功能", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showPictureCheckResult(Bitmap bitmap) {
//                在这里添加图片的显示部分
                System.out.println("显示图片，暂时没有添加功能");
                Toast.makeText(PointCheckActivity.this, "显示图片，暂时没有添加功能", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showPictureCheckResult(byte[] data) {
//                Toast.makeText(PointCheckActivity.this, "Picture", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(PointCheckActivity.this,Main2Activity4Test.class);
//                intent.putExtra("pic",data);
//                startActivity(intent);
            }
        });

        projects = SqlLiteService.getAllProject();
        ArrayAdapter<Project> projectAdapter = new ArrayAdapter<Project>(this,  android.R.layout.simple_list_item_1,  projects);
        projectSpinner.setAdapter(projectAdapter);
        if(projects.size() != 0){
           rules = SqlLiteService.getRulesOfProject(projects.get(0));
        }else{
            rules = new ArrayList<>();
        }

        ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1,  rules);
        ruleSpinner.setAdapter(ruleAdapter);

        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rules = SqlLiteService.getRulesOfProject(projects.get(position));
                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(PointCheckActivity.this,
                        android.R.layout.simple_list_item_1,
                        rules);
                ruleSpinner.setAdapter(ruleAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}