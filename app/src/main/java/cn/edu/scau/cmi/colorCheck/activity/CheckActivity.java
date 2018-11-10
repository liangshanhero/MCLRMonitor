package cn.edu.scau.cmi.colorCheck.activity;

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
import cn.edu.scau.cmi.colorCheck.domain.Linear_Rule;
import cn.edu.scau.cmi.colorCheck.domain.Rule;
import cn.edu.scau.cmi.colorCheck.ui.CustomizedSurfaceView;
import cn.edu.scau.cmi.colorCheck.R;

public class CheckActivity extends AppCompatActivity {

    LinearLayout selectedColor;
    EditText red;
    EditText green;
    EditText blue;
    EditText result;
    TextView resultName;
    CustomizedSurfaceView surfaceView;
    Spinner project;
    Spinner rule;

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
        surfaceView.setGRBTouch(new CustomizedSurfaceView.RGBTouch() {
            @Override
            public void displayRGB(int color) {
                selectedColor.setBackgroundColor(color);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                red.setText(r+"");
                green.setText(g+"");
                blue.setText(b+"");
                if(rules.size() ==0 ){
                    if(projects.size()>0 && projects.get(project.getSelectedItemPosition()).type_id == 2){
                        result.setText(Service.rangeCheck(r,g,b,projects.get(project.getSelectedItemPosition()).id));
                    }else{
                        Toast.makeText(CheckActivity.this, "请先创建规则", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(rules.get(rule.getSelectedItemPosition()).getLinear_rule() != null){
                        Linear_Rule rul = rules.get(rule.getSelectedItemPosition()).getLinear_rule();
                        float y =  rul.k1*r + rul.k2*g + rul.k3*b + rul.b;
                        result.setText(new BigDecimal(y).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }
                }
            }

            @Override
            public void showPicture(byte[] data) {
//                Toast.makeText(CheckActivity.this, "Check", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(CheckActivity.this,Main2Activity4Test.class);
//                intent.putExtra("pic",data);
//                startActivity(intent);
            }
        });

        selectedColor = findViewById(R.id.select_color);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        resultName = findViewById(R.id.result_name);
        result = findViewById(R.id.check_result);
        project = findViewById(R.id.check_project);
        rule = findViewById(R.id.check_rule);
        projects = Service.getAllProject();
        ArrayAdapter<Project> projectAdapter = new ArrayAdapter<Project>(this,
                android.R.layout.simple_list_item_1,
                projects);
        project.setAdapter(projectAdapter);
        if(projects.size() != 0){
           rules = Service.getRulesOfProject(projects.get(0));
        }else{
            rules = new ArrayList<>();
        }
        ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                rules);
        rule.setAdapter(ruleAdapter);


        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rules = Service.getRulesOfProject(projects.get(position));
                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(CheckActivity.this,
                        android.R.layout.simple_list_item_1,
                        rules);
                rule.setAdapter(ruleAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




}
