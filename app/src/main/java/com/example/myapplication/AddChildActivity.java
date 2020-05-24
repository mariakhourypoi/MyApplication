package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddChildActivity extends AppCompatActivity implements View.OnClickListener {
Button addButton;

//TextView textView;
ImageButton cancelButton,goSchedule;
EditText name,birthday,homeAdress,schoolAdress;
private TimePicker timePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        goSchedule=findViewById(R.id.imageButtonGoSched);
        goSchedule.setOnClickListener(this);
        addButton=findViewById(R.id.addChildButton);
        addButton.setOnClickListener(this);
        name = findViewById(R.id.editTextName);
        birthday=findViewById(R.id.editTextBirthday);
        homeAdress=findViewById(R.id.editTextHomeAdress);
        schoolAdress=findViewById(R.id.editTextSchoolAdress);
        //textView=(TextView) findViewById(R.id.textViewName);
        cancelButton=findViewById(R.id.cancelAddingButton);
        cancelButton.setOnClickListener(this);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);

        }


    public void onClick(View v) {

            if(v==addButton)
            {
            String addchild = name.getText().toString();
            String addbirthday = birthday.getText().toString();
            String addHomeAdress=schoolAdress.getText().toString();
            String addSchoolAdress=homeAdress.getText().toString();

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("childName", addchild);
            i.putExtra("birthday",addbirthday);
            i.putExtra("SchoolAdress",addSchoolAdress);
            i.putExtra("homeAdress",addHomeAdress);
            startActivity(i);
            }
            if(v==cancelButton)
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            if(v==goSchedule)
            {
                Intent i=new Intent(this,AddScheduleDetailsActivity.class);
                startActivity(i);
            }
    }
}
