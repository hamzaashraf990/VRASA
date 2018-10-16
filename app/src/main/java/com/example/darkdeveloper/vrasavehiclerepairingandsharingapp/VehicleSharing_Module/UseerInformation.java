package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleSharing_Module;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleRepairing_Module.MechanicMap;
import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleRepairing_Module.VehicleRepair_UserRequests;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UseerInformation extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView lv_UI;
    private static UserInformation_Adapter userInformation_adapter;
    ArrayList<UserInformation_DataModel> userInformation_dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useer_information);
        lv_UI = (ListView) findViewById(R.id.lv_userrequests);
        lv_UI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserInformation_DataModel dataModel = userInformation_dataModel.get(position);

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInformation_dataModel = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserInformation_DataModel dataModel = new UserInformation_DataModel();
                    dataModel.setUserId(dataSnapshot1.getKey());
                    dataModel.setU_name((String) dataSnapshot1.child("Name").getValue());
                    dataModel.setU_phone((String) dataSnapshot1.child("Phone").getValue());
                    dataModel.setU_Status((String)dataSnapshot1.child("Status").getValue());

                    userInformation_dataModel.add(dataModel);
                }


                userInformation_adapter = new UserInformation_Adapter(userInformation_dataModel, getApplicationContext());
                lv_UI.setAdapter(userInformation_adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static class UserInformation_Adapter extends ArrayAdapter<UserInformation_DataModel>implements View.OnClickListener{

        private  ArrayList<UserInformation_DataModel> userdataset;
        Context ncontext;

        private static class ViewHolder{
            TextView name,phone,status;
            ImageView profileimage;
        }
        public UserInformation_Adapter(ArrayList<UserInformation_DataModel> data ,Context context){
            super(context, R.layout.userinformation_items, data);
            this.userdataset = data;
            this.ncontext=context;
        }
        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            UserInformation_DataModel dataModel = (UserInformation_DataModel)object;
        }

        private int lastPosition = -1;


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final UserInformation_DataModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.userinformation_items, parent, false);
                viewHolder.name = (TextView) convertView.findViewById(R.id.user_name);
                viewHolder.phone = (TextView) convertView.findViewById(R.id.user_phone);
                viewHolder.status = (TextView) convertView.findViewById(R.id.user_status);
                viewHolder.profileimage = (ImageView) convertView.findViewById(R.id.user_image);



                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(ncontext, (position > lastPosition) ? R.anim.ur_up_from_bottom
                    : R.anim.ur_down_from_top);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.name.setText(dataModel.getU_name());
            viewHolder.phone.setText(dataModel.getU_phone());
            viewHolder.status.setText(dataModel.getU_Status());
            viewHolder.profileimage.setTag(dataModel.U_profileImage);


            return convertView;
        }

    }

    static class UserInformation_DataModel {
        String U_name;
        String U_phone;
        String U_Status;
        String U_profileImage;
        String userid;

        public String getUserid() {
            return userid;
        }
        public void setUserId(String userId) {
            this.userid = userId;
        }

        public UserInformation_DataModel(){
            }
        public UserInformation_DataModel(String UI_name, String UI_phone, String UI_status,String UI_location) {
            this.U_name =UI_name;
            this.U_phone=UI_phone;
            this.U_Status=UI_status;
            this.U_profileImage=UI_location;
        }

        public void setU_name(String u_name) {
            U_name = u_name;
        }

        public void setU_phone(String u_phone) {
            U_phone = u_phone;
        }

        public void setU_Status(String u_Status) {
            U_Status = u_Status;
        }

        public void setU_profileImage(String u_profileImage) {
            U_profileImage = u_profileImage;
        }

        public String getU_name() {
            return U_name;
        }

        public String getU_phone() {
            return U_phone;
        }

        public String getU_Status() {
            return U_Status;
        }

        public String getU_profileImage() {
            return U_profileImage;
        }
    }
}
