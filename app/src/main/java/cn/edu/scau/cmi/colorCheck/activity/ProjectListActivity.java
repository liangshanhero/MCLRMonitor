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
import cn.edu.scau.cmi.colorCheck.asyncTask.ProjectAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.MySqlServiceAsyncTask;

public class ProjectListActivity extends AppCompatActivity {

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    private  RecyclerView recyclerView;

    private  ProjectAdapter projectAdapter;
    public ProjectAdapter getProjectAdapter() {
        return projectAdapter;
    }

    public void setProjectAdapter(ProjectAdapter projectAdapter) {
        this.projectAdapter = projectAdapter;
    }


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
//        TODO 从数据库中获取姓名，不用本地数据库
//        projectAdapter = new ProjectAdapter(SqlLiteService.getAllProject(),flag, this);
// TODO       网络获取数据，应该使用AsynaTask方法，有可能包下面的错误
// java.lang.RuntimeException: Unable to resume activity               android.os.NetworkOnMainThreadException
//        TODO 暂时使用各自的Task，待优化到一个通用异步任务类

        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(ProjectListActivity.this);
        projectAsyncTask.execute();

//        projectAdapter = new ProjectAdapter(MySqlServiceAsyncTask.getAllProject(),flag, this);
        recyclerView.setAdapter(projectAdapter);
    }
    public void addProject(View view){
        startActivity(new Intent(this, ProjectAddActivity.class));
    }
}
