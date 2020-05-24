package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 ListView listView;
 CustomAdapter adapter;
 ArrayList<Notification> arrayList;
 TextView name,birthday,homeAdress,schoolAdress ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.textViewChildName1);
        birthday=findViewById(R.id.textViewBir);
        homeAdress=findViewById(R.id.textViewHomeAdress);
        schoolAdress=findViewById(R.id.textViewSchoolAdress);


        listView=findViewById(R.id.listViewChildren);
        arrayList=new ArrayList<Notification>();

        adapter =new CustomAdapter (this,R.layout.notification_item,arrayList);
        listView.setAdapter(adapter);

       if (getIntent().hasExtra("childName")&&getIntent().hasExtra("birthday")&&getIntent().hasExtra("homeAdress")&&getIntent().hasExtra("SchoolAdress")) {
           String name = getIntent().getStringExtra("childName");
           String birthday = getIntent().getStringExtra("birthday");
           String homeAdress=getIntent().getStringExtra("homeAdress");
           String schoolAdress=getIntent().getStringExtra("schoolAdress");
           arrayList.add(new Notification(name,birthday,schoolAdress,homeAdress));

      }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, ChildDetailsActivity.class);
                startActivity(intent);
            }
        });

        ;

      //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         // @Override
         // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //    Intent intent=new Intent(MainActivity.this, ChildDetailsActivity.class);
          //    startActivity(intent);
        //  }
      //});
    }



    @Override
    public boolean onCreateOptionsMenu (Menu m){

        getMenuInflater().inflate(R.menu.main_menu, m);
        return super.onCreateOptionsMenu(m);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
      switch(item.getItemId()) {
          case R.id.addChild:
              Intent i = new Intent(MainActivity.this, AddChildActivity.class);
              startActivity(i);
      }

        switch(item.getItemId()) {
            case R.id.emergencyCall:
                Intent intent2 = new Intent(MainActivity.this, EmergencyCallActivity.class);
                startActivity(intent2);
        }

     return super.onOptionsItemSelected(item);
    }

}
