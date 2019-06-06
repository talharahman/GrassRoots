package com.example.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grassroots.R;

import org.w3c.dom.Text;

public class HistoryFeedViewHolder extends RecyclerView.ViewHolder {

    ImageView uh_img_notification;
    TextView uh_txt_notification;
    TextView uh_txt_petition;

    public HistoryFeedViewHolder(@NonNull View itemView) {
        super(itemView);

        uh_img_notification = itemView.findViewById(R.id.uh_img_notification);
        uh_txt_notification = itemView.findViewById(R.id.uh_txt_notification);
        uh_txt_petition = itemView.findViewById(R.id.uh_txt_petition);
    }

    public void onBind(){

    }
}
