package org.grassrootsapp.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.petition.Petition;

import java.util.ArrayList;
import java.util.List;

public class PetitionActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int viewType;
    private String currentID;
    private final int MY_ACTIVITY_TYPE = 0;
    private final int USER_HISTORY_TYPE = 1;
    private List<Petition> petitionList;

    public PetitionActivityAdapter() {}

    public void setPetitionActivityAdapterList(List<Petition> petitionList, String currentID, int viewType) {
        this.petitionList = petitionList;
        this.currentID = currentID;
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        switch (viewType){
            case MY_ACTIVITY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_user_activity, viewGroup,false);
                return new UserPetitionActivityViewHolder(itemView);
            case USER_HISTORY_TYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_user_history, viewGroup,false);
                return new HistoryFeedViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == MY_ACTIVITY_TYPE){
            userActivityFeedItemView((UserPetitionActivityViewHolder) viewHolder,currentID, i);
        }
        if (getItemViewType(i) == USER_HISTORY_TYPE){
            historyFeedItemView((HistoryFeedViewHolder) viewHolder, currentID, i);
        }
    }

    private void historyFeedItemView(HistoryFeedViewHolder historyViewHolder, String currentID, int position) {
        (historyViewHolder).onBind(petitionList.get(position), currentID);

    }

    private void userActivityFeedItemView(UserPetitionActivityViewHolder userPetitionActivityViewHolder, String currentID, int position) {
        (userPetitionActivityViewHolder).onBind(petitionList.get(position), currentID);
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
