package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;

public class UserPetitionActivityViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public UserPetitionActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.petition_description_test);
    }

    public void onBind(Petition petition) {

        Log.d("ISTHISWORKING?", "onBind: " + petition.getmPetitionName());
        textView.setText(petition.getmPetitionName());
    }
}
