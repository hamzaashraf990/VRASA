package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleRepairing_Module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VehicleRepair_UserRequests extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    private static UserRequests_Adapter UR_Adapter;
    ArrayList<UserRequests_DataModel> UR_DataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_repair__user_requests);
        listView = (ListView) findViewById(R.id.lv_userrequests);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserRequests_DataModel dataModel = UR_DataModel.get(position);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VRASA_Database").child("CustomerRequests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                UR_DataModel = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserRequests_DataModel dataModel = new UserRequests_DataModel();
                    dataModel.setUserId(dataSnapshot1.getKey());
                    dataModel.setName((String) dataSnapshot1.child("UserData").child("Name").getValue());
                    dataModel.setPhone_number((String) dataSnapshot1.child("UserData").child("Phone").getValue());
                    dataModel.setStatus((String) dataSnapshot1.child("VehicleRepair").child("Status").getValue());
                    dataModel.setLocation(((Double) dataSnapshot1.child("VehicleRepair").child("Location").child("latitude").getValue())
                            + "," + (Double) dataSnapshot1.child("VehicleRepair").child("Location").child("longitude").getValue());







                    UR_DataModel.add(dataModel);
                }


                UR_Adapter = new UserRequests_Adapter(UR_DataModel, getApplicationContext());
                listView.setAdapter(UR_Adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public static class UserRequests_Adapter extends ArrayAdapter<UserRequests_DataModel> implements View.OnClickListener{

        private ArrayList<UserRequests_DataModel> dataSet;
        Context mContext;

        // View lookup cache
        private static  class ViewHolder {
            TextView location_customer,phone_customer,status_customer,name_customer;
            Button btnrequest;
        }

        public UserRequests_Adapter(ArrayList<UserRequests_DataModel> data, Context context) {
            super(context, R.layout.userrequests_items, data);
            this.dataSet = data;
            this.mContext=context;

        }
        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            UserRequests_DataModel dataModel=(UserRequests_DataModel)object;
            }

        private int lastPosition = -1;


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final UserRequests_DataModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.userrequests_items, parent, false);
                viewHolder.location_customer = (TextView) convertView.findViewById(R.id.location_customer);
                viewHolder.phone_customer = (TextView) convertView.findViewById(R.id.phone_customer);
                viewHolder.status_customer = (TextView) convertView.findViewById(R.id.status_customer);
                viewHolder.name_customer = (TextView) convertView.findViewById(R.id.name_customer);
                viewHolder.btnrequest = (Button)convertView.findViewById(R.id.accept_customerReq);



                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.ur_up_from_bottom
                    : R.anim.ur_down_from_top);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.location_customer.setText(dataModel.getLocation());
            viewHolder.phone_customer.setText(dataModel.getPhone_number());
            viewHolder.status_customer.setText(dataModel.getStatus());
            viewHolder.name_customer.setText(dataModel.getName());

            viewHolder.btnrequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference;
                    String userID = dataSet.get(position).getUserId();
                    databaseReference = firebaseDatabase.getReference("VRASA_Database").child("CustomerRequests").child(userID);
                    databaseReference.child("VehicleRepair").child("Status").setValue("In Progress").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(mContext,MechanicMap.class);
                                intent.putExtra("location",dataModel.getLocation());
                                intent.putExtra("customer_id",dataModel.getUserId());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent);

                            }
                        }
                    });
                }
            });


            // viewHolder.info.setOnClickListener(this);
            // viewHolder.info.setTag(position);
            // Return the completed view to render on screen
            return convertView;
        }



    }

    class UserRequests_DataModel {
        String status;
        String location;
        String name;
        String phone_number;
        String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public UserRequests_DataModel(){

        }
        public UserRequests_DataModel(String Request_status, String User_Location, String phone_customer,String name_customer) {
            this.name =name_customer;
            this.phone_number=phone_customer;
            this.status=Request_status;
            this.location=User_Location;
            }


        public String getName() {
            return name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getLocation() {

            return location;
        }
    }

}
