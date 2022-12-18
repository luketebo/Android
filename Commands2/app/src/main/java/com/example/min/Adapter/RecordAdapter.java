package com.example.min.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.min.Model.Commands;
import com.example.min.Model.Record;
import com.example.min.R;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {
    List<Record> objects;
    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects){
        super(context,resource,objects);
        this.objects = objects;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Record getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Record record = getItem(position); //得到当前项的 Commands 实例
        //为每个子项加载预定的布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.record_item,parent,false);
        //分别获取 textview 实例
        TextView recordtitle = view.findViewById(R.id.record_title);
        //设置要显示的图片和文字
        recordtitle.setText(record.getTitle());
        return view;
    }
}
