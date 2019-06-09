package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.activity.UserActionActivity;
import com.example.grassroots.model.petition.Petition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserPetitionActivityViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public UserPetitionActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.petition_description_test);
    }

    public void onBind(Petition petition, String currentID) {

        for (int i = 0; i < petition.getSigners().size(); i++) {
            Log.d("PETITIONNAMESSS", "onBind: " + petition.getmPetitionName());
            Log.d("PETITIONNAMESSS", "onBind: " + petition.getSigners().get(i));
            Log.d("DOESTHISIDWORK", "onBind: " + currentID);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


            if (petition.getSigners().get(i).equals(currentUser.getUid())){
                textView.setText("You have signed"+ petition.getmPetitionName()) ;

            }
        }

//        Log.d("ISTHISWORKING?", "onBind: " + currentID);
    }
}
