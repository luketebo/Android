package top.luketebo.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button btn_disable = findViewById(R.id.btn_disable);
        Button btn_enable =  findViewById(R.id.btn_enable);
        Button btn_test =  findViewById(R.id.btn_test);
        TextView tv_test  =   findViewById(R.id.tv_test);

        btn_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_test.setEnabled(false);
            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc  = "demo";
                tv_test.setText(desc);
            }
        });

        btn_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_test.setEnabled(true);
            }
        });

    }
}