package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;

import java.util.ArrayList;
import java.util.List;

public class PetitionActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int viewType;
    String currentID;
    final int MY_ACTIVITY_TYPE = 0;
    final int USER_HISTORY_TYPE = 1;
    private List<Petition> petitionList;
    private List<Petition> petitionList1;
    private List<Petition> petitionListWithSignatures;
//    private List<MyActivity> myActivityList = new ArrayList<>();
    HistoryFeedViewHolder historyFeedViewHolder;
    UserPetitionActivityViewHolder userPetitionActivityViewHolder;
    private List<String> totalSignatures = new ArrayList<>();


    public PetitionActivityAdapter() {}

    public void setPetitionActivityAdapterList(List<Petition> petitionList, String currentID, int viewType) {
        this.petitionList = petitionList;
        this.currentID = currentID;
        Log.d("ADAPTERCURRENTID", "setPetitionActivityAdapterList: " + this.currentID);
        Log.d("ADAPTERCURRENTID", "setPetitionActivityAdapterList: " + currentID);
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        switch (viewType){
            case MY_ACTIVITY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_user_activity,viewGroup,false);
                return new UserPetitionActivityViewHolder(itemView);
            case USER_HISTORY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_user_history,viewGroup,false);
                return new HistoryFeedViewHolder(itemView);
                default:
                    return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int item;
        if (getItemViewType(i) == MY_ACTIVITY_TYPE){
            userActivityFeedItemView((UserPetitionActivityViewHolder) viewHolder,currentID, i);
        }
        if (getItemViewType(i) == USER_HISTORY_TYPE){
            historyFeedItemView((HistoryFeedViewHolder) viewHolder, currentID, i);
        }
    }

    private void historyFeedItemView(HistoryFeedViewHolder historyViewHolder, String currentID, int position) {

        List<String> totalSignatures = new ArrayList<>();

//        for (int i = 0; i < petitionList1.size(); i++) {
//            for (int j = 0; j < petitionList1.get(i).getSigners().size(); j++) {
//                Log.d("PETITIONNAME", "onBind: " + petitionList1.size());
//                Log.d("PETITIONNAME", "onBind: " + petitionList1.get(i).getmPetitionName());
//                Log.d("PETITIONNAME", "onBind: " + petitionList1.get(i).getSigners().size());
//            }
//        }



        ((HistoryFeedViewHolder) historyViewHolder).onBind( petitionList.get(position), currentID);

    }

    private void userActivityFeedItemView(UserPetitionActivityViewHolder userPetitionActivityViewHolder, String currentID, int position) {
//        while (position< petitionListWithSignatures.size()) {
            ((UserPetitionActivityViewHolder) userPetitionActivityViewHolder).onBind(petitionList.get(position), currentID);
//        }
    }
        @Override
    public int getItemCount() {
        return petitionList.size();
    }

    @Override
    public int getItemViewType(int position) {

//        if (position >= petitionList1.size()) {
//            return MY_ACTIVITY_TYPE;
//
//        }
//
//        if (position - 1 <= petitionList1.size()) {
//            return USER_HISTORY_TYPE;
//        }

        return viewType;
    }
}
