package cn.edu.scau.cmi.colorCheck.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.activity.toBeDone.MachineLearningRuleActivity;
import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.activity.toBeDone.CustomizeRuleActivity;
import cn.edu.scau.cmi.colorCheck.activity.collect.PointSampleCollectActivity;
import cn.edu.scau.cmi.colorCheck.olderVersion.dao.sqlLite.SqlLiteService;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Target;

/**
 * Created by Mr_Chen on 2018/6/6.
 */

public class SqlLiteProjectAdapter extends RecyclerView.Adapter<SqlLiteProjectAdapter.ViewHolder>{

    List<Project> projectList;
    Context context;
    int flag;
    public SqlLiteProjectAdapter(List<Project> projectList, int flag, Context context) {
        this.projectList = projectList;
        this.flag = flag;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView project;
        TextView memo;
        TextView dataDB;
        TextView deleteBtn;
        TextView editBtn;
        TextView ruleBtn;
        LinearLayout btnGroup;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            project = itemView.findViewById(R.id.project_name);
            memo = itemView.findViewById(R.id.project_memo);
            dataDB = itemView.findViewById(R.id.project_db);
            deleteBtn = itemView.findViewById(R.id.project_delete);
            editBtn = itemView.findViewById(R.id.project_edit);
            ruleBtn = itemView.findViewById(R.id.project_rule);
            btnGroup = itemView.findViewById(R.id.project_btn_group);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Project project = projectList.get(position);
        holder.project.setText(project.name);
        holder.memo.setText(project.memo);
        String type = SqlLiteService.getCheckType(project.type_id);
        if("定性".equals(type)){
            type += "：";
            List<Target> list =  SqlLiteService.getProjectTarget(project);
            for(Target target : SqlLiteService.getProjectTarget(project)){
                type += target.name+" ";
            }
            holder.dataDB.setText(type);
        }else{
            holder.dataDB.setText(type);
        }


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               projectList.remove(position);
               SqlLiteService.deleteProject(project);
               notifyDataSetChanged();
            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        holder.ruleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = new RecyclerView(context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                //TODO
                List<Rule> list = SqlLiteService.getRulesOfProject(project);
                RuleAdapter adapterRule = new RuleAdapter(list,context);
                recyclerView.setAdapter(adapterRule);
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setView(recyclerView)
                        .setPositiveButton("确定",null)
                        .setNegativeButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(holder.dataDB.getText().toString().equals("定量")){
                                    Intent intent = new Intent(context, CustomizeRuleActivity.class);
                                    intent.putExtra("projectId", projectList.get(position).id);
                                    context.startActivity(intent);
                                }
                            }
                        })
                        .show();
            }
        });
        if(flag != 0){
            holder.btnGroup.setVisibility(View.GONE);
        }
        //样本数据收集
        if(flag == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PointSampleCollectActivity.class);
                    intent.putExtra("projectId",project.id);
                    intent.putExtra("projectName",project.name+"_样本采集");
                    intent.putExtra("type",SqlLiteService.getCheckType(project.type_id));
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
        }
        //机器规则生成
        if(flag == 2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,MachineLearningRuleActivity.class);
                    intent.putExtra("projectName",project.name);
                    intent.putExtra("projectId",project.id);
                    intent.putExtra("type",SqlLiteService.getCheckType(project.type_id));
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
