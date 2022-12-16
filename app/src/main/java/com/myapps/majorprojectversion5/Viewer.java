package com.myapps.majorprojectversion5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Viewer extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<TitleClass> list;
    AlertDialog.Builder builder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("AdsData");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(requireActivity(), list, this);
        recyclerView.setAdapter(myAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TitleClass titleClass = dataSnapshot.getValue(TitleClass.class);
                    list.add(titleClass);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void OnItemClicked(TitleClass titleClass) {
//        Toast.makeText(requireActivity(), titleClass.getTitle(), Toast.LENGTH_SHORT).show();

        builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Confirm if you want to watch the ad?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(requireActivity(), WebViewForAd.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", titleClass.getTitle());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Confirm");
        alertDialog.show();

    }
}

class TitleClass {
    String title;

    public TitleClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TitleClass(String title) {
        this.title = title;
    }
}