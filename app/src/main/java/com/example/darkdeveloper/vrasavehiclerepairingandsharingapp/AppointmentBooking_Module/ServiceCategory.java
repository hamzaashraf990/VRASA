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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceCategory extends AppCompatActivity {
    GridView gridView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("VRASA_Database").child("AppointmentBooking");
        databaseReference.child("ServiceCategory").setValue("OiL Change");
        gridView = (GridView) findViewById(R.id.gv_servicecategory);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(ServiceCategory.this, SP_OilChange.class);
                    databaseReference.child("ServiceCategory").setValue("OiL Change");
                    startActivity(intent);

                }
                if (position == 1) {
                    Intent intent = new Intent(ServiceCategory.this, SP_BatteryReplacement.class);
                    databaseReference.child("ServiceCategory").setValue("Battery Replacement");
                    startActivity(intent);
                }

                if (position == 2) {
                    Intent intent = new Intent(ServiceCategory.this, SP_Tunning.class);
                    databaseReference.child("ServiceCategory").setValue("Tunning");
                    startActivity(intent);
                }

                if (position == 3) {
                    Intent intent = new Intent(ServiceCategory.this, SP_EngineService.class);
                    databaseReference.child("ServiceCategory").setValue("Engine Serive");
                    startActivity(intent);
                }

                if (position == 4) {
                    Intent intent = new Intent(ServiceCategory.this,SP_BrakeService.class);

                    databaseReference.child("ServiceCategory").setValue("Break Service");
                    startActivity(intent);
                }

                if (position == 5) {
                    Intent intent = new Intent(ServiceCategory.this, SP_DetailCheckup.class);
                    databaseReference.child("ServiceCategory").setValue("Detail Check Up");
                    startActivity(intent);
                }

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
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
                R.drawable.aa, R.drawable.bb,
                R.drawable.cc, R.drawable.dd,
                R.drawable.ee, R.drawable.ff,

        };
    }




}
