package com.solarexsoft.radarviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.solarexsoft.radarview.RadarData;
import com.solarexsoft.radarview.RadarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    }

    public void do3Test(View v){
        RadarView radarView = (RadarView) findViewById(R.id.radarView);

        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setData(dataList);
    }

    public void do6Test(View v){
        RadarView radarView = (RadarView) findViewById(R.id.radarView);

        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setData(dataList);
    }

    public void do9Test(View v){
        RadarView radarView = (RadarView) findViewById(R.id.radarView);

        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setData(dataList);
    }
}
