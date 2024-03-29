package cn.edu.scau.cmi.colorCheck.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Sample;

/**
 * Created by Mr_Chen on 2018/6/6.
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder>{

    List<Sample> sampleList;
    Context context;

    public SampleAdapter(List<Sample> sampleList, Context context) {
        this.sampleList = sampleList;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView red;
        TextView green;
        TextView blue;
        TextView result;

        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            red = itemView.findViewById(R.id.collection_red);
            green = itemView.findViewById(R.id.collection_green);
            blue = itemView.findViewById(R.id.collection_blue);
            result = itemView.findViewById(R.id.collection_result);
            background = itemView.findViewById(R.id.collection_background);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collected_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Sample sample = sampleList.get(position);
        holder.red.setText(sample.red+"");
        holder.green.setText(sample.green+"");
        holder.blue.setText(sample.blue+"");
        if(sample.getTarget()!=null){
            holder.result.setText(sample.getTarget());
        }else{
            holder.result.setText(sample.result+"");
        }
        if(position % 2 ==0){
            holder.background.setBackgroundColor(0xdfdbd);
        }
    }

    @Override
    public int getItemCount() {
        return sampleList.size();
    }
}