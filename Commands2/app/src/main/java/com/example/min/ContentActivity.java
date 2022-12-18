package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.min.DatabaseHelp.RecordDatabaseHelp;
import com.example.min.Model.Commands;
import com.example.min.Model.Record;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    ImageView back;
    TextView title,content;
    RecordDatabaseHelp dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        dbHelper = new RecordDatabaseHelp(ContentActivity.this);
        back = findViewById(R.id.back_icon);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String title1 = intent.getStringExtra("title");
        //遍历找到这个命令
        ArrayList<Record> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Record record = data.get(i);
            if(title1.equals(record.getTitle())){
                title.setText(record.getTitle());
                content.setText(record.getContent());
                break;
            }
        }
    }
}