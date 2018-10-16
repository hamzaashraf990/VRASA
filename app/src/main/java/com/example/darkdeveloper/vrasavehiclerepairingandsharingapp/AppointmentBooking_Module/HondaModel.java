package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.AppointmentBooking_Module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HondaModel extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;

    ArrayAdapter<String> adapter;
    String[] list={"Accord","City","Civic","City Ivtec","CR-V","InSight","Life"," N One","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honda_model);
        listView = (ListView) findViewById(R.id.lv_hondamodel);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");

                if (i==0){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Accord");
                    startActivity(intent);
                }
                if (i==1){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("City");
                    startActivity(intent);
                }
                if (i==2){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Civic");
                    startActivity(intent);
                }
                if (i==3){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("City Ivtec");
                    startActivity(intent);
                }
                if (i==4){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("CR-V");
                    startActivity(intent);
                }
                if (i==5){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("InSight");
                    startActivity(intent);
                }
                if (i==6){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Life");
                    startActivity(intent);
                }
                if (i==7){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue(" N One");
                    startActivity(intent);
                }
                if (i==8){
                    Intent intent = new Intent(HondaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Others");
                    startActivity(intent);
                }

            }
        });
    }
}
