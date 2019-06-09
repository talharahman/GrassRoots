package com.example.grassroots.fragment.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.grassroots.utils.PetitionsFeedInterface;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.recyclerview.PetitionsAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainFeed extends Fragment {

    private RecyclerView petitionRecyclerView;
    private PetitionsAdapter petitionsAdapter;
    private DatabaseReference databaseReference;
    private PetitionsFeedInterface listener;
    private List<Petition>petitionList=new ArrayList<>();
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference petitionRef=db.collection("Petitioncol");
    private PetitionViewModel petitionViewModel;

//    private DocumentReference noteRef = db.document("Petition");


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
        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);

        loadNote();

//        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot postSnapShot:dataSnapshot.getChildren()){
//                    Petition petition=postSnapShot.getValue(Petition.class);
//                    petition.setPetitiopnKey(postSnapShot.getKey());
//                    petitionList.add(petition);
//                }
//                petitionsAdapter=new PetitionsAdapter(mListener);
//                petitionsAdapter.setAdapterList(petitionList);
//                petitionRecyclerView.setAdapter(petitionsAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(requireActivity(),""+databaseError.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });

    }

    public void loadNote() {
        petitionList.clear();
        petitionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                            Petition petition=documentSnapshot.toObject(Petition.class);
                            petition.setPetitionKey(documentSnapshot.getId());
                            petitionList.add(petition);
                        }
                        petitionViewModel.setPetitionList(petitionList);
                        petitionsAdapter=new PetitionsAdapter(listener);
                        petitionsAdapter.setAdapterList(petitionList);
                        petitionRecyclerView.setAdapter(petitionsAdapter);
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