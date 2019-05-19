package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.model.ProPublica.Bills.BillDetailModel;

public class BillsViewHolder extends RecyclerView.ViewHolder {

    TextView billName;

    public BillsViewHolder(@NonNull View itemView) {
        super(itemView);
        setBillReferences(itemView);
    }

    void onBind(BillDetailModel billDetailModel) {
        billName.setText(billDetailModel.getTitle());
    }

    void setBillReferences(View itemView){
        billName = itemView.findViewById(R.id.bill_name);
    }
}
