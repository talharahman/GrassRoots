package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;

public class HistoryFeedViewHolder extends RecyclerView.ViewHolder {

    private TextView textView_history;
    private ImageView imageview_history;

    HistoryFeedViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_history = itemView.findViewById(R.id.petition_description_history);
        imageview_history = itemView.findViewById(R.id.petition_image_history);
    }

    public void onBind(Petition petition, String currentID){
        textView_history.setText("You have signed\n" + petition.getmPetitionName());
        Glide.with(itemView.getContext()).load(petition.getmPetitionImageURL()).optionalFitCenter().centerCrop().into(imageview_history);
    }
}
