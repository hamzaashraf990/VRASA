package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserPanels extends AppCompatActivity {

    Button c_panel,m_panel;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panels);
        c_panel = (Button)findViewById(R.id.btn_customerpanel);
        m_panel = (Button)findViewById(R.id.btn_mechanicpanel);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent n=new Intent(UserPanels.this,Authantication_Login.class);
            startActivity(n);
            finish();
        }
        else {


            c_panel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preferences = getSharedPreferences("Pref", MODE_PRIVATE);
                    editor = preferences.edit();
                    editor.putString("UserType", "Customer");
                    editor.apply();
                    finish();
                    Intent intent = new Intent(UserPanels.this, Authantication_Login.class);
                    startActivity(intent);


                }
            });
            m_panel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    preferences = getSharedPreferences("Pref", MODE_PRIVATE);
                    editor = preferences.edit();
                    editor.putString("UserType", "Mechanic");
                    editor.apply();
                    finish();
                    Intent intent = new Intent(UserPanels.this, Authantication_Login.class);
                    startActivity(intent);
                }
            });
        }

    }

}
