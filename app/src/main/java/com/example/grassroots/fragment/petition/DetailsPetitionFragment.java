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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionUpdates;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.recyclerview.PetitionUpdatesAdapter;
import com.example.grassroots.utils.PetitionsFeedInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;


public class DetailsPetitionFragment extends Fragment {
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
    private PetitionsFeedInterface mListener;
    private PetitionViewModel petitionViewModel;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private DocumentReference petitionUpdateRef;
    private List<PetitionUpdates>petitionUpdatesList=new ArrayList<>();
    private RecyclerView petitionUpdateRecyclerView;
    private WaveLoadingView waveLoadingView;
    private SeekBar seekBar;



    public DetailsPetitionFragment() {
        // Required empty public constructor
    }


    public static DetailsPetitionFragment newInstance(Petition petition) {
        DetailsPetitionFragment fragment = new DetailsPetitionFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_PETITION,petition);
        fragment.setArguments(args);
        Log.d("ben", "onClick:  "+petition.getmPetitionName());

        return fragment;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        MenuInflater menuInflater =requireActivity().getMenuInflater();
//        menuInflater.inflate(R.menu.detail_petition_opetion_menu,menu );
//    }

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

        waveLoadingView=view.findViewById(R.id.cirele);
        waveLoadingView.setProgressValue(mParam1.getmPetitionSignature());

        waveLoadingView.setBottomTitle(" Let’s get to "+mParam1.getmPetitionSignatureGoal()+"!");
        waveLoadingView.setCenterTitle("have signed. ");
        waveLoadingView.setTopTitle(String.valueOf(mParam1.getmPetitionSignature()));




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
        petitionViewModel.setPetitionKey(mParam1.getPetitionKey());


        Glide.with(requireContext()).load(mParam1.getmPetitionImageURL()).fitCenter().centerCrop().into(petitionImageImageView);
        petitionProgressBar.setMax(mParam1.getmPetitionSignatureGoal());
        petitionProgressBar.setProgress(mParam1.getmPetitionSignature());
        petitionSignatureTextView.setText(mParam1.getmPetitionSignature()+" have signed. Let’s get to "+mParam1.getmPetitionSignatureGoal()+"!");

        loadPetitionUpdates();

        petitionUpdatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToPetitionUpdatesFirstFragament(new PetitionUpdateFirstFragment());
            }
        });

        petitionSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = db.collection("Petitioncol").document(petitionViewModel.getPetitionKey());

                documentReference.update("mPetitionSignature", mParam1.getmPetitionSignature()+1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(requireContext(), "hi is done", Toast.LENGTH_LONG).show();
                                petitionProgressBar.setProgress(mParam1.getmPetitionSignature());
                                waveLoadingView.setProgressValue(mParam1.getmPetitionSignature());


                            }
                        });



            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionsFeedInterface) {
            mListener = (PetitionsFeedInterface) context;
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
                petition.setPetitionKey(documentSnapshot.getId());
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
