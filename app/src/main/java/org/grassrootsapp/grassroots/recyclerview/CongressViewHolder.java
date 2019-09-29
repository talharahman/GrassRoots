package org.grassrootsapp.grassroots.recyclerview;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.grassrootsapp.grassroots.activity.CongressTabActivity;
import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.ProPublica.Members.CongressMember;

class CongressViewHolder extends RecyclerView.ViewHolder {

    private TextView txtv_name;
    private TextView txtv_party_title;
    private TextView txtv_state;
    private ImageView party_square;

    CongressViewHolder(@NonNull View itemView) {
        super(itemView);
        txtv_name = itemView.findViewById(R.id.congress_member_name_iv);
        txtv_party_title = itemView.findViewById(R.id.congress_title_iv);
        txtv_state = itemView.findViewById(R.id.congress_state_iv);
        party_square = itemView.findViewById(R.id.party_square);
    }

    void onBind(CongressMember congressMember) {

        txtv_name.setText(String.format("%s %s", congressMember.getFirst_name(), congressMember.getLast_name()));
        txtv_state.setText(congressMember.getState());
        txtv_party_title.setText(congressMember.getTitle());

        if (congressMember.getParty().equals("D")) {
            party_square.setBackgroundColor(Color.rgb(129, 163, 251));
        } else if (congressMember.getParty().equals("R")) {
            party_square.setBackgroundColor(Color.rgb(234, 94, 128));
        } else {
            party_square.setImageResource(R.drawable.rectangle);
        }


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tabIntent = new Intent(v.getContext(), CongressTabActivity.class);
                tabIntent.putExtra("CONGRESSMEMBER", congressMember);
                v.getContext().startActivity(tabIntent);
            }
        });
    }
}
