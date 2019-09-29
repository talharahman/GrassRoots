package org.grassrootsapp.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.grassrootsapp.grassroots.R;

import org.grassrootsapp.grassroots.model.ProPublica.VotePositions.Votes;

import java.util.List;

public class VotePositionAdapter extends RecyclerView.Adapter<VotePositionViewHolder> {

    private List<Votes> vp_category_list;

    public VotePositionAdapter () {}

    public void setVp_category_list(List<Votes> vp_category_list) {
        this.vp_category_list = vp_category_list;
    }

    @NonNull
    @Override
    public VotePositionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     return new VotePositionViewHolder(LayoutInflater
             .from(viewGroup.getContext())
             .inflate(R.layout.itemview_vote_position_bills, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VotePositionViewHolder votePositionViewHolder, int i) {
        votePositionViewHolder.onBind(vp_category_list.get(i));

        votePositionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = vp_category_list.get(i).isExpanded();
                vp_category_list.get(i).setExpanded(!expanded);
                VotePositionAdapter.this.notifyItemChanged(i);
            }
        });
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
