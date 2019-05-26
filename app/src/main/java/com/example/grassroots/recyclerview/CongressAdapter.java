package com.example.grassroots.recyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;

import java.util.List;

public class CongressAdapter extends RecyclerView.Adapter<CongressViewHolder> {

    private List<CongressMember> members;
    private Context mContext;


    public CongressAdapter(List<CongressMember> members){
        this.members = members;
    }

    @NonNull
    @Override
    public CongressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_congress, viewGroup,false);
        return new CongressViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull CongressViewHolder congressViewHolder, int i) {
        congressViewHolder.onBind(members.get(i));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setData(List<CongressMember> newMemberList) {
        this.members = newMemberList;
        notifyDataSetChanged();
    }
}
