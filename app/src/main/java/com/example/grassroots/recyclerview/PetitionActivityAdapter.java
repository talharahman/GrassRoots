package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.fragment.user.UserHistory;
import com.example.grassroots.model.petition.Petition;

import java.util.ArrayList;
import java.util.List;

public class PetitionActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    final int MY_ACTIVITY_TYPE = 0;
    final int USER_HISTORY_TYPE = 1;
    private List<Petition> petitionList = new ArrayList<>();
    private List<Petition> petitionList1 = new ArrayList<>();
//    private List<MyActivity> myActivityList = new ArrayList<>();
    HistoryFeedViewHolder historyFeedViewHolder;
    UserPetitionActivityViewHolder userPetitionActivityViewHolder;


    public PetitionActivityAdapter() {}

    public void setPetitionActivityAdapterList(List<Petition> petitionList, List<Petition> petitionList1) {
        this.petitionList = petitionList;
        this.petitionList1 = petitionList1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        switch (getItemViewType(i)){
            case MY_ACTIVITY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_user_activity,viewGroup,false);
                return new UserPetitionActivityViewHolder(itemView);
            case USER_HISTORY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_petition_history,viewGroup,false);
                return new HistoryFeedViewHolder(itemView);
                default:
                    return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int item;
        if (getItemViewType(i) == MY_ACTIVITY_TYPE){
            userActivityFeedItemView((UserPetitionActivityViewHolder) viewHolder, i);
        }
        Log.d("FINDME", "onBindViewHolder: " + getItemViewType(i));
        if (getItemViewType(i) == USER_HISTORY_TYPE){
            historyFeedItemView((HistoryFeedViewHolder) viewHolder, i);
        }
    }

    private void historyFeedItemView(HistoryFeedViewHolder historyViewHolder, int position) {

        if (position<= petitionList1.size()) {
            ((HistoryFeedViewHolder) historyViewHolder).onBind(petitionList1.get(position));
        }
    }

    private void userActivityFeedItemView(UserPetitionActivityViewHolder userPetitionActivityViewHolder, int position) {
        if (position> petitionList1.size()) {
            ((UserPetitionActivityViewHolder) userPetitionActivityViewHolder).onBind(petitionList1.get(position));
        }
    }
        @Override
    public int getItemCount() {
        return petitionList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position >= petitionList1.size()) {
            return MY_ACTIVITY_TYPE;

        }

        if (position - 1 <= petitionList1.size()) {
            return USER_HISTORY_TYPE;
        }

        return -1;
    }
}
