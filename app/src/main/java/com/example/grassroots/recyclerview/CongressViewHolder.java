package com.example.grassroots.recyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.activity.CongressTabActivity;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;

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

//        OfficeExpResult officeExpResult = new OfficeExpResult();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tabIntent = new Intent(v.getContext(), CongressTabActivity.class);
                tabIntent.putExtra("CONGRESSMEMBER", congressMember);
//                tabIntent.putExtra("OFFICERESULT", officeExpResult);
                v.getContext().startActivity(tabIntent);
            }
        });
    }
}
