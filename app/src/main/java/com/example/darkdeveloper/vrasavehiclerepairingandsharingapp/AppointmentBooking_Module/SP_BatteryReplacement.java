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

public class SP_BatteryReplacement extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView1;
    ArrayAdapter<String> adapter;
    String[] list={"AGS","EXIDE","OSAKA"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp__battery_replacement);
        listView1 = (ListView) findViewById(R.id.lv_battery);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                {

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");

                    if (i == 0) {
                        Intent intent = new Intent(SP_BatteryReplacement.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("AGS");
                        startActivity(intent);
                    }


                    if (i == 1) {
                        Intent intent = new Intent(SP_BatteryReplacement.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("EXIDE");
                        startActivity(intent);
                    }


                    if (i == 2) {
                        Intent intent = new Intent(SP_BatteryReplacement.this, VehicleCompany.class);
                        databaseReference.child("SparePart").setValue("OSAKA");
                        startActivity(intent);
                    }

                }
            }
        });
    }
}
