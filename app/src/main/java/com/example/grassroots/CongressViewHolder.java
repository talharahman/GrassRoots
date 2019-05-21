package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.model.ProPublica.Members.CongressMember;

public class CongressViewHolder extends RecyclerView.ViewHolder {

    private TextView txtv_name;
    private TextView txtv_party_title;
    
    public CongressViewHolder(@NonNull View itemView) {
        super(itemView);
        txtv_name = itemView.findViewById(R.id.congress_member_name_iv);
        txtv_party_title = itemView.findViewById(R.id.congress_party_title_iv);
    }

    public void onBind(CongressMember congressMember) {
        txtv_name.setText(String.format("%s %s", congressMember.getFirst_name(), congressMember.getLast_name()));
        txtv_party_title.setText(congressMember.getParty() + ", " + congressMember.getTitle());
    }
}
