package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.activity.CongressActivity;
import com.example.grassroots.activity.LocalRepsActivity;
import com.example.grassroots.model.CivicInfo.CivicInfoModel;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.user.UserActionViewModel;
import com.example.grassroots.network.CivicInfo.CivicInfoPresenter;
import com.example.grassroots.network.PetitionDB.FirebaseRepository;
import com.example.grassroots.network.PetitionDB.MyPetitionHistoryInterface;
import com.example.grassroots.utils.LocalRepsUIListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class OverviewFragmentv2 extends Fragment {

    private View rootView;
    private TabContactListener tabContactListener;
    private List<Petition> myPetitionsHistory = new ArrayList<>();
    private UserActionViewModel userActionViewModel;
    public static final String ADDRESS = "https://www.fec.gov/data/candidate/";

    public OverviewFragmentv2() {
    }

    public static OverviewFragmentv2 newInstance() {
        return new OverviewFragmentv2();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TabContactListener) {
            tabContactListener = (TabContactListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement TabContactListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_overview_design_v3, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        makeDatabaseCall();
    }

    private void makeDatabaseCall() {
        new FirebaseRepository().getAllPetitions(myPetitions -> {
            myPetitionsHistory = myPetitions;
            userActionViewModel.setPetitions(myPetitionsHistory);
        });
    }

    private void initialize() {
        CongressOverviewVM congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        userActionViewModel = ViewModelProviders
                .of((FragmentActivity) requireContext())
                .get(UserActionViewModel.class);

        ImageView img_profile_rep = rootView.findViewById(R.id.img_profile_rep);
        TextView txt_title_name = rootView.findViewById(R.id.txt_title_name);
        TextView txt_party_state = rootView.findViewById(R.id.txt_party_state);
        TextView txt_fec_id = rootView.findViewById(R.id.txt_fec_id_number);
        TextView txt_year = rootView.findViewById(R.id.txt_year);
        ImageView send = rootView.findViewById(R.id.plane);

        send.setOnClickListener(v -> {
            AlertDialog.Builder petitions = new AlertDialog.Builder(rootView.getContext());
            petitions.setIcon(R.drawable.send);
            petitions.setTitle("Choose a Petition to send");

            String[] myPetitionNames = {
                    myPetitionsHistory.get(myPetitionsHistory.size() -1).getmPetitionName(),
                    myPetitionsHistory.get(myPetitionsHistory.size() -2).getmPetitionName(),
                    myPetitionsHistory.get(0).getmPetitionName(),
                    myPetitionsHistory.get(1).getmPetitionName()};


            int checkedItem = 0;
            petitions.setSingleChoiceItems(myPetitionNames, checkedItem, (dialog, which) -> { });
            petitions.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(rootView.getContext(), "Your Petition has been sent!", Toast.LENGTH_SHORT).show();
                }
            });
            petitions.setNegativeButton("Cancel", null);

            AlertDialog dialog = petitions.create();
            dialog.show();
        });

        txt_title_name.setText(
                String.format("%s %s %s", congressOverviewVM.getCongressMember().getShort_title(), congressOverviewVM.getCongressMember().getFirst_name(), congressOverviewVM.getCongressMember().getLast_name()));

        txt_party_state.setText(
                String.format("%s, %s", congressOverviewVM.getCongressMember().getParty(), congressOverviewVM.getCongressMember().getState()));

        String fecID = congressOverviewVM.getCongressMember().getFec_candidate_id();
        txt_fec_id.setText(fecID);
        txt_fec_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ADDRESS + fecID + "/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        txt_year.setText(congressOverviewVM.getCongressMember().getNext_election());


        ImageView twitter = rootView.findViewById(R.id.acct_twitter);
        ImageView facebook = rootView.findViewById(R.id.acct_facebook);
        ImageView youtube = rootView.findViewById(R.id.acct_youtube);
        ImageView gmail = rootView.findViewById(R.id.acct_gmail);
        ImageView website = rootView.findViewById(R.id.website);
        ImageView phone = rootView.findViewById(R.id.phone);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: TWITTER");
                if (tabContactListener != null) {
                    tabContactListener.openTwitter(congressOverviewVM.getCongressMember().getTwitter_account());
                    Log.d("OFRAG2", "onClick: TWITTER" + congressOverviewVM.getCongressMember().getTwitter_account());

                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: FACEBOOK");
                if (tabContactListener != null) {
                    tabContactListener.openFacebook(congressOverviewVM.getCongressMember().getFacebook_account());
                    Log.d("OFRAG2", "onClick: FACEBOOK" + congressOverviewVM.getCongressMember().getFacebook_account());

                }
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: YOUTUBE");

                if (tabContactListener != null) {
                    tabContactListener.openYoutube(congressOverviewVM.getCongressMember().getYoutube_account());
                    Log.d("OFRAG2", "onClick: YOUTUBE" + congressOverviewVM.getCongressMember().getYoutube_account());

                }

            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: GMAIL");
                if (tabContactListener != null) {
                    tabContactListener.openEmail(congressOverviewVM.getCongressMember().getContact_form());
                    Log.d("OFRAG2", "onClick: GMAIL" + " " + congressOverviewVM.getCongressMember().getContact_form());

                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: WEBSITE");

                if (tabContactListener != null) {
                    tabContactListener.openWebsite(congressOverviewVM.getCongressMember().getUrl());
                    Log.d("OFRAG2", "onClick: WEBSITE" + congressOverviewVM.getCongressMember().getUrl());

                }

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: PHONE");

                if (tabContactListener != null) {
                    tabContactListener.openPhont(congressOverviewVM.getCongressMember().getPhone());
                    Log.d("OFRAG2", "onClick: PHONE" + congressOverviewVM.getCongressMember().getPhone());

                }
            }
        });

    }
}