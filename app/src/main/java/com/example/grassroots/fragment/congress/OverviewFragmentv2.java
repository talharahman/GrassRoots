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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;

public class OverviewFragmentv2 extends Fragment {

    private View rootView;
    private String fecID;
    public static final String ADDRESS = "https://www.fec.gov/data/candidate/";

    public OverviewFragmentv2() {
    }

    public static OverviewFragmentv2 newInstance() {
        return new OverviewFragmentv2();
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
    }

    private void initialize() {
        CongressOverviewVM congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);

        TextView txt_title_name = rootView.findViewById(R.id.txt_title_name);
        TextView txt_party_state = rootView.findViewById(R.id.txt_party_state);
        TextView txt_fec_id = rootView.findViewById(R.id.txt_fec_id_number);
        TextView txt_year = rootView.findViewById(R.id.txt_year);


//        TextView ov_txtv_vs_votesRatio = rootView.findViewById(R.id.ov_txtv_vs_votesRatio);
//        TextView ov_txtv_vs_pct_missed = rootView.findViewById(R.id.ov_txtv_vs_pct_missed);
//        TextView ov_txtv_vs_pct_party = rootView.findViewById(R.id.ov_txtv_vs_pct_party);
//        TextView ov_txtv_nextElection_congressmember = rootView.findViewById(R.id.ov_txtv_nextElection_congressmember);

        txt_title_name.setText(
                congressOverviewVM.getCongressMember().getShort_title() + " " +
                congressOverviewVM.getCongressMember().getFirst_name() + " " +
                congressOverviewVM.getCongressMember().getLast_name());

        txt_party_state.setText(
                congressOverviewVM.getCongressMember().getParty() + ", " +
                congressOverviewVM.getCongressMember().getState());

        fecID = congressOverviewVM.getCongressMember().getFec_candidate_id();
        txt_fec_id.setText(fecID);

        txt_year.setText(congressOverviewVM.getCongressMember().getNext_election());

//        ov_txtv_vs_votesRatio.setText(
//                "Missed Votes / Total Votes Ratio: " + congressOverviewVM.getCongressMember().getMissed_votes() + " / " +
//                congressOverviewVM.getCongressMember().getTotal_votes());
//
//        ov_txtv_vs_pct_missed.setText(
//                "Missed Votes Percentage: " + congressOverviewVM.getCongressMember().getMissed_votes_pct());
//
//        ov_txtv_vs_pct_party.setText(
//                "Votes with Party Percentage: " + congressOverviewVM.getCongressMember().getVotes_with_party_pct());
//
//        ov_txtv_nextElection_congressmember.setText(
//                congressOverviewVM.getCongressMember().getNext_election());
    }
}