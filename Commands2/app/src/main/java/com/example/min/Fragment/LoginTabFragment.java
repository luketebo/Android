package com.example.min.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.min.DatabaseHelp.UserDatabaseHelp;
import com.example.min.HomeActivity;
import com.example.min.Model.User;
import com.example.min.R;

import java.util.ArrayList;

public class LoginTabFragment extends Fragment {
    Context context;
    UserDatabaseHelp dbHelper;
    EditText username,password;
    TextView change;
    Button login;
    float v = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login_tab,container,false);//将 XML 文件实例化为 View

        super.onCreate(savedInstanceState);
        this.context = getActivity();//返回和此fragment绑定的Activity
        dbHelper = new UserDatabaseHelp(context);//通过创建子类继承SQLiteOpenHelper类，对数据库进行操作
        change = root.findViewById(R.id.change);
        password = root.findViewById(R.id.pass);
        username = root.findViewById(R.id.username);
        login = root.findViewById(R.id.login);

        username.setTranslationY(800);
        password.setTranslationY(800);
        change.setTranslationY(800);
        login.setTranslationY(800);//设置初始位置

        username.setAlpha(v);
        password.setAlpha(v);//不透明度设置为零
        login.setAlpha(v);
        change.setAlpha(v);

        username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(0).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(0).start();
        change.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(0).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(0).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取编辑框里面的字符串
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                //判断编辑框里的字符串是否为空，若为空则Toast"用户名或密码不能为空"
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)){
                    //声明一个User类的数组通过dbHelper.getAll()放入数据库里的所有数据
                    ArrayList<User> data = dbHelper.getAll();
                    //声明userdata用于判断是否有匹配的条件
                    boolean userdata = false;
                    //遍历data数组，比较是否有名字和密码都匹配的数据
                    for(int i=0;i<data.size();i++){
                        User user = data.get(i);
                        if(name.equals(user.getName())&&pass.equals(user.getPassword())){
                            userdata = true;
                            break;
                        }else userdata = false;
                    }
                    if (userdata){
                        Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, HomeActivity.class);
                        intent.putExtra("username",name);
                        intent.putExtra("password",pass);
                        startActivity(intent);
                    }else Toast.makeText(context,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
