package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.grassroots.model.ElectedPositions;
import com.example.grassroots.model.ElectedRepresentatives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CivicInfoAdapter extends RecyclerView.Adapter<CivicInfoViewHolder> {

    private List<ElectedPositions> electedPositions = new ArrayList<>();
    private List<ElectedRepresentatives> electedRepresentatives = new ArrayList<>();

    public CivicInfoAdapter() {}

    public void setadapterList(List<ElectedPositions> electedPositions,
                               List<ElectedRepresentatives> electedRepresentatives) {
        this.electedRepresentatives = electedRepresentatives;
        this.electedPositions = electedPositions;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CivicInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CivicInfoViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.local_rep_itemview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CivicInfoViewHolder civicInfoViewHolder, int i) {
        HashMap<Integer, String> positionsMap = new HashMap<>();
        for (int j = 0; j < electedPositions.size(); j++) {
            for (int k = 0; k < electedPositions.get(j).getOfficialIndices().size(); k++) {
                positionsMap.put(electedPositions.get(j).getOfficialIndices().get(k), electedPositions.get(j).getName());
            }
        }
        civicInfoViewHolder.onBind(electedRepresentatives.get(i), positionsMap.get(i));
    }

    @Override
    public int getItemCount() {
        return electedRepresentatives.size();
    }
}