package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResults;
import com.example.grassroots.model.ProPublica.VotePositions.Votes;

import java.util.ArrayList;
import java.util.List;

public class VotePositionAdapter extends RecyclerView.Adapter<VotePositionViewHolder> {

    private List<Votes> vp_category_list;

    public VotePositionAdapter(List<Votes> vp_category_list){
        this.vp_category_list = vp_category_list;
    }

    @NonNull
    @Override
    public VotePositionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_bill_deets_design_v2, viewGroup, false);
        return new VotePositionViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull VotePositionViewHolder votePositionViewHolder, int i) {
        votePositionViewHolder.onBind(vp_category_list.get(i));
    }

    @Override
    public int getItemCount() {
        return vp_category_list.size();
    }

    public void setData(List<Votes> newVotesList) {
        this.vp_category_list = newVotesList;
        notifyDataSetChanged();
    }
}
