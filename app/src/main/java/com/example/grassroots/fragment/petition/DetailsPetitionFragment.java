package com.example.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionSignatures;
import com.example.grassroots.model.petition.PetitionUpdates;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.recyclerview.PetitionUpdatesAdapter;
import com.example.grassroots.utils.PetitionsFeedInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;


public class DetailsPetitionFragment extends Fragment {

    private static final String PARAM_PETITION = "param1";

    private TextView petitionNameTextView;
    private TextView petitionDescrptionTextView;
    private TextView petitionSupporterTextView;
    private TextView petitionSignatureTextView;
    private ImageView petitionImageImageView;
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

    private List<String> signersList = new ArrayList<>();
    private List<PetitionSignatures> petitionSignaturesList = new ArrayList<>();

    public DetailsPetitionFragment() { }


    public static DetailsPetitionFragment newInstance(Petition petition) {
        DetailsPetitionFragment fragment = new DetailsPetitionFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_PETITION,petition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.opetion_petiton_menu,menu );
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_details_petiton, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConstraintLayout constraintLayout;
        BottomSheetBehavior bottomSheetBehavior;
        constraintLayout=view.findViewById(R.id.bottom_button_layout);
        bottomSheetBehavior=BottomSheetBehavior.from(constraintLayout);

        Toolbar toolbar = view.findViewById(R.id.app_bar);
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=view.findViewById(R.id.collapsing);

      // collapsingToolbarLayout.setTitle(mParam1.getmPetitionName());

      collapsingToolbarLayout.setTitle(" ");

        waveLoadingView=view.findViewById(R.id.cirele);
        double percentage=(double) (mParam1.getmPetitionSignature()*100)/mParam1.getmPetitionSignatureGoal();

        int ger=(int)Math.round(percentage);

        waveLoadingView.setProgressValue(ger);

        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);

        petitionNameTextView=view.findViewById(R.id.petition_name_text_view1);
        petitionDescrptionTextView=view.findViewById(R.id.petition_description_text_view);
        petitionSupporterTextView=view.findViewById(R.id.petition_supporter_text_view);
        petitionImageImageView=view.findViewById(R.id.petition_image_image_view);
        petitionSignatureTextView=view.findViewById(R.id.signatures_text_view);
        petitionUpdateRecyclerView=view.findViewById(R.id.updates_recyclerView);
        petitionSignButton=view.findViewById(R.id.bottom_button);

        petitionUpdateRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL,false));

        petitionNameTextView.setText(mParam1.getmPetitionName());
        petitionSupporterTextView.setText(" Ben started this petition to "+mParam1.getmPetitionSupporter());
        petitionDescrptionTextView.setText(mParam1.getmPetitionDescription());
        petitionViewModel.setPetitionKey(mParam1.getPetitionKey());

        petitionSignatureTextView.setText(mParam1.getmPetitionSignature()+" have signed. "+"Let’s get to "+mParam1.getmPetitionSignatureGoal()+"!");


        Glide.with(requireContext()).load(mParam1.getmPetitionImageURL()).optionalFitCenter().centerCrop().into(petitionImageImageView);

       loadPetitionUpdates();



        petitionSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = db.collection("Petitioncol").document(petitionViewModel.getPetitionKey());
                signersList.add("GvMnoE6YKTeouWXheeHuT1FCc5q2");
                mParam1.setmPetitionSignature(mParam1.getmPetitionSignature()+1);

                documentReference.update("mPetitionSignature", mParam1.getmPetitionSignature(),
                        "signers", signersList)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                waveLoadingView.setProgressValue(mParam1.getmPetitionSignature());

                                // add a signature to the petition
                                signersList.add("GvMnoE6YKTeouWXheeHuT1FCc5q2");
                                petitionViewModel.setSigners(signersList);
                                mListener.moveToPetitionAnim(new PetitionSignAnim());
                                mListener.moveToDetailsPetition(DetailsPetitionFragment.newInstance(mParam1));
                            }
                        });

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
               mListener.moveToPetitionUpdatesFirstFragment(new PetitionUpdateFirstFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
