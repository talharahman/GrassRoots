package com.example.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionUpdates;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.recyclerview.PetitionUpdatesAdapter;
import com.example.grassroots.recyclerview.PetitionViewHolder;
import com.example.grassroots.recyclerview.PetitionsAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DetailsPetitonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PARAM_PETITION = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private TextView petitionNameTextView;
    private TextView petitionDescrptionTextView;
    private TextView petitionSupporterTextView;
    private TextView petitionSignatureTextView;
    private ImageView petitionImageImageView;
    private ProgressBar petitionProgressBar;
    private Button petitionUpdatesButton;
    private Button petitionSignButton;
    private PetitionUpdatesAdapter petitionUpdatesAdapter;
    private Petition mParam1=new Petition();
    private PetitionFragmentsListener mListener;
    private PetitionViewModel petitionViewModel;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference petitionUpdateRef;
    private List<PetitionUpdates>petitionUpdatesList=new ArrayList<>();
    private RecyclerView petitionUpdateRecyclerView;




    public DetailsPetitonFragment() {
        // Required empty public constructor
    }


    public static DetailsPetitonFragment newInstance(Petition petition) {
        DetailsPetitonFragment fragment = new DetailsPetitonFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_PETITION,petition);
        fragment.setArguments(args);
        Log.d("ben", "onClick:  "+petition.getmPetitionName());

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Petition) getArguments().getSerializable(PARAM_PETITION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_petiton, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);


        petitionNameTextView=view.findViewById(R.id.petition_name_text_view1);
        petitionDescrptionTextView=view.findViewById(R.id.petition_description_text_view);
        petitionSupporterTextView=view.findViewById(R.id.petition_supporter_text_view);
        petitionSignatureTextView=view.findViewById(R.id.petition_Signatures_text_view);
        petitionImageImageView=view.findViewById(R.id.image_petition_image);
        petitionProgressBar=view.findViewById(R.id.progress_bar_signatures);
        petitionUpdatesButton=view.findViewById(R.id.petition_update_button);
        petitionUpdateRecyclerView=view.findViewById(R.id.updates_recyclerView);
        petitionSignButton=view.findViewById(R.id.sing_petition);

        petitionUpdateRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext()));


        petitionNameTextView.setText(mParam1.getmPetitionName());
        petitionSupporterTextView.setText(mParam1.getmPetitionSupporter());
        petitionDescrptionTextView.setText(mParam1.getmPetitionDescription());
        petitionViewModel.setPetitionKey(mParam1.getPetitiopnKey());


        Glide.with(requireContext()).load(mParam1.getmPetitionImageURL()).fitCenter().centerCrop().into(petitionImageImageView);
        petitionProgressBar.setMax(mParam1.getmPetitionSignatureGoal());
        petitionProgressBar.setProgress(mParam1.getmPetitionSignature());
        petitionSignatureTextView.setText(mParam1.getmPetitionSignature()+" have signed. Let’s get to "+mParam1.getmPetitionSignatureGoal()+"!");

        loadPetitionUpdates();

        petitionUpdatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updatesMood=true;
                mListener.moveToPetitionUpdatesFirstFrgament(new PetitionUpdateFirstFragment());
            }
        });

        petitionSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petitionUpdateRef =db.collection("Petitioncol").document(mParam1.getPetitionKey());


            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionFragmentsListener) {
            mListener = (PetitionFragmentsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void loadPetitionUpdates() {
        petitionUpdateRef =db.collection("Petitioncol").document(mParam1.getPetitionKey());

        petitionUpdateRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Petition petition=documentSnapshot.toObject(Petition.class);
                petition.setPetitiopnKey(documentSnapshot.getId());
                for(PetitionUpdates p: petition.getmPetitionUpdatesList()) {
                    petitionUpdatesList.add(p);
                }

                petitionViewModel.setmPetitionUpdatesList(petitionUpdatesList);
                petitionUpdatesAdapter=new PetitionUpdatesAdapter();
                petitionUpdatesAdapter.setPetitionUpdatesList(petitionUpdatesList);
                petitionUpdateRecyclerView.setAdapter(petitionUpdatesAdapter);

            }
        });

    }



}
