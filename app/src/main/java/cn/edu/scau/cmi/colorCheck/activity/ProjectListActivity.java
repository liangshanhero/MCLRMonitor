package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.adapter.ProjectAdapter;
import cn.edu.scau.cmi.colorCheck.dao.sqlLite.Service;

public class ProjectListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProjectAdapter adapterProject;
    Button delBtn;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        recyclerView = findViewById(R.id.project_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        delBtn = findViewById(R.id.project_del);
        flag = getIntent().getIntExtra("flag",0);
        if(flag != 0){
            delBtn.setVisibility(View.GONE);
            getSupportActionBar().setTitle("请选择一个项目");
        }else{
            getSupportActionBar().setTitle("项目列表");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterProject = new ProjectAdapter(Service.getAllProject(),flag, this);
        recyclerView.setAdapter(adapterProject);
    }

    public void addProject(View view){
        startActivity(new Intent(this, ProjectAddActivity.class));
    }
}