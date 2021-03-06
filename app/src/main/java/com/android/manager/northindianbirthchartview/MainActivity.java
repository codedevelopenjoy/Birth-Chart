package com.android.manager.northindianbirthchartview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int lagna = 10;
        ArrayList<String> list = new ArrayList<>();
        list.add("Ju");
        list.add("Ke");
        list.add("Sa Mo");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("Ra");
        list.add("Me Su");
        list.add("");
        list.add("Ma Ve");
        list.add("");

        NorthIndianBirthChartView nic = findViewById(R.id.nic);
        nic.setPlanets(lagna,list);


    }
}
