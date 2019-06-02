package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.R;

public class OverviewFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private CardView finSummary;
    private String fecID;
    private FloatingActionButton fabMain, fabTwitter, fabFacebook;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private boolean isFabOpen = false;
    public static final String ADDRESS = "https://www.fec.gov/data/candidate/";

    public OverviewFragment() {
    }

    public static OverviewFragment newInstance() {
        return new OverviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setButtons();
    }

    private void initialize() {
        CongressOverviewVM congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);

        TextView ov_first_congressmember = rootView.findViewById(R.id.ov_first_congressmember);
        TextView ov_second_congressmember = rootView.findViewById(R.id.ov_second_congressmember);
        TextView ov_txtv_third_congressmember = rootView.findViewById(R.id.ov_txtv_third_congressmember);
        TextView ov_txtv_vs_votesRatio = rootView.findViewById(R.id.ov_txtv_vs_votesRatio);
        TextView ov_txtv_vs_pct_missed = rootView.findViewById(R.id.ov_txtv_vs_pct_missed);
        TextView ov_txtv_vs_pct_party = rootView.findViewById(R.id.ov_txtv_vs_pct_party);
        TextView ov_txtv_nextElection_congressmember = rootView.findViewById(R.id.ov_txtv_nextElection_congressmember);

        ov_first_congressmember.setText(
                congressOverviewVM.getCongressMember().getShort_title() + " " +
                congressOverviewVM.getCongressMember().getFirst_name() + " " +
                congressOverviewVM.getCongressMember().getLast_name());

        ov_second_congressmember.setText(
                congressOverviewVM.getCongressMember().getParty() + ", " +
                congressOverviewVM.getCongressMember().getState());

        fecID = congressOverviewVM.getCongressMember().getFec_candidate_id();
        ov_txtv_third_congressmember.setText(fecID);

        ov_txtv_vs_votesRatio.setText(
                "Missed Votes / Total Votes Ratio: " + congressOverviewVM.getCongressMember().getMissed_votes() + " / " +
                congressOverviewVM.getCongressMember().getTotal_votes());

        ov_txtv_vs_pct_missed.setText(
                "Missed Votes Percentage: " + congressOverviewVM.getCongressMember().getMissed_votes_pct());

        ov_txtv_vs_pct_party.setText(
                "Votes with Party Percentage: " + congressOverviewVM.getCongressMember().getVotes_with_party_pct());

        ov_txtv_nextElection_congressmember.setText(
                congressOverviewVM.getCongressMember().getNext_election());
    }

    private void setButtons() {
        finSummary = rootView.findViewById(R.id.overview_first_cardview);
        fabMain = rootView.findViewById(R.id.fab_main);
        fabTwitter = rootView.findViewById(R.id.fab_twitter);
        fabFacebook = rootView.findViewById(R.id.fab_facebook);

        fab_open = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_backward);

        finSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ADDRESS + fecID + "/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        fabMain.setOnClickListener(this);
        fabTwitter.setOnClickListener(this);
        fabFacebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_main:
                animateFAB();
                break;
            case R.id.fab_twitter:
                break;
            case R.id.fab_facebook:
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fabMain.startAnimation(rotate_backward);

            fabTwitter.startAnimation(fab_close);
            fabFacebook.startAnimation(fab_close);

            fabTwitter.setClickable(false);
            fabFacebook.setClickable(false);

            isFabOpen = false;
        } else {
            fabMain.startAnimation(rotate_forward);

            fabTwitter.startAnimation(fab_open);
            fabFacebook.startAnimation(fab_open);

            fabTwitter.setClickable(true);
            fabFacebook.setClickable(true);

            isFabOpen = true;
        }
    }
}