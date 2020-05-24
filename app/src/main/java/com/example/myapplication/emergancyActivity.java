package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emergancyActivity extends AppCompatActivity {
    private  Button police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergancy);
        onButtonClickListener();
    }
    public void onButtonClickListener() {
        police=(Button)findViewById(R.id.buttonPoliceCall);
        police.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_Builder=new AlertDialog.Builder(emergancyActivity.this);
                        a_Builder.setMessage("are you sure you want to call the police?")
                                .setCancelable(false)
                                .setPositiveButton("call", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert=a_Builder.create();
                        alert.setTitle("police");
                        alert.show();

                    }
                }
        );

    }
}



