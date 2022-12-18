package com.example.min.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.min.DatabaseHelp.UserDatabaseHelp;
import com.example.min.R;

public class SignupTabFragment  extends Fragment {
    Context context;
    UserDatabaseHelp dbHelper;
    EditText email,username,password,confirm;
    Button signup;
    float v = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_signup_tab,container,false);

        dbHelper = new UserDatabaseHelp(context);
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.Pass);
        email = root.findViewById(R.id.email);
        confirm = root.findViewById(R.id.confirm);
        signup = root.findViewById(R.id.signup);

        email.setTranslationY(800);
        password.setTranslationY(800);
        username.setTranslationY(800);
        confirm.setTranslationY(800);
        signup.setTranslationY(800);//设置初始位置

        email.setAlpha(v);
        password.setAlpha(v);//不透明度设置为零
        username.setAlpha(v);
        confirm.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        confirm.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        signup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emil = email.getText().toString().trim();
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confir = confirm.getText().toString().trim();
                if(!TextUtils.isEmpty(emil)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(confir)){
                    if(!confir.equals(pass)){
                        Toast.makeText(context,"两次密码不一样，请重新输入",Toast.LENGTH_SHORT).show();
                    }else {
                        dbHelper.add(emil,name,pass);
                        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }
}