package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.utils.PetitionsFeedInterface;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionsAdapter extends RecyclerView.Adapter<PetitionViewHolder> {

    private List<Petition>petitionList;
    private PetitionsFeedInterface listener;

    public PetitionsAdapter(PetitionsFeedInterface listener) {
        this.listener = listener;
    }

    public void setAdapterList(List<Petition> petitionList){
        this.petitionList = petitionList;
    }

    @NonNull
    @Override
    public PetitionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childViewHolder= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itemview_petition,viewGroup,false);
        return new PetitionViewHolder(childViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionViewHolder petitionViewHolder, int i) {
        Petition currentPetition = petitionList.get(i);
        petitionViewHolder.onBind(currentPetition, listener);
    }

    @Override
    public int getItemCount() {
        return petitionList.size() ;
    }
}
