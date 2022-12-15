package top.luketebo.login_demo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DataBase db;
    EditText oldWord, newWord;
    TextView name, test;
    Button ready, back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = new DataBase(UpdateActivity.this);
        name = findViewById(R.id.et_Up_userName);
        oldWord = findViewById(R.id.et_Up_keyWordA);
        newWord = findViewById(R.id.et_Up_keyWordB);
        ready = findViewById(R.id.bt_Up_Login);
        back = findViewById(R.id.bt_Up_Reg);
        test = findViewById(R.id.test_tv);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        name.setText(username);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this,SucActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = oldWord.getText().toString().trim();
                String newPass = newWord.getText().toString().trim();
                if(TextUtils.isEmpty(oldPass) && TextUtils.isEmpty(newPass)){
                    Toast.makeText(UpdateActivity.this, "选项不能为空", Toast.LENGTH_SHORT).show();
                }
                ArrayList<User> data = db.getAllData();
                boolean userdata = false;
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);
                    if(username.equals(user.getName()) && oldPass.equals(user.getPassword())){
                        userdata = true;
                        break;
                    }
                }
                if (!userdata) {
                    Toast.makeText(UpdateActivity.this,"旧密码不正确",Toast.LENGTH_SHORT).show();
                }else if (newPass.equals(oldPass)){
                    Toast.makeText(UpdateActivity.this,"新旧密码不能相同",Toast.LENGTH_SHORT).show();
                }else if (userdata) {
                    db.update(username, newPass);
                    Toast.makeText(UpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this,SucActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}