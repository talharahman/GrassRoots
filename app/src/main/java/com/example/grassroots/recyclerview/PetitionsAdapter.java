package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionsAdapter extends RecyclerView.Adapter<PetitionViewHolder> {
    private List<Petition>petitionList;
    public static final String TAG = "PetitionsAdapter";

    public PetitionsAdapter(){

    }
    public void setAdapterList(List<Petition> petitionList){
        this.petitionList=petitionList;
    }

    @NonNull
    @Override
    public PetitionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View childViewHolder= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itemview_petition,viewGroup,false);

        return new PetitionViewHolder(childViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionViewHolder petitionViewHolder, int i) {
        Petition currentPetition=petitionList.get(i);
        petitionViewHolder.petitionNameTextView.setText(currentPetition.getmPetitionName());
        Log.d(TAG, "onBindViewHolder: " + currentPetition.getmPetitionImageURL());
//        Picasso.get()
//                .load("https://firebasestorage.googleapis.com/v0/b/grassroots-93865.appspot.com/o?name=uploads%2F1558803233495.jpg")
//                .fit()
//                .centerCrop()
//                .into(petitionViewHolder.petitionImageImageView);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("uploads");



        Glide.with(getApplicationContext())
                .load(storageReference)
                .into(petitionViewHolder.petitionImageImageView);
    }

    @Override
    public int getItemCount() {
        return petitionList.size() ;
    }
}
