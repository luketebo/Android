package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.min.Adapter.CommandAdapter;
import com.example.min.DatabaseHelp.ComDatabaseHelp;
import com.example.min.Model.Commands;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {
    ImageView back;
    ListView listView;
    ComDatabaseHelp dbHelper;
    Integer yes = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        back = findViewById(R.id.back_icon);
        listView = findViewById(R.id.list_view);
        dbHelper = new ComDatabaseHelp(CollectActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollectActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        List<Commands> comlist = new ArrayList<>();
        ArrayList<Commands> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Commands commands = data.get(i);
            if(yes.equals(commands.getCollect())){
                comlist.add(commands);
            }
        }
        CommandAdapter adapter = new CommandAdapter(CollectActivity.this,R.layout.commands_item,comlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//重写
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Commands commands= comlist.get(position) ;//获取点击的那个item
                Intent intent = new Intent(CollectActivity.this, ExplainActivity.class);
                intent.putExtra("name",commands.getName());
                startActivity(intent);
            }
        });
    }
}