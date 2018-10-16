package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.AppointmentBooking_Module;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VehicleCompany extends AppCompatActivity {
    GridView gridView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_company);
        gridView =  (GridView) findViewById(R.id.gv_SelectCompany);
        gridView.setAdapter(new ImageAdapter2(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users").child("BookingDetails");


                if (position == 0) {
                    Intent intent = new Intent(VehicleCompany.this, HondaYear.class);
                    databaseReference.child("Company").setValue("HONDA");
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(VehicleCompany.this,HyundaiYear.class);
                    databaseReference.child("Company").setValue("HYUNDI");
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(VehicleCompany.this,MazdaYear.class);
                    databaseReference.child("Company").setValue("MAZDA");
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(VehicleCompany.this,SuzukiYear.class);
                    databaseReference.child("Company").setValue("SUZUKI");
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(VehicleCompany.this,ToyotaYear.class);

                    databaseReference.child("Company").setValue("TOYOTA");


                    startActivity(intent);
                }

            }
        });
    }

    public class ImageAdapter2 extends BaseAdapter {
        private Context nContext;

        public ImageAdapter2(Context context) {
            nContext = context;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(nContext);
                imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.b,
                R.drawable.f,
                R.drawable.d,
                R.drawable.c,
                R.drawable.a,



        };

    }

}
