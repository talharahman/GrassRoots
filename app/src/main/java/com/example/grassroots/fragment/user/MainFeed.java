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

import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.PetitionFragmentsListener;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFeed extends Fragment {

    private RecyclerView petitionRecyclerView;
    private PetitionsAdapter petitionsAdapter;
    private DatabaseReference databaseReference;
    private PetitionFragmentsListener mListener;
    private List<Petition>petitionList=new ArrayList<>();
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference petitionRef=db.collection("Petitioncol");
    private PetitionViewModel petitionViewModel;

//    private DocumentReference noteRef = db.document("Petition");


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MainFeed() {
        // Required empty public constructor
    }


    public static MainFeed newInstance(String param1, String param2) {
        MainFeed fragment = new MainFeed();
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
        return inflater.inflate(R.layout.main_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        petitionRecyclerView=view.findViewById(R.id.petitions_recycler_view);
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
        petitionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                            Petition petition=documentSnapshot.toObject(Petition.class);
                            petition.setPetitiopnKey(documentSnapshot.getId());
                            petitionList.add(petition);
                        }
                        petitionViewModel.setPetitionList(petitionList);
                        petitionsAdapter=new PetitionsAdapter();
                        petitionsAdapter.setAdapterList(petitionList);
                        petitionRecyclerView.setAdapter(petitionsAdapter);
                    }
                });
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof PetitionFragmentsListener) {
//            mListener = (PetitionFragmentsListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
