package com.example.grassroots.recyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.CongressTabActivity;
import com.example.grassroots.MainActivity;
import com.example.grassroots.R;
import com.example.grassroots.fragment.OverviewFragment;
import com.example.grassroots.model.ProPublica.Members.CongressMember;

import java.util.Objects;

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
