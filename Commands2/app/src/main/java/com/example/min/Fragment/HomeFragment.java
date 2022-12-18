package com.example.min.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.min.Adapter.CommandAdapter;
import com.example.min.DatabaseHelp.ComDatabaseHelp;
import com.example.min.Model.Commands;
import com.example.min.R;
import com.example.min.ExplainActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ListView listView;
    Context context;
    ComDatabaseHelp dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
        super.onCreate(savedInstanceState);
        //获取控件
        listView = root.findViewById(R.id.list_view);
        this.context = getActivity();//返回和此fragment绑定的Activity
        dbHelper = new ComDatabaseHelp(context);//通过创建子类继承SQLiteOpenHelper类，对数据库进行操作

        //准备一个集合用于存放数据库中的数据映射到listview上
        List<Commands> comlist = new ArrayList<>();
        ArrayList<Commands> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Commands command = data.get(i);
            comlist.add(command);
        }
        CommandAdapter adapter = new CommandAdapter(context,R.layout.commands_item,comlist);//上下文，listview每个item的布局，数据
        listView.setAdapter(adapter);
        //listview 的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//重写
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Commands commands= comlist.get(position) ;//获取点击的那个item
                Intent intent = new Intent(context, ExplainActivity.class);
                intent.putExtra("name",commands.getName());
                startActivity(intent);
            }
        });
        return root;
    }
}