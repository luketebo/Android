package com.example.min;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.min.Adapter.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

public class Login_signupActivity extends AppCompatActivity {
    TabLayout tabLayout;//页面切换指示器
    ViewPager viewPager;//页面切换组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));//向此布局添加选项卡
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);//设置当前的标签tab的布局方式：GRAVITY_FILL （内容尽可能充满TabLayout）

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);//给分页器加上适配器
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));//viewpager和tablayout互相绑定
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) );
    }
}