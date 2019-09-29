package org.grassrootsapp.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;

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


        oe_category.setText(officeExpResult.getCategory());
        txtv_amt.setText("Amount: $" + String.format("%,.2f", officeExpResult.getAmount()));
        txtv_ytd.setText("Year-to-Date: $" + String.format("%,.2f", officeExpResult.getYear_to_date()));
        txtv_chng.setText("Change from Previous Quarter: $" + String.format("%,.2f", officeExpResult.getChange_from_previous_quarter()));

    }
}
