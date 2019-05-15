package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.model.ElectedRepresentatives;

class CivicInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView representativeName;

    CivicInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        representativeName = itemView.findViewById(R.id.Representative_name);
    }

    void onBind(ElectedRepresentatives electedRepresentatives){
        representativeName.setText(electedRepresentatives.getName());
    }
}
