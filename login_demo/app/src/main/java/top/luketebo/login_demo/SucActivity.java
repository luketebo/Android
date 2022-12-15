package top.luketebo.login_demo;


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

public class SucActivity extends AppCompatActivity {

    DataBase db;
    TextView text;
    Button logout, change, find, back;
    EditText explore;
    String word, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suc);

        db = new DataBase(SucActivity.this);
        text = findViewById(R.id.tv_SC_show);
        explore = findViewById(R.id.explore);
        logout = findViewById(R.id.bt_SC_logout);
        change = findViewById(R.id.bt_SC_change);
        find = findViewById(R.id.bt_SC_find);
        back = findViewById(R.id.bt_SC_back);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        ArrayList<User> users = db.getAllData();
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            if(name.equals(user.getName())){
                password = user.getPassword();
                text.setText("登录数据："+"\n"+"用户名："+name+"\n"+"密码："+password);
                break;
            }
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> data = db.getAllData();
                boolean userdata = false;
                for(int i = 0;i<data.size();i++){
                    User user = data.get(i);
                    if(name.equals(user.getName())&&password.equals(user.getPassword())){
                        userdata = true;
                        break;
                    }else userdata = false;
                }if(userdata){
                    db.delete(name,password);
                    Toast.makeText(SucActivity.this,"注销成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SucActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SucActivity.this,UpdateActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = explore.getText().toString().trim();
                text.setText(username);
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SucActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                ArrayList<User> data = db.getAllData();
                boolean userdata = false;
                for(int i = 0;i<data.size();i++){
                    User user = data.get(i);
                    if(username.equals(user.getName())){
                        userdata = true;
                        word = user.getPassword();
                        break;
                    }else userdata = false;
                }
                if(userdata){
                    text.setText("登录数据："+"\n"+"用户名："+username+"\n"+"密码："+word);
                    Toast.makeText(SucActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(SucActivity.this,"没有此用户" ,Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SucActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}