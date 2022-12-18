package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.min.DatabaseHelp.RecordDatabaseHelp;

public class PlusActivity extends AppCompatActivity {
    ImageView back;
    EditText title,content;
    Button insert;
    RecordDatabaseHelp dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        back = findViewById(R.id.back_icon);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        insert = findViewById(R.id.insert);
        dbHelper = new RecordDatabaseHelp(PlusActivity.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlusActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title1 = title.getText().toString().trim();
                String content1 = content.getText().toString().trim();
                //判断编辑框里的字符串是否为空
                if(!TextUtils.isEmpty(title1)&&!TextUtils.isEmpty(content1)){
                    dbHelper.add(title1,content1);
                    Toast.makeText(PlusActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlusActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(PlusActivity.this,"请输入完整标题或内容",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}