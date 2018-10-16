package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.AppointmentBooking_Module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.Customer_Nav_Panel;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitAppointment extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText date,time,address;
    Button cancel,btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_appointment);


        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VRASA_Database").child("CustomerRequests").child(userID).child("AppointmentBooking");
        AppointmentCancelled();
        AppointmentSumbitted();
    }



    public  void AppointmentCancelled() {
        cancel = (Button) findViewById(R.id.cancelappointment);


        cancel.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                databaseReference.child("BookingStatus").setValue("Cancelled Appointment");
                Intent intent = new Intent(SubmitAppointment.this, Customer_Nav_Panel.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

//        btn_main =(Button)findViewById(R.id.btn_home);
//        btn_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Submit_Appointment.this,Modules.class);
//                startActivity(intent);
//            }
//        });
    }


    public void AppointmentSumbitted() {

        date = (EditText) findViewById(R.id.appointmentdate);
        time = (EditText) findViewById(R.id.appointmenttime);
        address = (EditText) findViewById(R.id.appointmentaddress);
        btn_submit = (Button)findViewById(R.id.submit);
        databaseReference.child("BookingStatus").setValue("AppointmentSubmitted");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validationDetails()) {

                    databaseReference.child("AppointmentDate").setValue(date.getText().toString());
                    databaseReference.child("AppointmentTime").setValue(time.getText().toString());
                    databaseReference.child("AppointmentAddress").setValue(address.getText().toString());

                    Intent intent = new Intent(SubmitAppointment.this, Customer_Nav_Panel.class);
                    startActivity(intent);


                }
            }
        });

        //Log.i("TAG", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    private boolean validationDetails() {

        if (date.getText().toString().isEmpty()) {
            date.setError("Enter Booking Date First ");
            date.requestFocus();
            Toast.makeText(getApplicationContext(), "Please Enter Date for Booking", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (time.getText().toString().isEmpty()) {
            time.setError("Enter Booking Time First ");
            return false;
        }
        if (address.getText().toString().isEmpty()) {
            address.setError("Enter your Home Address");
            return false;
        }
        return true;
    }
}


