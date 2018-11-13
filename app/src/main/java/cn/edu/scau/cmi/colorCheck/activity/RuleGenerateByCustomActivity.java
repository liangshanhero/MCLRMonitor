package cn.edu.scau.cmi.colorCheck.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.dao.Service;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.QuantitativeLinearRule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;

public class RuleGenerateByCustomActivity extends AppCompatActivity {


    EditText k1;
    EditText ruleName;
    EditText k2;
    EditText k3;
    EditText b;
    int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rule_add);
        k1 = findViewById(R.id.add_rule_k1);
        k2 = findViewById(R.id.add_rule_k2);
        k3 = findViewById(R.id.add_rule_k3);
        b = findViewById(R.id.add_rule_b);
        ruleName = findViewById(R.id.add_rule_name);

        projectId = getIntent().getIntExtra("projectId",0);
        getSupportActionBar().setTitle("添加自定义规则");
    }

    public void sureAddRule(View view){
        if(ruleName.getText().toString().length() == 0){
            Toast.makeText(this, "规则名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(k1.getText().length() == 0 || k2.getText().length() == 0 || k3.getText().length() == 0 || b.getText().length() == 0 ){
            Toast.makeText(this, "存在未输入的参数值", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(projectId != 0){

            Rule rule = new Rule();
            rule.project_id = projectId;
            rule.num = 0;
            rule.name = ruleName.getText().toString();
            rule.create_date = new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date());
            rule.type = "自定义规则";
            QuantitativeLinearRule quantitativeLinear_rule = new QuantitativeLinearRule();
            quantitativeLinear_rule.k1 = Float.valueOf(k1.getText().toString());
            quantitativeLinear_rule.k2 = Float.valueOf(k2.getText().toString());
            quantitativeLinear_rule.k3 = Float.valueOf(k3.getText().toString());
            quantitativeLinear_rule.b = Float.valueOf(b.getText().toString());
            rule.setQuantitativeLinear_rule(quantitativeLinear_rule);
            if(Service.addRule(rule)){
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "规则名已存在", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "(0 .0)! 项目Id错了", Toast.LENGTH_SHORT).show();
        }
    }

}
