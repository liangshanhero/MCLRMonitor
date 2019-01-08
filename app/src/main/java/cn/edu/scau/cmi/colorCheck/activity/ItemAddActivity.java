package cn.edu.scau.cmi.colorCheck.activity;

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

import java.util.ArrayList;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.olderVersion.dao.sqlLite.SqlLiteService;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.CheckType;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Target;
import cn.edu.scau.cmi.colorCheck.layout.RangeLinearLayout;

public class ItemAddActivity extends AppCompatActivity {

    EditText name;
    EditText memo;
    Spinner type;
    TextView addRange;
    LinearLayout range;

    List<RangeLinearLayout> ranges = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);
        name = findViewById(R.id.add_project_name);
        memo = findViewById(R.id.add_project_memo);
        addRange = findViewById(R.id.add_project_add_range);
        range = findViewById(R.id.add_project_range);
        type = findViewById(R.id.add_project_check_type);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    for (RangeLinearLayout rangeLinearLayout : ranges)
                    {
                        range.removeView(rangeLinearLayout);
                    }
                    range.setVisibility(View.GONE);
                    ranges.clear();
                }else{
                    range.setVisibility(View.VISIBLE);
                    RangeLinearLayout rangeLinearLayout = new RangeLinearLayout(ItemAddActivity.this, new RangeLinearLayout.DeleteListener() {
                        @Override
                        public void deleteRange(RangeLinearLayout layout) {
                            range.removeView(layout);
                            ranges.remove(layout);
                        }
                    });
                    range.addView(rangeLinearLayout);
                    ranges.add(rangeLinearLayout);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, SqlLiteService.getAllCheckType());
        type.setAdapter(adapter);
        addRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RangeLinearLayout rangeLinearLayout = new RangeLinearLayout(ItemAddActivity.this, new RangeLinearLayout.DeleteListener() {
                    @Override
                    public void deleteRange(RangeLinearLayout layout) {
                        range.removeView(layout);
                        ranges.remove(layout);
                    }
                });
                range.addView(rangeLinearLayout);
                ranges.add(rangeLinearLayout);
            }
        });
    }

    public void sureAddProject(View view){
        if(name.getText().toString().length() == 0 ){
            Toast.makeText(this, "未输入项目名", Toast.LENGTH_SHORT).show();
            return;
        }

        String type1 = type.getSelectedItem().toString();
        if(type1.equals("定性")&&ranges.size()==0){
            Toast.makeText(this, "未添加目标值", Toast.LENGTH_SHORT).show();
            return ;
        }
        Project project = new Project(name.getText().toString(),memo.getText().toString());
        if("定性".equals(type1)){
            if(!SqlLiteService.addProject(project,new CheckType(type1))){
                Toast.makeText(this, "项目名已被使用", Toast.LENGTH_SHORT).show();
                return ;
            }
            List<Target> targets = new ArrayList<>();
            for(RangeLinearLayout layout : ranges){
                if(layout.getRange().length() == 0){
                    Toast.makeText(this, "存在未输入范围", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    targets.add(new Target(layout.getRange()));
                }
            }
           SqlLiteService.addRangeOfProject(project,targets);
        }else{
            if(!SqlLiteService.addProject(project,new CheckType(type1))){
                Toast.makeText(this, "项目名已被使用", Toast.LENGTH_SHORT).show();
                return ;
            }
        }
        Toast.makeText(this, "新建成功", Toast.LENGTH_SHORT).show();
        finish();

    }
}
