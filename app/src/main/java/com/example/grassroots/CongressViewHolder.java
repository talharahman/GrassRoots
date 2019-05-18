package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.model.CongressMember;

public class CongressViewHolder extends RecyclerView.ViewHolder {

    private TextView txtv_name;
    
    public CongressViewHolder(@NonNull View itemView) {
        super(itemView);
        txtv_name = itemView.findViewById(R.id.txtv_name);
    }

    public void onBind(CongressMember congressMember) {
        txtv_name.setText(String.format("%s %s", congressMember.getFirst_name(), congressMember.getLast_name()));
    }
}
