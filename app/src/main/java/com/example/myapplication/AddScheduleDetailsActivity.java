package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddScheduleDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button set;
    ImageButton start;
    ImageButton end;
    TextView day;
    ImageButton back;
    TextView startSelect;
    TextView endSelect;


    Schedule schedule = new Schedule();

    //create a list of items for the spinner.

    String[] days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_details);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
       // dropdown.setBackgroundResource(R.drawable.edt_bkgd);

        days = getResources().getStringArray(R.array.week_days);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        start = findViewById(R.id.buttonStarting);
        start.setOnClickListener(this);

        end = findViewById(R.id.buttonEnding);
        end.setOnClickListener(this);

        set = findViewById(R.id.buttonSet);
        set.setOnClickListener(this);

        back=findViewById(R.id.imageButtonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleDetailsActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });
        startSelect=findViewById(R.id.textViewStartSelect);
        endSelect=findViewById(R.id.textViewEndSelect);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), days[position], Toast.LENGTH_LONG).show();
        schedule.setDay(days[position]);



    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        schedule.setDay(days[0]);
    }
    public void onClick(View v)   {
        if(v==start)
        {
            final Calendar c=Calendar.getInstance();
            int mHour=c.get(Calendar.HOUR_OF_DAY);
            int mMinutes=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddScheduleDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                           // eReminderTime.setText( selectedHour + ":" + selectedMinute);
                            Toast.makeText(getApplicationContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                            schedule.setTimeScheduleStart(new Time(selectedHour, selectedMinute));
                            startSelect.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, mHour, mMinutes, true);//Yes 24 hour time
            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();

        }
        else if(v==end)
        {
            final Calendar c=Calendar.getInstance();
            int mHour=c.get(Calendar.HOUR_OF_DAY);
            int mMinutes=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddScheduleDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    Toast.makeText(getApplicationContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                    schedule.setTimeScheduleEnd(new Time(selectedHour, selectedMinute));
                    endSelect.setText(selectedHour + ":" + selectedMinute);

                }
            }, mHour, mMinutes, true);//Yes 24 hour time
            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();

        }else if (v == set){
            if(schedule.getTimeScheduleEnd() != null && schedule.getTimeScheduleStart()!=null ) {
                Intent i = new Intent(this, ScheduleActivity.class);
                i.putExtra("schedule", schedule);
                startActivity(i);
            }

        }

    }


}
