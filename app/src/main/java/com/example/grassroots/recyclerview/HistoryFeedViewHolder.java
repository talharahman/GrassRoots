package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.fragment.user.UserHistory;
import com.example.grassroots.model.petition.Petition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFeedViewHolder extends RecyclerView.ViewHolder {

    TextView textView_history;

    public HistoryFeedViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_history = itemView.findViewById(R.id.petition_description_user_history_test);
    }

    public void onBind(Petition petition, String currentID){


        textView_history.setText(petition.getmPetitionName() + "has been signed by 1 person." );


    }
}
