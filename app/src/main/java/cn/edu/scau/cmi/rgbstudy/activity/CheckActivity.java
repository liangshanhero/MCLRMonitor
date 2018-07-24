package cn.edu.scau.cmi.rgbstudy.activity;

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

import cn.edu.scau.cmi.rgbstudy.dao.Service;
import cn.edu.scau.cmi.rgbstudy.domain.Project;
import cn.edu.scau.cmi.rgbstudy.domain.Linear_Rule;
import cn.edu.scau.cmi.rgbstudy.domain.Range_Rule;
import cn.edu.scau.cmi.rgbstudy.domain.Rule;
import cn.edu.scau.cmi.rgbstudy.ui.CustomizedSurfaceView;
import cn.edu.scau.cmi.rgbstudy.R;

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
                    Toast.makeText(CheckActivity.this, "请先创建规则", Toast.LENGTH_SHORT).show();
                }else{
                    if(rules.get(rule.getSelectedItemPosition()).getLinear_rule() != null){
                        Linear_Rule rul = rules.get(rule.getSelectedItemPosition()).getLinear_rule();
                        float y =  rul.k1*r + rul.k2*g + rul.k3*b + rul.b;
                        result.setText(new BigDecimal(y).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
                    }else{
                        List<Range_Rule> one = new ArrayList<>();
                        List<Range_Rule> two = new ArrayList<>();
                        List<Range_Rule> three = new ArrayList<>();
                        for(Range_Rule range_rule : rules.get(rule.getSelectedItemPosition()).getRange_rules()){
                            int count = 0;
                            if(range_rule.r_start <= r && r <= range_rule.r_end ){
                                count++;
                            }
                            if(range_rule.g_start <= g && g <= range_rule.g_end ){
                                count++;
                            }
                            if(range_rule.b_start <= b && b <= range_rule.b_end ){
                                count++;
                            }
                            if(count == 1){
                                one.add(range_rule);
                            }
                            if(count == 2){
                                two.add(range_rule);
                            }
                            if(color == 3){
                                three.add(range_rule);
                            }
                        }
                        if(three.size() != 0){
                            String rs = "";
                            for(Range_Rule range_rule : three){
                                rs += range_rule.getResult()+" ";
                            }
                            result.setText(rs);
                        }else if(two.size() != 0){
                            String rs = "";
                            for(Range_Rule range_rule : two){
                                rs += range_rule.getResult()+" ";
                            }
                            result.setText(rs);
                        }else if(one.size()!=0){
                            String rs = "";
                            for(Range_Rule range_rule : one){
                                rs += range_rule.getResult()+" ";
                            }
                            result.setText(rs);
                        }else{
                            result.setText("无最优解");
                        }
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
