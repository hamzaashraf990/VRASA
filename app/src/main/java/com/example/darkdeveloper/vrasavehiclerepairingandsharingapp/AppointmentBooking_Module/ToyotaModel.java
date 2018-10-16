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

public class ToyotaModel extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] list={"Axio","Camry","Corolla","Hiace","Land Cruiser","Mark X","Pro Box","Premio","Prius","Prado","Surf","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toyota_model);
        listView = (ListView) findViewById(R.id.lv_toyotamodel);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("AppointmentBooking").child("Users").child("BookingDetails");

                if (i==0){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Axio");
                    startActivity(intent);
                }
                if (i==1){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Camry");
                    startActivity(intent);
                }
                if (i==2){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Corolla");
                    startActivity(intent);
                }
                if (i==3){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Hiace");
                    startActivity(intent);
                }
                if (i==4){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Land Cruise");
                    startActivity(intent);
                }
                if (i==5){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Mark X");
                    startActivity(intent);
                }
                if (i==6){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Pro Box");
                    startActivity(intent);
                }
                if (i==7){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Premio");
                    startActivity(intent);
                }
                if (i==8){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Prius");
                    startActivity(intent);
                }
                if (i==9){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Prado");
                    startActivity(intent);
                }
                if (i==10){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Surf");
                    startActivity(intent);
                }
                if (i==11){
                    Intent intent = new Intent(ToyotaModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Others");
                    startActivity(intent);
                }
            }
        });
    }
}
