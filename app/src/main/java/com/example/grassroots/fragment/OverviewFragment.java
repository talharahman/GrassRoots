package com.example.grassroots.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public class OverviewFragment extends Fragment {

    private CongressOverviewVM congressOverviewVM;

    public OverviewFragment() {
    }

    public static OverviewFragment newInstance() {
        OverviewFragment overviewFragment = new OverviewFragment();
        return overviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);


        TextView ov_first_congressmember = view.findViewById(R.id.ov_first_congressmember);
        TextView ov_second_congressmember = view.findViewById(R.id.ov_second_congressmember);
        TextView ov_txtv_third_congressmember = view.findViewById(R.id.ov_txtv_third_congressmember);
        TextView ov_txtv_vs_votesRatio = view.findViewById(R.id.ov_txtv_vs_votesRatio);
        TextView ov_txtv_vs_pct_missed = view.findViewById(R.id.ov_txtv_vs_pct_missed);
        TextView ov_txtv_vs_pct_party = view.findViewById(R.id.ov_txtv_vs_pct_party);
        TextView ov_txtv_nextElection_congressmember = view.findViewById(R.id.ov_txtv_nextElection_congressmember);

        ov_first_congressmember.setText(congressOverviewVM.getCongressMember().getShort_title() + " " +
                congressOverviewVM.getCongressMember().getFirst_name() + " " +
                congressOverviewVM.getCongressMember().getLast_name());

        ov_second_congressmember.setText(congressOverviewVM.getCongressMember().getParty() + ", " +
                congressOverviewVM.getCongressMember().getState());

        ov_txtv_third_congressmember.setText(congressOverviewVM.getCongressMember().getFec_candidate_id());

        ov_txtv_vs_votesRatio.setText("Missed Votes / Total Votes Ratio: " + congressOverviewVM.getCongressMember().getMissed_votes() + " / " +
                congressOverviewVM.getCongressMember().getTotal_votes());

        ov_txtv_vs_pct_missed.setText("Missed Votes Percentage: " + congressOverviewVM.getCongressMember().getMissed_votes_pct());

        ov_txtv_vs_pct_party.setText("Votes with Party Percentage: " + congressOverviewVM.getCongressMember().getVotes_with_party_pct());

        ov_txtv_nextElection_congressmember.setText(congressOverviewVM.getCongressMember().getNext_election());
    }
}
