package com.example.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.grassroots.network.PetitionDB.FirebaseRepository;
import com.example.grassroots.network.PetitionDB.PetitionSignatureListener;
import com.example.grassroots.network.PetitionDB.PetitionUpdateOnCompleteListener;
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


public class DetailsPetitionFragment extends Fragment implements View.OnClickListener {

    private static final String PARAM_PETITION = "param1";

    private PetitionUpdatesAdapter petitionUpdatesAdapter;
    private Petition mParam1=new Petition();
    private PetitionsFeedInterface mListener;
    private PetitionViewModel petitionViewModel;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
  //  private DocumentReference petitionUpdateRef;
  //  private List<PetitionUpdates>petitionUpdatesList=new ArrayList<>();
    private RecyclerView petitionUpdateRecyclerView;
    private WaveLoadingView waveLoadingView;

    private List<String> signersList = new ArrayList<>();
    private List<PetitionSignatures> petitionSignaturesList = new ArrayList<>();

    private FloatingActionButton socialFab, fabTwitter, fabFacebook;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private boolean isFabOpen = false;

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
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=view.findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle(" ");

        socialFab = view.findViewById(R.id.fab_petition_social);
        fabTwitter = view.findViewById(R.id.fab_petition_twitter);
        fabFacebook = view.findViewById(R.id.fab_petition_facebook);
        socialFab.setOnClickListener(this);
        fabTwitter.setOnClickListener(this);
        fabFacebook.setOnClickListener(this);

        waveLoadingView=view.findViewById(R.id.cirele);
        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);

        TextView petitionNameTextView = view.findViewById(R.id.petition_name_text_view1);
        TextView petitionDescrptionTextView = view.findViewById(R.id.petition_description_text_view);
        TextView petitionSupporterTextView = view.findViewById(R.id.petition_supporter_text_view);
        ImageView petitionImageImageView = view.findViewById(R.id.petition_image_image_view);
        TextView petitionSignatureTextView = view.findViewById(R.id.signatures_text_view);
        petitionUpdateRecyclerView=view.findViewById(R.id.updates_recyclerView);
        Button petitionSignButton = view.findViewById(R.id.bottom_button);

        petitionUpdateRecyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL,false));

        petitionNameTextView.setText(mParam1.getmPetitionName());
        petitionSupporterTextView.setText(" Ben started this petition to "+mParam1.getmPetitionSupporter());
        petitionDescrptionTextView.setText(mParam1.getmPetitionDescription());
        petitionViewModel.setPetitionKey(mParam1.getPetitionKey());

        petitionSignatureTextView
                .setText(mParam1.getmPetitionSignature()+" have signed. "+"Let’s get to "+mParam1.getmPetitionSignatureGoal()+"!");


        Glide.with(requireContext())
                .load(mParam1.getmPetitionImageURL())
                .optionalFitCenter()
                .centerCrop()
                .into(petitionImageImageView);

        double percentage=(double) (mParam1.getmPetitionSignature()*100)/mParam1.getmPetitionSignatureGoal();
        int ger=(int)Math.round(percentage);

        waveLoadingView.setProgressValue(ger);

        loadPetitionUpdates();
        setButtons();

        petitionSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseRepository().updatePetitionSignatures("GvMnoE6YKTeouWXheeHuT1FCc5q2", mParam1, new PetitionSignatureListener() {
                    @Override
                    public void petitionSignatureSuccess(int newSignatures, List<String> newSignersList) {
                        waveLoadingView.setProgressValue(mParam1.getmPetitionSignature());

                        petitionViewModel.setSigners(signersList);

                        mListener.moveToPetitionAnim(PetitionSignAnim.newInstance(mParam1));
                    }

                    @Override
                    public void petitionSignatureFailure(Throwable t) {
                        Log.d("TAG", "OnFailure: " +  t.getMessage());
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.update) {
            mListener.moveToPetitionUpdatesFirstFragment(new PetitionUpdateFirstFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        new FirebaseRepository().getPetitionUpdates(mParam1.getPetitionKey(), new PetitionUpdateOnCompleteListener() {
            @Override
            public void onPetitionUpdatesOnSuccess(List<PetitionUpdates> petitionUpdatesList) {
                petitionViewModel.setmPetitionUpdatesList(petitionUpdatesList);
                petitionUpdatesAdapter=new PetitionUpdatesAdapter();
                petitionUpdatesAdapter.setPetitionUpdatesList(petitionUpdatesList);
                petitionUpdateRecyclerView.setAdapter(petitionUpdatesAdapter);
            }

            @Override
            public void onPetitionUpdatesOnFailure(Throwable t) {
                Log.d("TAG", "OnFailure: " + t.getMessage());
            }

        });
    }

    private void setButtons() {
        fab_open = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close);
   //     rotate_forward = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_forward);
   //     rotate_backward = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_backward);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_petition_social:
                animateFAB();
                break;
            case R.id.fab_petition_twitter:
                Intent shareIntent = requireContext().getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                if (shareIntent != null) {
                    shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, mParam1.getmPetitionImageUri());
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Please sign\n" + mParam1.getmPetitionName() + "\n'Join us in this petition at \n https://play.google.com/store/apps/details?id=com.nexon.durango.global'");
                    shareIntent.setPackage("com.twitter.android");
                    startActivity(shareIntent);
                } else {
                    shareIntent = new Intent(Intent.ACTION_VIEW);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.setData(Uri.parse("market://details?id=" + "com.twitter.android"));
                    startActivity(shareIntent);
                }
                break;
            case R.id.fab_petition_facebook:
                break;
        }
    }

    private void animateFAB() {
        if (isFabOpen) {
     //       socialFab.startAnimation(rotate_backward);

            fabTwitter.startAnimation(fab_close);
            fabFacebook.startAnimation(fab_close);

            fabTwitter.setClickable(false);
            fabFacebook.setClickable(false);

            isFabOpen = false;
        } else {
      //      socialFab.startAnimation(rotate_forward);

            fabTwitter.startAnimation(fab_open);
            fabFacebook.startAnimation(fab_open);

            fabTwitter.setClickable(true);
            fabFacebook.setClickable(true);

            isFabOpen = true;
        }
    }
}
