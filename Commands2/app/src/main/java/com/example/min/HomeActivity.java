package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;


import com.example.min.Fragment.BookmarkFragment;
import com.example.min.Fragment.FindFragment;
import com.example.min.Fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    View home,find,bookmark,github,theme,details;
    NavigationView navigationView;
    DrawerLayout drawerLayout;//抽屉
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home_view,new HomeFragment())   // 此处的R.id.home_view是要盛放fragment的父容器
                .commit();

        // 通过id获取按钮
        home = findViewById(R.id.homeFragment);
        find = findViewById(R.id.findFragment);
        bookmark = findViewById(R.id.bookmarkFragment);
        github = findViewById(R.id.githubFragment);
        theme = findViewById(R.id.themeFragment);
        details = findViewById(R.id.detailsFragment);
        navigationView = findViewById(R.id.nav_view_left);
        drawerLayout = findViewById(R.id.drawer_layout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()   //所得到的是所在fragment 的父容器的管理器
                        .beginTransaction()
                        .addToBackStack(null)   //添加到后退栈
                        //此处的id，是FrameLayout的id
                        .replace(R.id.home_view,new HomeFragment())   //anotherFragment()为新建的fragment的java文件，OnCreateView里面把.xml文件添加
                        .commit();
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()   //所得到的是所在fragment 的父容器的管理器
                        .beginTransaction()
                        .addToBackStack(null)   //添加到后退栈
                        //此处的id，是FrameLayout的id
                        .replace(R.id.home_view,new FindFragment())   //anotherFragment()为新建的fragment的java文件，OnCreateView里面把.xml文件添加
                        .commit();
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()   //所得到的是所在fragment 的父容器的管理器
                        .beginTransaction()
                        .addToBackStack(null)   //添加到后退栈
                        //此处的id，是FrameLayout的id
                        .replace(R.id.home_view,new BookmarkFragment())   //anotherFragment()为新建的fragment的java文件，OnCreateView里面把.xml文件添加
                        .commit();
            }
        });

        //给navigation对象添加项目选择监视器，根据点击的项目不同，所显示的消息不同
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.githubFragment:
                        Uri uri = Uri.parse("https://github.com/zzzzzsy-blue/AndroidCommands.git");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        drawerLayout.closeDrawers();//响应后侧边栏关闭
                        break;
                    case R.id.themeFragment:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.detailsFragment:
                        Intent intent1 = new Intent(HomeActivity.this,DetailsActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawers();
                        break;
                    case  R.id.logout:
                        Intent intent2 = new Intent(HomeActivity.this,Login_signupActivity.class);
                        startActivity(intent2);
                        finish();
                        drawerLayout.closeDrawers();
                }

                return false;
            }
        });

    }
}