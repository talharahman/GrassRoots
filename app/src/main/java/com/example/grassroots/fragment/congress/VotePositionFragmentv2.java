package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;
import com.example.grassroots.network.ProPublica.VotePositions.VotePostitionPresenter;
import com.example.grassroots.recyclerview.VotePositionAdapter;
import com.example.grassroots.utils.VotePositionUIListener;

public class VotePositionFragmentv2 extends Fragment {

    private CongressOverviewVM congressOverviewVM;

    private String member_id;


    public static final String TAG = "HERE";

    private VotePositionAdapter votePositionAdapter;
    private RecyclerView recyclerView;

    public VotePositionFragmentv2() {}

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

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();

        TextView vp_txt_title_name = view.findViewById(R.id.vp_txt_title_name);
        vp_txt_title_name.setText(congressOverviewVM.getCongressMember().getShort_title() + " "
                + congressOverviewVM.getCongressMember().getFirst_name() + " "
                + congressOverviewVM.getCongressMember().getLast_name());


        VotePostitionPresenter votePostitionPresenter = new VotePostitionPresenter(new VotePositionUIListener() {
            @Override
            public void updateUI(VotePositionResponse votePositionResponse) {
                Log.d(TAG, "updateUI: " + votePositionResponse.getStatus().toString());
                votePositionAdapter = new VotePositionAdapter(votePositionResponse.getResults().get(0).getVotes());
                recyclerView.setAdapter(votePositionAdapter);
                votePositionAdapter.notifyDataSetChanged();
            }
        });

        votePostitionPresenter.networkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),member_id);

    }

}