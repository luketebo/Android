package top.luketebo.login_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBase db;
    EditText username, password;
    Button reg, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase(MainActivity.this);
        username = findViewById(R.id.et_userName);
        password = findViewById(R.id.et_keyWord);
        reg = findViewById(R.id.bt_Reg);
        login = findViewById(R.id.bt_Login);

        ArrayList<User> data = db.getAllData();
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            System.out.println(user.getName());
            System.out.println(user.getPassword());
        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = username.getText().toString().trim();
                String password = MainActivity.this.password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
                    ArrayList<User> data =  db.getAllData();
                    boolean userdata = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())){
                            userdata = true;
                            break;
                        }else userdata = false;
                    }
                    if(userdata) {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SucActivity.class);
                        intent.putExtra("username", name);
                        startActivity(intent);
                        finish();
                    }else Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();

            }
        });


    }

}