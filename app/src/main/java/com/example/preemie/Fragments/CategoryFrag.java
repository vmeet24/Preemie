package com.example.preemie.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.preemie.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFrag extends Fragment {
     ImageView generalReasoning , newFacts , healthCare,nutrients,diseases,eqiupments;
    //List<String> imageUrls = new ArrayList<>();
    // RecyclerView categoryRecyclerView;
   FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public CategoryFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    void callFragment(String data,Fragment fragment , FragmentTransaction fragmentTransaction)
    {
        Bundle args = new Bundle();
        args.putString("uploadCat", data);//
        fragmentTransaction.add(R.id.frmLyt, fragment);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.frmLyt, fragment).commit();
        fragmentTransaction.addToBackStack(null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generalReasoning = view.findViewById(R.id.generalReasoning);
        newFacts = view.findViewById(R.id.newFacts);
        healthCare = view.findViewById(R.id.healthCare);
        nutrients = view.findViewById(R.id.nutrients);
        diseases = view.findViewById(R.id.diseases);
        eqiupments = view.findViewById(R.id.equipments);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Posts");


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final homeFrag fragment = new homeFrag();
        newFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("newFacts",fragment,fragmentTransaction);
            }
        });
        generalReasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("generalReasoning",fragment,fragmentTransaction);
            }
        });
        healthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("healthCare",fragment,fragmentTransaction);
            }
        });
        nutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("nutrients",fragment,fragmentTransaction);
            }
        });
        diseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("diseases",fragment,fragmentTransaction);
            }
        });
        eqiupments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("equipments",fragment,fragmentTransaction);
            }
        });
    }
}