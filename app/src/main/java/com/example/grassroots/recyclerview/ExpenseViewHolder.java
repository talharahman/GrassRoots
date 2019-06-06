package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {

    private TextView oe_category;
    private TextView txtv_amt;
    private TextView txtv_ytd;
    private TextView txtv_chng;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);

        oe_category = itemView.findViewById(R.id.oe_category);
        txtv_amt = itemView.findViewById(R.id.txtv_amt);
        txtv_ytd = itemView.findViewById(R.id.txtv_ytd);
        txtv_chng = itemView.findViewById(R.id.txtv_chng);
    }

    public void onBind(OfficeExpResult officeExpResult) {
        Log.d("give me stuff", officeExpResult.getCategory());


        oe_category.setText(officeExpResult.getCategory());
        txtv_amt.setText("Amount: $" + String.valueOf(officeExpResult.getAmount()));
        txtv_ytd.setText("Year-to-Date: $" + String.valueOf(officeExpResult.getYear_to_date()));
        txtv_chng.setText("Change from Previous Quarter: $" + String.valueOf(officeExpResult.getChange_from_previous_quarter()));

    }
}
