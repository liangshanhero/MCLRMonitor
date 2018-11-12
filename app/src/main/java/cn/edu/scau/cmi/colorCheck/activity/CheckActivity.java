package cn.edu.scau.cmi.colorCheck.activity;

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

import cn.edu.scau.cmi.colorCheck.dao.Service;
import cn.edu.scau.cmi.colorCheck.domain.Project;
import cn.edu.scau.cmi.colorCheck.domain.QuantitativeLinearRule;
import cn.edu.scau.cmi.colorCheck.domain.Rule;
import cn.edu.scau.cmi.colorCheck.ui.CustomizedSurfaceView;
import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.ui.TouchListener;

public class CheckActivity extends AppCompatActivity {
// 用于显示选中后的背景
    LinearLayout selectedColorLinnerLayout;
    EditText redEditText;
    EditText greenEditText;
    EditText blueEditText;
    EditText resultEditText;
    TextView resultTextView;
    CustomizedSurfaceView surfaceView;
    Spinner projectSpinner;
    Spinner ruleSpinner;

    private List<Project> projects;
    private List<Rule> rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        initView();

    }

    private void initView() {
        surfaceView = findViewById(R.id.check_surface);
//        点击后的行为
        surfaceView.setGRBTouch(new TouchListener() {
            @Override
            public void displayRGB(int color) {
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
                        resultEditText.setText(Service.rangeCheck(red,green,blue,projects.get(projectSpinner.getSelectedItemPosition()).id));
                    }else{
                        Toast.makeText(CheckActivity.this, "请先创建规则", Toast.LENGTH_SHORT).show();
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
            public void showPicture(Bitmap bitmap) {
//                在这里添加图片的显示部分
            }

            @Override
            public void showPicture(byte[] data) {
//                Toast.makeText(CheckActivity.this, "Check", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(CheckActivity.this,Main2Activity4Test.class);
//                intent.putExtra("pic",data);
//                startActivity(intent);
            }
        });

        selectedColorLinnerLayout = findViewById(R.id.select_color);
        redEditText = findViewById(R.id.red);
        greenEditText = findViewById(R.id.green);
        blueEditText = findViewById(R.id.blue);
        resultTextView = findViewById(R.id.result_name);
        resultEditText = findViewById(R.id.check_result);
        projectSpinner = findViewById(R.id.check_project);
        ruleSpinner = findViewById(R.id.check_rule);
        projects = Service.getAllProject();
        ArrayAdapter<Project> projectAdapter = new ArrayAdapter<Project>(this,
                android.R.layout.simple_list_item_1,
                projects);
        projectSpinner.setAdapter(projectAdapter);
        if(projects.size() != 0){
           rules = Service.getRulesOfProject(projects.get(0));
        }else{
            rules = new ArrayList<>();
        }
        ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                rules);
        ruleSpinner.setAdapter(ruleAdapter);


        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rules = Service.getRulesOfProject(projects.get(position));
                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(CheckActivity.this,
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