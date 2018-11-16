package cn.edu.scau.cmi.colorCheck.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.dao.sqlLite.Service;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.RangeRule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;

/**
 * Created by Mr_Chen on 2018/6/6.
 */

public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder>{

    List<Rule> ruleList;
    Context context;

    public RuleAdapter(List<Rule> ruleList, Context context) {
        this.ruleList = ruleList;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView formula;
        TextView type;
        TextView createTime;
        TextView del;
        TextView edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rule_name);
            formula = itemView.findViewById(R.id.rule_formula);
            type = itemView.findViewById(R.id.rule_type);
            createTime = itemView.findViewById(R.id.rule_createTime);
            del = itemView.findViewById(R.id.rule_del);
            edit = itemView.findViewById(R.id.rule_edit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rule,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Rule rule = ruleList.get(position);
        holder.name.setText(rule.name);
        if (rule.getQuantitativeLinear_rule()!=null){
            holder.formula.setText(rule.getQuantitativeLinear_rule().toString());
        }else{
            StringBuilder sb = new StringBuilder();
            for(RangeRule range_rule : rule.getRange_rules()){
                sb.append(range_rule.toString()).append('\n');
            }
            sb.deleteCharAt(sb.length()-1);
            holder.formula.setText(sb.toString());
        }
        

        if(rule.type.equals("机器学习")){
            holder.type.setText(rule.type+"-->数据量："+rule.num +" 条");
        }else{
            holder.type.setText(rule.type);
        }

        holder.createTime.setText(rule.create_date);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Service.deleteRule(rule.id);
                ruleList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ruleList.size();
    }
}
