package com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.VehicleSharing_Module;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.darkdeveloper.vrasavehiclerepairingandsharingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class Fragment_Status extends Fragment {


    EditText editText;
    Button button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView lv_UI;
    private static UseerInformation.UserInformation_Adapter userInformation_adapter;
    ArrayList<UseerInformation.UserInformation_DataModel> userInformation_dataModel;
    View view;

    public Fragment_Status() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__status, container, false);
        editText = (EditText) view.findViewById(R.id.ed_status);
        button = (Button)view.findViewById(R.id.btn_status);
        FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("VRASA_Database").child("Users");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Status").setValue(editText.getText().toString());
                Toast.makeText(getContext(),"Status is Uploaded",Toast.LENGTH_LONG).show();

            }
        });


        lv_UI = (ListView) view.findViewById(R.id.lv_userinformation);
        lv_UI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UseerInformation.UserInformation_DataModel dataModel = userInformation_dataModel.get(position);

            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VRASA_Database").child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInformation_dataModel = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UseerInformation.UserInformation_DataModel dataModel = new UseerInformation.UserInformation_DataModel();
                    dataModel.setUserId(dataSnapshot1.getKey());
                    dataModel.setU_name((String) dataSnapshot1.child("Name").getValue());
                    dataModel.setU_phone((String) dataSnapshot1.child("Phone").getValue());
                    dataModel.setU_Status((String)dataSnapshot1.child("Status").getValue());
                    dataModel.setU_profileImage((String) dataSnapshot1.child("ProfileImage").getValue());


                    userInformation_dataModel.add(dataModel);
                }


                userInformation_adapter = new UseerInformation.UserInformation_Adapter(userInformation_dataModel,getContext());
                lv_UI.setAdapter(userInformation_adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view;
    }

}
