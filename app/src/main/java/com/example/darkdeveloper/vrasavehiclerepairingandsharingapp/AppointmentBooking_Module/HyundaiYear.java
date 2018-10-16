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

public class HyundaiYear extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] list={"2010","2011","2012","2013","2014","2015"," 2016","2017","2018"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyundai_year);
        listView = (ListView) findViewById(R.id.lv_hyundaiyear);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");


                if (i == 0) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2010");
                    startActivity(intent);
                }

                if (i == 1) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2011");
                    startActivity(intent);
                }

                if (i == 2) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2012");
                    startActivity(intent);
                }

                if (i == 3) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2013");
                    startActivity(intent);
                }

                if (i == 4) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2014");
                    startActivity(intent);
                }

                if (i == 5) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2015");
                    startActivity(intent);
                }

                if (i == 6) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2016");
                    startActivity(intent);
                }

                if (i == 7) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2017");
                    startActivity(intent);
                }

                if (i == 8) {
                    Intent intent = new Intent(HyundaiYear.this, HyundaiModel.class);
                    databaseReference.child("Year").setValue("2018");
                    startActivity(intent);
                }
            }
        });
    }
}
