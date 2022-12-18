package com.example.min.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.example.min.Adapter.CommandAdapter;
import com.example.min.DatabaseHelp.ComDatabaseHelp;
import com.example.min.ExplainActivity;
import com.example.min.Model.Commands;
import com.example.min.R;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    Context context;
    ListView lv;
    EditText et;
    ImageButton find;
    ComDatabaseHelp dbHelper;
    List<Commands> comlist;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_find,container,false);
        super.onCreate(savedInstanceState);

        this.context = getActivity();
        lv = root.findViewById(R.id.lv);
        et = root.findViewById(R.id.et);
        find = root.findViewById(R.id.find);
        dbHelper = new ComDatabaseHelp(context);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et.getText().toString().trim();
                ArrayList<Commands> data = new ArrayList<>();
                data = dbHelper.getAll();
                comlist = new ArrayList<>();
                boolean datause = false;
                if(!TextUtils.isEmpty(str)){
                    for(int i=0;i<data.size();i++){
                        Commands commands = data.get(i);
                        if(commands.getName().contains(str)){
                            datause = true;
                            comlist.add(commands);
                        }
                    }
                    if(datause) {
                        lv.setAdapter(new CommandAdapter(context,R.layout.commands_item,comlist));
                        Toast.makeText(context, "查找成功", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(context, "不存在这个命令，请重新输入", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, "请输入要查找的信息", Toast.LENGTH_SHORT).show();
            }
        });
        //listview 的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Commands commands= comlist.get(position) ;
                Intent intent = new Intent(context, ExplainActivity.class);
                intent.putExtra("name",commands.getName());
                startActivity(intent);
            }
        });

        return root;
    }


}