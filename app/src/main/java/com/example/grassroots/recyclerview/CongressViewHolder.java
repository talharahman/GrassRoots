package com.example.grassroots.recyclerview;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;

class CongressViewHolder extends RecyclerView.ViewHolder {

    private TextView txtv_name;
    private TextView txtv_party_title;
    private TextView txtv_state;
    private CardView congress_cardview;
    
    CongressViewHolder(@NonNull View itemView) {
        super(itemView);
        txtv_name = itemView.findViewById(R.id.congress_member_name_iv);
        txtv_party_title = itemView.findViewById(R.id.congress_title_iv);
        txtv_state = itemView.findViewById(R.id.congress_state_iv);
        congress_cardview = itemView.findViewById(R.id.congress_cardview);
    }

    void onBind(CongressMember congressMember) {
        txtv_name.setText(String.format("%s %s", congressMember.getFirst_name(), congressMember.getLast_name()));
        txtv_state.setText(congressMember.getState());
        txtv_party_title.setText(congressMember.getTitle());

        if (congressMember.getParty().equals("D")) {
            congress_cardview.setCardBackgroundColor(Color.rgb(129, 163, 251));
        } else if (congressMember.getParty().equals("R")) {
            congress_cardview.setCardBackgroundColor(Color.rgb(234, 94, 128));
        } else {
            congress_cardview.setCardBackgroundColor(Color.WHITE);
        }
    }
}
