package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.model.ElectedRepresentatives;

import java.util.List;

public class CivicInfoAdapter extends RecyclerView.Adapter<CivicInfoViewHolder> {

    private List<ElectedRepresentatives> electedRepresentatives;

    public CivicInfoAdapter() {
    }

    public void setadapterList(List<ElectedRepresentatives> electedRepresentatives) {
        this.electedRepresentatives = electedRepresentatives;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CivicInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rep_directory_item_view, viewGroup, false);
        return new CivicInfoViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull CivicInfoViewHolder civicInfoViewHolder, int i) {
        civicInfoViewHolder.onBind(electedRepresentatives.get(i));
    }

    @Override
    public int getItemCount() {
        return electedRepresentatives.size();
    }
}