package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    private CardView user_activity_cardview;

    UserPetitionActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_activity = itemView.findViewById(R.id.petition_description_activity);
        imageview_activity = itemView.findViewById(R.id.petition_image_activity);
        user_activity_cardview = itemView.findViewById(R.id.user_activity_cardview);
    }

    public void onBind(Petition petition, String currentID) {

        for (int i = 0; i < petition.getSigners().size(); i++) {


            FirebaseAuth currentUser = FirebaseAuth.getInstance();

            if (petition.getSigners().get(i).equals(currentUser.getUid())){
                textView_activity.setText("1 person has signed your petition\n" + petition.getmPetitionName());
                Glide.with(itemView.getContext()).load(petition.getmPetitionImageURL()).optionalFitCenter().centerCrop().into(imageview_activity);
            }
        }

//        Log.d("ISTHISWORKING?", "onBind: " + currentID);
    }
}
