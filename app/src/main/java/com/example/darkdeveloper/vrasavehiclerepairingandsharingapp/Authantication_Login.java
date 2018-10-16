package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.AppointmentBooking_Module.ServiceCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Authantication_Login extends AppCompatActivity {
    EditText edit_name,edit_phone,edit_code;
    Button get_code,put_code;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String codeSent;
    DatabaseReference vr_databaseReference;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authantication__login);
        //if user is alreaday login he move to the main activity
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (getUserType().equals("Customer")) {
                startActivity(new Intent(this, Customer_Nav_Panel.class));

                finish();
            } else {
                startActivity(new Intent(this, Mechanic_Nav_Panel.class));
                finish();
            }

        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users");
        firebaseAuth = FirebaseAuth.getInstance();
        vr_databaseReference=firebaseDatabase.getReference("VRASA_Database").child("CustomerRequests");

        edit_name = (EditText) findViewById(R.id.name_text);
        edit_phone = (EditText) findViewById(R.id.phone_text);
        edit_phone.setText("+92");
        edit_code = (EditText) findViewById(R.id.code_text);

        get_code = (Button)findViewById(R.id.get_code);
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
                Toast.makeText(Authantication_Login.this,"Code Sent",Toast.LENGTH_LONG).show();
            }
        });

        put_code = (Button)findViewById(R.id.login_btn);
        put_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });

    }

    public String getUserType() {
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        return preferences.getString("UserType", "");
    }

    private void verifySignInCode(){
        String code_phone = edit_code.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code_phone);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {



            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    final String name = edit_name.getText().toString().trim();
                    final String phone = edit_phone.getText().toString().trim();

                    String userID = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference databaseReference1 = databaseReference.child(userID);
                    databaseReference1.child("Name").setValue(name);
                    databaseReference1.child("Phone").setValue(phone);

                    DatabaseReference vrDatabaseReference = vr_databaseReference.child(userID);
                    vrDatabaseReference.child("UserData").child("Name").setValue(name);
                    vrDatabaseReference.child("UserData").child("Phone").setValue(phone);


                    if (getUserType().equals("Customer")){
                        intent = new Intent(Authantication_Login.this, Customer_Nav_Panel.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    }
                    else
                        intent = new Intent(Authantication_Login.this, Mechanic_Nav_Panel.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                    } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(),
                                "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void sendVerificationCode(){

        String number = edit_phone.getText().toString();
        if(number.isEmpty()){
            edit_phone.setError("Phone number is required");
            edit_phone.requestFocus();
            return;
        }
        if(number.length() < 10 ){
            edit_phone.setError("Please enter a valid phone");
            edit_phone.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeoutn
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };
}
