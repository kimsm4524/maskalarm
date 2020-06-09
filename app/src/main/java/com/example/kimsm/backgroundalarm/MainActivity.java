package com.example.kimsm.backgroundalarm;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnStart, btnEnd;
    private LinearLayout layout;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btn_start);
        btnEnd = (Button)findViewById(R.id.btn_end);
        textview = (TextView)findViewById(R.id.textView);
        layout = (LinearLayout)findViewById(R.id.masklayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smartstore.naver.com/mdamayo/products/3803685971"));
                startActivity(intent);
            }
        });
        if(isServiceRunningCheck())
        {
            textview.setText("알람 서비스 ON");
        }else
        {
            textview.setText("알람 서비스 OFF");
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 시작",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
                if(isServiceRunningCheck())
                {
                    textview.setText("알람 서비스 ON");
                }else
                {
                    textview.setText("알람 서비스 OFF");
                }
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 끝",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
                if(isServiceRunningCheck())
                {
                    textview.setText("알람 서비스 ON");
                }else
                {
                    textview.setText("알람 서비스 OFF");
                }
            }
        });
    }
    public boolean isServiceRunningCheck() {
        ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.kimsm.backgroundalarm.MyService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}