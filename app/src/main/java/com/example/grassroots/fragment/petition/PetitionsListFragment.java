package com.example.grassroots.fragment.petition;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.recyclerview.PetitionsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetitionsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetitionsListFragment extends Fragment {

    private RecyclerView petitionRecyclerView;
    private PetitionsAdapter petitionsAdapter=new PetitionsAdapter();
    private DatabaseReference databaseReference;
    private List<Petition>petitionList=new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PetitionsListFragment() {
        // Required empty public constructor
    }


    public static PetitionsListFragment newInstance(String param1, String param2) {
        PetitionsListFragment fragment = new PetitionsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petitions_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        petitionRecyclerView=view.findViewById(R.id.petitions_recycler_view);
        petitionRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        try {
            File localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();


        }

        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren()){
                    Petition petition=postSnapShot.getValue(Petition.class);
                    petitionList.add(petition);
                }
                petitionsAdapter.setAdapterList(petitionList);
                petitionRecyclerView.setAdapter(petitionsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(requireActivity(),""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
