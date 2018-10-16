package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.AppointmentBooking_Module.ServiceCategory;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleRepairing_Module.CustomerMap;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleSharing_Module.Import_PhoneContacts;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleSharing_Module.VehicleSharing;
import com.google.firebase.auth.FirebaseAuth;

public class Customer_Nav_Panel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__nav__panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer__nav__panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.appointmentbooking) {
            Intent intent = new Intent(Customer_Nav_Panel.this, ServiceCategory.class);
            startActivity(intent);

        } else if (id == R.id.vehiclerepair) {
            Intent intent = new Intent(Customer_Nav_Panel.this, CustomerMap.class);
            startActivity(intent);

        } else if (id == R.id.vehiclesharing) {
            Intent intent = new Intent(Customer_Nav_Panel.this, VehicleSharing.class);
            startActivity(intent);

        } else if (id == R.id.appointmentstatus) {

            Intent intent = new Intent(Customer_Nav_Panel.this, Customer_appointment_status.class);
            startActivity(intent);

        }
        else if (id == R.id.faqs) {

            Intent intent  = new Intent(Customer_Nav_Panel.this, Customer_FAQs.class);
            startActivity(intent);


        }
        else if (id == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Customer_Nav_Panel.this,UserPanels.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("EXIT", true);
            startActivity(intent);

        }
        else if (id == R.id.contactus){
            Intent intent  = new Intent(Customer_Nav_Panel.this, Contact_Us.class);
            startActivity(intent);

        }
        else if (id == R.id.about){
            Intent intent  = new Intent(Customer_Nav_Panel.this, About_Us.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
