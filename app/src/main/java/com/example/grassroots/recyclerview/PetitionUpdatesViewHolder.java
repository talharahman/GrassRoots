package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.PetitionUpdates;

public class PetitionUpdatesViewHolder extends RecyclerView.ViewHolder {
    private TextView petitionUpdateHeadlineTextView;
    private TextView pettitonUdpatesDescrptionTextView;
    private ImageView petitionUpdateImageImageView;

    public PetitionUpdatesViewHolder(@NonNull View itemView) {
        super(itemView);

        petitionUpdateHeadlineTextView= itemView.findViewById(R.id.petition_update_headline);
        pettitonUdpatesDescrptionTextView=itemView.findViewById(R.id.petition_updates_description);
        petitionUpdateImageImageView=itemView.findViewById(R.id.petition_update_image);
    }

    public void onBind(PetitionUpdates currentPetitionUpdate) {

        petitionUpdateHeadlineTextView.setText(currentPetitionUpdate.getmPetitionHeadLine());
        pettitonUdpatesDescrptionTextView.setText(currentPetitionUpdate.getmPetitionDescription());
        Glide.with(itemView.getContext())
                .load(currentPetitionUpdate.getmPetitionImageURL())
                .fitCenter()
                .centerCrop().fitCenter()
                .into(petitionUpdateImageImageView);

    }
}
