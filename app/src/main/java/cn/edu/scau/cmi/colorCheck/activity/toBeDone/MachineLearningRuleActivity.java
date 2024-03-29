package cn.edu.scau.cmi.colorCheck.activity.toBeDone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.adapter.SampleAdapter;
import cn.edu.scau.cmi.colorCheck.olderVersion.dao.sqlLite.SqlLiteService;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.QuantitativeLinearRule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Sample;
import cn.edu.scau.cmi.colorCheck.machineLearning.Regression;

public class MachineLearningRuleActivity extends AppCompatActivity {
    int projectId;
    String type;
    List<Sample> samples;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinelearning_rule_generate);

        RecyclerView recyclerView = findViewById(R.id.collection_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        projectId = getIntent().getIntExtra("projectId",0);
        type = getIntent().getStringExtra("type");
        samples = SqlLiteService.getSampleOfProject(projectId);
        recyclerView.setAdapter(new SampleAdapter(samples,this));
        getSupportActionBar().setTitle(getIntent().getStringExtra("projectName"));

    }
    public void formFormula(View view){
        if(projectId != 0){
            if(samples.size() == 0){
                Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();

            }else{
                if("定性".equals(type)){
                    Toast.makeText(this, "定性无需手动生成", Toast.LENGTH_SHORT).show();
                    finish();
                    return ;
                }
                double[][] x = new double[samples.size()][3];
                double[] y = new double[samples.size()];
                for(int i = 0; i< samples.size(); i++){
                    x[i][0] = samples.get(i).red;
                    x[i][1] = samples.get(i).green;
                    x[i][2] = samples.get(i).blue;
                    y[i] = samples.get(i).result;
                }
                int n = 3;
                int m = samples.size();
                double[] K = new double[n+1];
                double result = Regression.optLineRegression(x, y, K, n, m,0.8,new int[100]);

                if(result == 0){
                    Toast.makeText(this, "训练失败", Toast.LENGTH_SHORT).show();
                    return ;
                }else{
                    Rule rule = new Rule();
                    rule.type = "多元线性回归";
                    rule.create_date = new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date());
                    rule.num = samples.size();
                    rule.project_id = projectId;

                    rule.name = rule.create_date + " "+ rule.type;
                    QuantitativeLinearRule quantitativeLinear_rule = new QuantitativeLinearRule();
                    quantitativeLinear_rule.k1 = new BigDecimal(K[3]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.k2 = new BigDecimal(K[3]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.k3 = new BigDecimal(K[1]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.b = new BigDecimal(K[0]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    rule.setQuantitativeLinear_rule(quantitativeLinear_rule);
                    SqlLiteService.addRule(rule);
                }
                result = Regression.LineRegression(x, y, K, n, m);
                if(result == 0){
                    Toast.makeText(this, "训练失败", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Rule rule = new Rule();
                    rule.type = "多元线性回归";
                    rule.create_date = new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date());
                    rule.num = samples.size();
                    rule.name = rule.create_date + "-t "+ rule.type;
                    rule.project_id = projectId;
                    QuantitativeLinearRule quantitativeLinear_rule = new QuantitativeLinearRule();
                    quantitativeLinear_rule.k1 = new BigDecimal(K[3]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.k2 = new BigDecimal(K[3]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.k3 = new BigDecimal(K[1]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    quantitativeLinear_rule.b = new BigDecimal(K[0]).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    rule.setQuantitativeLinear_rule(quantitativeLinear_rule);
                    SqlLiteService.addRule(rule);
                    Toast.makeText(this, "训练成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
