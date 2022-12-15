package top.luketebo.login_demo;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegActivity extends AppCompatActivity {

    EditText username, password;
    Button ready, back;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        db = new DataBase(RegActivity.this);
        username = findViewById(R.id.et_RG_userName);
        password = findViewById(R.id.et_RG_keyWord);
        ready = findViewById(R.id.bt_RG_Login);
        back = findViewById(R.id.bt_RG_Reg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String word = password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(word)){
                    db.add(name, word);
                    Intent intent = new Intent(RegActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}