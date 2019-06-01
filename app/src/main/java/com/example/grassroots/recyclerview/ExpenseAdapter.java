package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

//    private List<OfficeExpenseResponse> category_list;

//    public ExpenseAdapter(){}
//
//    public void setCategory_list(List<OfficeExpResult> category_list) {
//        this.category_list = category_list;
//        notifyDataSetChanged();
//    }
//   public ExpenseAdapter(List<OfficeExpenseResponse> category_list){
//        this.category_list = category_list;

    private List<OfficeExpResult> category_list;

    public ExpenseAdapter(List<OfficeExpResult> category_list) {
        this.category_list = category_list;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_oe_category, viewGroup, false);
        return new ExpenseViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder expenseViewHolder, int i) {
        expenseViewHolder.onBind(category_list.get(i));
//        Log.d("FIND", "onBindViewHolder: " + category_list.get(0));
    }

    @Override
    public int getItemCount() {
        return category_list.size();
    }
}
