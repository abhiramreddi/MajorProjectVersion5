package com.myapps.majorprojectversion5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView = view.findViewById(R.id.tvWallet);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPred", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Wallet");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Long str = snapshot.child("coins").getValue(Long.class);
                    textView.setText(String.valueOf(str));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}