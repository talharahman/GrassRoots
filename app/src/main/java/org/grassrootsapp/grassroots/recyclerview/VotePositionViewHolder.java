package org.grassrootsapp.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.ProPublica.VotePositions.Votes;

public class VotePositionViewHolder extends RecyclerView.ViewHolder {

    TextView voteBillPostion;
    TextView voteBillDescription;
    TextView voteBillStatus;
    TextView voteBillID;
    TextView voteBillPurpose;
    TextView voteBillLatestAction;
    TextView showDetails;
    CardView vpBillExpanded;

    public VotePositionViewHolder(@NonNull View itemView) {
        super(itemView);
        voteBillID = itemView.findViewById(R.id.vp_bill_id);
        voteBillDescription = itemView.findViewById(R.id.vp_bill_description);
        voteBillPostion = itemView.findViewById(R.id.vp_bill_result);
        voteBillPurpose = itemView.findViewById(R.id.vp_bill_purpose_textview);
        voteBillStatus = itemView.findViewById(R.id.vp_bill_status);
        voteBillLatestAction = itemView.findViewById(R.id.vp_bill_latest_action);
        showDetails = itemView.findViewById(R.id.show_details);
        vpBillExpanded = itemView.findViewById(R.id.vote_position_bill_expanded);
    }

    public void onBind(Votes votes) {
        voteBillID.setText(votes.getBill().getBill_id());
        voteBillPurpose.setText(votes.getBill().getTitle());
        voteBillLatestAction.setText(votes.getBill().getLatest_action());
        voteBillDescription.setText(votes.getDescription());
        voteBillPostion.setText(votes.getPosition());
        voteBillStatus.setText(votes.getResult());

        boolean expanded = votes.isExpanded();
        if (expanded) {
            vpBillExpanded.setVisibility(View.VISIBLE);
            showDetails.setText("hide details");
        } else {
            vpBillExpanded.setVisibility(View.GONE);
            showDetails.setText("show details");
        }
    }
}

