package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResults;
import com.example.grassroots.model.ProPublica.VotePositions.Votes;

import java.util.ArrayList;

public class VotePositionViewHolder extends RecyclerView.ViewHolder {

    TextView votePostion;
    TextView voteDescription;
    TextView voteID;

    public VotePositionViewHolder(@NonNull View itemView) {
        super(itemView);
        votePostion= itemView.findViewById(R.id.txt_category);
        voteDescription= itemView.findViewById(R.id.txt_description);
        voteID= itemView.findViewById(R.id.txt_position);
    }

    public void onBind(VotePositionResults votes) {
        voteDescription.setText(votes.getVotes().get(0).getPosition());
        votePostion.setText(votes.getVotes().get(0).getDescription());
        voteID.setText(votes.getVotes().get(0).getResult());
    }
}

