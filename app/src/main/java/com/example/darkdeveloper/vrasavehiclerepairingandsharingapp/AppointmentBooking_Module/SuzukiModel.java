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

public class SuzukiModel extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] list={"Alto","Baleno","Cultus","Every","Jimny","Liana","Mehran","WagnoR","Potohar","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suzuki_model);
        listView = (ListView) findViewById(R.id.lv_suzukimodel);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");

                if (i==0){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Alto");
                    startActivity(intent);
                }
                if (i==1){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Baleno");
                    startActivity(intent);
                }
                if (i==2){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Cultus");
                    startActivity(intent);
                }
                if (i==3){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Every");
                    startActivity(intent);
                }
                if (i==4){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Jimny");
                    startActivity(intent);
                }
                if (i==5){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Liana");
                    startActivity(intent);
                }
                if (i==6){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Mehran");
                    startActivity(intent);
                }
                if (i==7){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("WagnoR");
                    startActivity(intent);
                }
                if (i==8){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Potohar");
                    startActivity(intent);
                }
                if (i==9){
                    Intent intent = new Intent(SuzukiModel.this, SubmitAppointment.class);
                    databaseReference.child("Model").setValue("Others");
                    startActivity(intent);
                }
            }
        });
    }
}
