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
        votePostion= itemView.findViewById(R.id.txt_positionv2);
        voteDescription= itemView.findViewById(R.id.txt_descriptionv2);
        voteSummary= itemView.findViewById(R.id.txt_summaryv2);
        voteBillID = itemView.findViewById(R.id.txt_bill_idv2);
        voteBillTitle = itemView.findViewById(R.id.txt_bill_purposev2);
        voteBillLatestAction = itemView.findViewById(R.id.txt_bill_latest_actionv2);
    }

    public void onBind(Votes votes) {
        voteBillID.setText(votes.getBill().getBill_id());
        voteBillTitle.setText(votes.getBill().getTitle());
        voteBillLatestAction.setText(votes.getBill().getLatest_action());
        voteDescription.setText(votes.getDescription());
        votePostion.setText(votes.getPosition());
        voteSummary.setText(votes.getResult());
    }
}

