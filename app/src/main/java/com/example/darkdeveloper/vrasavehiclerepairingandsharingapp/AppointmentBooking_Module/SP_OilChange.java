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

public class SP_OilChange extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] list={"Total","Zic","Caltex","Shell","Guard","Kixx"," Honda","Toyta",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp__oil_change);
        listView = (ListView) findViewById(R.id.lv_oilchange);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                {

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");

                    if (i == 0) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Total");
                        startActivity(intent);
                    }

                    if (i == 1) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparPart").setValue("Zic");
                        startActivity(intent);
                    }
                    if (i == 2) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Caltex");
                        startActivity(intent);
                    }
                    if (i == 3) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Shell");
                        startActivity(intent);
                    }
                    if (i == 4) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Guard");
                        startActivity(intent);
                    }
                    if (i == 5) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Kixx");
                        startActivity(intent);
                    }
                    if (i == 6) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Honda");
                        startActivity(intent);
                    }
                    if (i == 7) {
                        Intent intent = new Intent(SP_OilChange.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("Toyota");
                        startActivity(intent);
                    }
                }
            }
        });
    }}