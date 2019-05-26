package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;

public class PetitionViewHolder extends RecyclerView.ViewHolder {

    public TextView petitionNameTextView;
    public ImageView petitionImageImageView;

    public PetitionViewHolder(@NonNull View itemView) {
        super(itemView);
        petitionNameTextView=itemView.findViewById(R.id.petition_name_text_view);
        petitionImageImageView=itemView.findViewById(R.id.petition_image_image_view);
    }
}
