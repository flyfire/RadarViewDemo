package com.solarexsoft.radarviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.solarexsoft.radarview.RadarData;
import com.solarexsoft.radarview.RadarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadarView radarView = (RadarView) findViewById(R.id.radarView);

        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setData(dataList);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    public void test(int count){
        RadarView radarView = (RadarView) findViewById(R.id.radarView);

        List<RadarData> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            RadarData data = new RadarData("标题" + (i+1), (i+1)*11);
            dataList.add(data);
        }
        radarView.setData(dataList);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        if (viewid == R.id.btn3) {
            test(3);
        } else if (viewid == R.id.btn4) {
            test(4);
        } else if (viewid == R.id.btn5) {
            test(5);
        } else if (viewid == R.id.btn6) {
            test(6);
        } else if (viewid == R.id.btn7) {
            test(7);
        } else if (viewid == R.id.btn8) {
            test(8);
        } else if (viewid == R.id.btn9) {
            test(9);
        }
    }
}
