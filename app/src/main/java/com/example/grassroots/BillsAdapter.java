package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.model.ProPublica.Bills.BillDetailModel;

import java.util.List;

public class BillsAdapter extends RecyclerView.Adapter<BillsViewHolder> {

    private List<BillDetailModel> billDetailModelList;

    public BillsAdapter() {}

    public void setBillAdapterList(List<BillDetailModel> billDetailModelList) {
        this.billDetailModelList = billDetailModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_bill_detail,viewGroup,false);
        return new BillsViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder billsViewHolder, int i) {
        billsViewHolder.onBind(billDetailModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return billDetailModelList.size();
    }
}
