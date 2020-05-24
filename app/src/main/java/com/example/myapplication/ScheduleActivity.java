package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    TextView day;
    CustomAdapter2 adapter;
    ImageButton addSched;
    ArrayList<Schedule> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        addSched= findViewById(R.id.imageButtonAdd);
        addSched.setOnClickListener(this);
        listView = findViewById(R.id.listViewAddS);
        day=findViewById(R.id.textViewDay);

        adapter = new CustomAdapter2(this, R.layout.schedule_item, arrayList);
        listView.setAdapter(adapter);
        if (getIntent().hasExtra("schedule")) {
            Schedule schedule = (Schedule) getIntent().getSerializableExtra("schedule");
             arrayList.add(schedule);
             adapter.notifyDataSetChanged();
        }
    }
    public void onClick(View v) {
        if (v == addSched) {
            Intent i =new Intent(ScheduleActivity.this,AddScheduleDetailsActivity.class);
            startActivity(i);
        }
    }
}
