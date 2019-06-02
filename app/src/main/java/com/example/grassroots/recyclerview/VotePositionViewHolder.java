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
    TextView voteSummary;
    TextView voteBillID;
    TextView voteBillTitle;
    TextView voteBillLatestAction;

    public VotePositionViewHolder(@NonNull View itemView) {
        super(itemView);
        votePostion= itemView.findViewById(R.id.txt_position);
        voteDescription= itemView.findViewById(R.id.txt_description);
        voteSummary= itemView.findViewById(R.id.txt_summary);
        voteBillID = itemView.findViewById(R.id.txt_bill_id);
        voteBillTitle = itemView.findViewById(R.id.txt_bill_title);
        voteBillLatestAction = itemView.findViewById(R.id.txt_bill_latest_action);
    }

    public void onBind(VotePositionResults votes) {
        voteBillID.setText(votes.getVotes().get(0).getBill().getBill_id());
        voteBillTitle.setText(votes.getVotes().get(0).getBill().getTitle());
        voteBillLatestAction.setText(votes.getVotes().get(0).getBill().getLatest_action());
        voteDescription.setText(votes.getVotes().get(0).getDescription());
        votePostion.setText(votes.getVotes().get(0).getPosition());
        voteSummary.setText(votes.getVotes().get(0).getResult());

    }
}

