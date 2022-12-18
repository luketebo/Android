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
    ImageView back_icon;
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

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        ArrayList<Commands> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Commands commands = data.get(i);
            if(name1.equals(commands.getName())){
                name.setText(commands.getName());
                title.setText(commands.getName());
                details.setText(commands.getDetails());
                grammar.setText(commands.getGrammar());
                param.setText(commands.getParam());
                example.setText(commands.getExample());
                break;
            }
        }
    }
}