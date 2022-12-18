package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.min.DatabaseHelp.ComDatabaseHelp;
import com.example.min.Model.Commands;

import java.util.ArrayList;

public class ExplainActivity extends AppCompatActivity {
    ComDatabaseHelp dbHelper;
    TextView name,details,grammar,param,example,title;
    ImageView back_icon,collect;
    Commands command;
    Integer no=0,yes=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        dbHelper = new ComDatabaseHelp(ExplainActivity.this);
        name = findViewById(R.id.name);
        details = findViewById(R.id.details);
        grammar = findViewById(R.id.grammar);
        param = findViewById(R.id.param);
        example = findViewById(R.id.example);
        back_icon = findViewById(R.id.back_icon);
        title = findViewById(R.id.title);
        collect = findViewById(R.id.collect);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //获取传递过来的信息
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        //遍历找到这个命令
        ArrayList<Commands> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Commands commands = data.get(i);
            if(name1.equals(commands.getName())){
                command = commands;
                name.setText(commands.getName());
                title.setText(commands.getName());
                details.setText(commands.getDetails());
                grammar.setText(commands.getGrammar());
                param.setText(commands.getParam());
                example.setText(commands.getExample());
                if(no.equals(commands.getCollect())){
                    collect.setImageResource(R.drawable.uncollect);
                }else if (yes.equals(commands.getCollect())){
                    collect.setImageResource(R.drawable.collect);
                }
                break;
            }
        }

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Commands> data = dbHelper.getAll();
                for(int i=0;i<data.size();i++){
                    Commands commands = data.get(i);
                    if(name1.equals(commands.getName())){
                        if(no.equals(commands.getCollect())){
                            collect.setImageResource(R.drawable.collect);
                            dbHelper.update(commands.getName(),1);
                        }else if (yes.equals(commands.getCollect())){
                            collect.setImageResource(R.drawable.uncollect);
                            dbHelper.update(commands.getName(),0);
                        }
                        break;
                    }
                }
            }
        });
    }
}