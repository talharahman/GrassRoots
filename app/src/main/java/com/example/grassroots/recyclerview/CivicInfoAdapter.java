package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfo.ElectedPositions;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CivicInfoAdapter extends RecyclerView.Adapter<CivicInfoViewHolder> {

    private List<ElectedPositions> electedPositions = new ArrayList<>();
    private List<ElectedRepresentatives> electedRepresentatives = new ArrayList<>();

    public CivicInfoAdapter() {}

    public void setAdapterList(List<ElectedPositions> electedPositions,
                               List<ElectedRepresentatives> electedRepresentatives) {
        this.electedRepresentatives = electedRepresentatives;
        this.electedPositions = electedPositions;
        notifyDataSetChanged();
    }

    private <E> void reverseList(List<E> list) {
        Collections.reverse(list);

    }

    @NonNull
    @Override
    public CivicInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CivicInfoViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.expanded_local_rep_itemview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CivicInfoViewHolder civicInfoViewHolder, final int i) {
        HashMap<Integer, String> positionsMap = new HashMap<>();
        for (int j = 0; j < electedPositions.size(); j++) {
            for (int k = 0; k < electedPositions.get(j).getOfficialIndices().size(); k++) {
                positionsMap.put(electedPositions.get(j).getOfficialIndices().get(k), electedPositions.get(j).getName());
            }
        }
        civicInfoViewHolder.onBind(electedRepresentatives.get(i), positionsMap.get(i));

        civicInfoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = electedRepresentatives.get(i).isExpanded();
                electedRepresentatives.get(i).setExpanded(!expanded);
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electedRepresentatives.size();
    }
}