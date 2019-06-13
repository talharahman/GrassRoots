package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;
import com.example.grassroots.model.ProPublica.VotePositions.Votes;
import com.example.grassroots.network.ProPublica.VotePositions.VotePostitionPresenter;
import com.example.grassroots.recyclerview.VotePositionAdapter;
import com.example.grassroots.utils.VotePositionUIListener;


import java.util.ArrayList;
import java.util.List;

public class VotePositionFragmentv2 extends Fragment implements SearchView.OnQueryTextListener {

    public static final String TAG = "HERE";

    private VotePositionAdapter votePositionAdapter;
    private RecyclerView recyclerView;

    private List<Votes> votesList = new ArrayList<>();

    public VotePositionFragmentv2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_votepositions_design_v2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_vote_positions);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        votePositionAdapter = new VotePositionAdapter();

        final SearchView searchView = view.findViewById(R.id.sv_bill_deets);
        searchView.setOnQueryTextListener(this);

        CongressOverviewVM congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        String member_id = congressOverviewVM.getCongressMember().getId();
        Log.d(TAG, "current memberID: " + member_id);

        TextView txt_missed_total = view.findViewById(R.id.txt_missed_total);
        txt_missed_total.setText(String.format("%s / %s", congressOverviewVM.getCongressMember().getMissed_votes(), congressOverviewVM.getCongressMember().getTotal_votes()));

        TextView txt_missed_votes = view.findViewById(R.id.txt_missed_votes);
        txt_missed_votes.setText(String.format("%s%%", congressOverviewVM.getCongressMember().getMissed_votes_pct()));

        TextView txt_votes_with_party = view.findViewById(R.id.txt_votes_with_party);
        txt_votes_with_party.setText(String.format("%s%%", congressOverviewVM.getCongressMember().getVotes_with_party_pct()));

        VotePostitionPresenter votePostitionPresenter = new VotePostitionPresenter(new VotePositionUIListener() {
            @Override
            public void updateUI(VotePositionResponse votePositionResponse) {
                votesList = votePositionResponse.getResults().get(0).getVotes();
                Log.d(TAG, "updateUI: " + votePositionResponse.getResults().get(0).getMember_id());
                votePositionAdapter.setVp_category_list(votesList);
                recyclerView.setAdapter(votePositionAdapter);
                votePositionAdapter.notifyDataSetChanged();
            }
        });
        votePostitionPresenter.networkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key), member_id);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Votes> newVotesList = new ArrayList<>();
        for (Votes votes : votesList) {
            if (votes.getDescription().toLowerCase().contains(newText.toLowerCase())) {
                newVotesList.add(votes);
            }
            /*if (votes.getBill().getTitle().toLowerCase().contains(newText.toLowerCase())) {
                newVotesList.add(votes);
            }*/
        }
        votePositionAdapter.setData(newVotesList);
        return false;
    }
}
