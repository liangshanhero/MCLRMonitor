package cn.edu.scau.cmi.colorCheck.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.adapter.MySqlProjectAdapter;
import cn.edu.scau.cmi.colorCheck.asyncTask.ItemAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Item;

public class ProjectActivity extends AppCompatActivity {
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

//      （2）  项目创建的时候就从后台异步获取项目列表，并获取的所有项目设置到对应的，// 初始化界面，利用异步任务，从网络获取数据。
        ItemAsyncTask itemAsyncTask =new ItemAsyncTask(this, new ItemAsyncTask.HttpFinishedListener(){
            @Override
            public void doSomething(List<Item> projectList) {
                Log.e("获取的项目是：",projectList.toString());
                mySqlProjectAdapter = new MySqlProjectAdapter(projectList,1, ProjectActivity.this);
                recyclerView.setAdapter(mySqlProjectAdapter);
            }
            @Override
            public void doNothing() {
            }
        });
        itemAsyncTask.execute();
// （3）工具栏上显示相关的菜单栏
        flag = getIntent().getIntExtra("flag",0);
        if(flag != 0){
            delBtn.setVisibility(View.GONE);
            getSupportActionBar().setTitle("请选择一个项目");
        }else{
            getSupportActionBar().setTitle("项目列表");
        }
    }
// 界面再次返回到前端，例如，有可能是添加了项目后返回到这里
    @Override
    protected void onResume() {
        super.onResume();
//TODO 需要重新访问服务器，获取最新的项目列表
//        mySqlProjectAdapter = new SqlLiteProjectAdapter(MySqlServiceAsyncTask.getAllItemList(),flag, this);
        recyclerView.setAdapter(mySqlProjectAdapter);
    }
    public void addProject(View view){
        startActivity(new Intent(this, ProjectAddActivity.class));
    }
//TODO 获取所有的项目，带后面完善直接获取
    public void getAllProject(View view){
        ItemAsyncTask itemAsyncTask =new ItemAsyncTask(this, new ItemAsyncTask.HttpFinishedListener(){
            @Override
            public void doSomething(List<Item> projectList) {
                //TODO 修改listAdapter
                for(Item project:projectList){
                    System.out.println("通过网络获取的数据是："+project.getName());
                }
                Toast.makeText(ProjectActivity.this, "返回到了ProjectListActivity", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void doNothing() {
            }
        });
        itemAsyncTask.execute();
    }
}