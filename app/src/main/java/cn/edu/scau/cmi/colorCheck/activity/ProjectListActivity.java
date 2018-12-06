package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.adapter.MySqlProjectAdapter;
import cn.edu.scau.cmi.colorCheck.adapter.SqlLiteProjectAdapter;
import cn.edu.scau.cmi.colorCheck.asyncTask.ProjectAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;

public class ProjectListActivity extends AppCompatActivity {
    private  RecyclerView recyclerView;
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    private MySqlProjectAdapter mySqlProjectAdapter;
    public MySqlProjectAdapter getMySqlProjectAdapter() {
        return mySqlProjectAdapter;
    }
    public void setMySqlProjectAdapter(MySqlProjectAdapter mySqlProjectAdapter) {
        this.mySqlProjectAdapter = mySqlProjectAdapter;
    }


    Button delBtn;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        （1）界面组装
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        recyclerView = findViewById(R.id.project_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        delBtn = findViewById(R.id.project_del);

//      （2）  项目创建的时候就从后台异步获取项目列表，并获取的所有项目设置到对应的
//TODO 初始化界面的时候，从网络获取，利用异步任务，成功获取数据。
        ProjectAsyncTask projectAsyncTask=new ProjectAsyncTask(this, new ProjectAsyncTask.HttpFinishedListener(){
            @Override
            public void doSomething(List<Project> projectList) {
                //TODO 修改listAdapter
                for(Project project:projectList){
                    System.out.println("通过网络获取的数据是："+project.getName());
                }
                Toast.makeText(ProjectListActivity.this, "返回到了ProjectListActivity", Toast.LENGTH_SHORT).show();
                mySqlProjectAdapter = new MySqlProjectAdapter(projectList,1, ProjectListActivity.this);
                recyclerView.setAdapter(mySqlProjectAdapter);
            }
            @Override
            public void doNothing() {
            }
        });
        projectAsyncTask.execute();

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
//        mySqlProjectAdapter = new SqlLiteProjectAdapter(SqlLiteService.getAllProject(),flag, this);
// TODO       网络获取数据，应该使用AsynaTask方法，有可能包下面的错误
// java.lang.RuntimeException: Unable to resume activity               android.os.NetworkOnMainThreadException
//        TODO 暂时使用各自的Task，待优化到一个通用异步任务类


//        mySqlProjectAdapter = new SqlLiteProjectAdapter(MySqlServiceAsyncTask.getAllProject(),flag, this);
        recyclerView.setAdapter(mySqlProjectAdapter);
    }
    public void addProject(View view){
        startActivity(new Intent(this, ProjectAddActivity.class));
    }
}
