package cn.edu.scau.cmi.colorCheck.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.scau.cmi.colorCheck.R;

/**
 * Created by Mr_Chen on 2018/6/29.
 * 添加定性项目的时候，定义检测结果取值的UI组件
 */

public class RangeLinearLayout extends LinearLayout{
    private EditText editText;
    private TextView delete;
    private DeleteListener listener;

    public interface DeleteListener{
        void deleteRange(RangeLinearLayout layout);
    }
    public RangeLinearLayout(Context context, final DeleteListener listener) {
        super(context);
        this.listener = listener;
        LayoutInflater.from(context).inflate(R.layout.range_item,this);
        editText = findViewById(R.id.range);
        delete = findViewById(R.id.range_delete);
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteRange(RangeLinearLayout.this);
            }
        });
    }
    public String getRange(){
        return editText.getText().toString();
    }

}
