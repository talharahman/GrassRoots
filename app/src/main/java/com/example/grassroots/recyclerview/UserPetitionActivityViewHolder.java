package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserPetitionActivityViewHolder extends RecyclerView.ViewHolder {

    private TextView textView_activity;
    private ImageView imageview_activity;

    UserPetitionActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_activity = itemView.findViewById(R.id.petition_description_activity);
        imageview_activity = itemView.findViewById(R.id.petition_image_activity);
    }

    public void onBind(Petition petition, String currentID) {

        for (int i = 0; i < petition.getSigners().size(); i++) {
            Log.d("PETITIONNAMESSS", "onBind: " + petition.getmPetitionName());
            Log.d("PETITIONNAMESSS", "onBind: " + petition.getSigners().get(i));
            Log.d("DOESTHISIDWORK", "onBind: " + currentID);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

            if (petition.getSigners().get(i).equals(currentUser.getUid())){
                textView_activity.setText("1 person has signed your petition\n" + petition.getmPetitionName());
                Glide.with(itemView.getContext()).load(petition.getmPetitionImageURL()).optionalFitCenter().centerCrop().into(imageview_activity);
            }
        }

//        Log.d("ISTHISWORKING?", "onBind: " + currentID);
    }
}
