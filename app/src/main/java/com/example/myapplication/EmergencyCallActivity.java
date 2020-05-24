package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapter3 adapter;
    ArrayList<Emergancy> emergancyArrayList;
    TextView name;


Emergancy e1=new Emergancy("police");
Emergancy e2=new Emergancy("ambulance");
Emergancy e3=new Emergancy("firePlace");
Emergancy e4=new Emergancy("electricCompany");
Emergancy e5=new Emergancy("24 hours crisis line");

Emergancy[]emergancies=new Emergancy[]{e1,e2,e3,e4,e5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        listView=findViewById(R.id.listViewCalls);
        emergancyArrayList=new ArrayList<Emergancy>();
        emergancyArrayList.add(new Emergancy("police"));
        emergancyArrayList.add(new Emergancy("ambulance"));
        emergancyArrayList.add(new Emergancy("fireplace"));
        emergancyArrayList.add(new Emergancy("Electric Company"));
        emergancyArrayList.add(new Emergancy("24 Hour Crisis Line"));

        adapter = new CustomAdapter3 (this,R.layout.emergancy_item,emergancyArrayList);
        listView.setAdapter(adapter);
        name=findViewById(R.id.textViewEmergancyName);

       // ArrayAdapter<Emergancy>adapter=new ArrayAdapter<Emergancy>(this,android.R.layout.simple_list_item_1,emergancies);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder a_Builder=new AlertDialog.Builder(EmergencyCallActivity.this);
                a_Builder.setMessage("are you sure you want to call the "+emergancies[position]+"?")
                        .setCancelable(false)
                        .setPositiveButton("call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (emergancies[position].equals("police")) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:100"));
                                    startActivity(intent);

                                }
                                if (emergancies[position].equals("ambulance")) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:101"));
                                    startActivity(intent);

                                }
                                if (emergancies[position].equals("firePlace")) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:102"));
                                    startActivity(intent);

                                }
                                if (emergancies[position].equals("electricCompany")) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:103"));
                                    startActivity(intent);

                                }
                                if (emergancies[position].equals("24 hours crisis line")) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:118"));
                                    startActivity(intent);

                                }

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
                alert.setTitle(emergancies[position].getEmergancyName());
                alert.show();

            }
        }
        );



    }

}





