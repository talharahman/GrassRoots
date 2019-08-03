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
import com.example.grassroots.model.petition.PetitionSignatures;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HistoryFeedViewHolder extends RecyclerView.ViewHolder {

    private TextView textView_history;
    private ImageView imageview_history;
    private List<Petition> petitionListWithSignatures= new ArrayList<>();

    HistoryFeedViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_history = itemView.findViewById(R.id.petition_description_history);
        imageview_history = itemView.findViewById(R.id.petition_image_history);
    }

    public void onBind(Petition petition, String currentID) {

//        petitionListWithSignatures = new ArrayList<>();
//
//
//            if (petition.getPetitionSignatures().size() > 0) {
//                petitionListWithSignatures.add(petition);
//            }
//
//        for (Petition addedPetition :
//                petitionListWithSignatures) {
//            for (int i = 0; i < addedPetition.getPetitionSignatures().size(); i++) {
//                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

//                if (petition.getPetitionSignatures().get(i).getSignatureID().equals(currentUser.getUid())) {
                    textView_history.setText("You have signed\n" + petition.getmPetitionName());
                    Glide.with(itemView.getContext()).load(petition.getmPetitionImageURL()).optionalFitCenter().centerCrop().into(imageview_history);
//                }
            }


}
