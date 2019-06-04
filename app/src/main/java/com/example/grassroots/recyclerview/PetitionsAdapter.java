package com.example.grassroots.recyclerview;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.PetitionFragmentsListener;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.utils.PetitionsFeedInterface;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionsAdapter extends RecyclerView.Adapter<PetitionViewHolder> {

    private List<Petition>petitionList;
    private PetitionFragmentsListener mListener;
    private PetitionsFeedInterface listener;

    public static final String TAG = "PetitionsAdapter";

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

    @RequiresApi(api = Build.VERSION_CODES.N)
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
