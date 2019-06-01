package com.example.grassroots.fragment.user;

import android.content.Context;
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
import com.example.grassroots.utils.PetitionsFeedInterface;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.recyclerview.PetitionsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainFeed extends Fragment {

    private RecyclerView petitionRecyclerView;
    private PetitionsAdapter petitionsAdapter;
    private PetitionsFeedInterface listener;

    private List<Petition>petitionList = new ArrayList<>();

    public MainFeed () { }

    public static MainFeed newInstance() {
        return new MainFeed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        petitionRecyclerView = view.findViewById(R.id.petitions_recycler_view);
        petitionRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren()){
                    Petition petition=postSnapShot.getValue(Petition.class);
                    petitionList.add(petition);
                }
                petitionsAdapter = new PetitionsAdapter(listener);
                petitionsAdapter.setAdapterList(petitionList);
                petitionRecyclerView.setAdapter(petitionsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(requireActivity(),""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionsFeedInterface) {
            listener = (PetitionsFeedInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}