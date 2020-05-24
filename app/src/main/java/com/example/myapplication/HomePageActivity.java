package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu m){

        getMenuInflater().inflate(R.menu.main_menu, m);
        return super.onCreateOptionsMenu(m);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.emergencyCall:
                Intent intent2=new Intent(HomePageActivity.this,EmergencyCallActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}
