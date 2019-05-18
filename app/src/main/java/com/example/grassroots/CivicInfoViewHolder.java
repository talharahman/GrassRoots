package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.model.ElectedPositions;
import com.example.grassroots.model.ElectedRepresentatives;

class CivicInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView repName;
    private TextView repParty;
    private TextView repPosition;
    private ImageView repImage;

    public CivicInfoViewHolder(@NonNull View itemView) {
        super(itemView);

        repName = itemView.findViewById(R.id.rep_name);
        repParty = itemView.findViewById(R.id.rep_party);
        repPosition = itemView.findViewById(R.id.rep_position);
        repImage = itemView.findViewById(R.id.Rep_image);
    }

    void onBind(ElectedRepresentatives electedRepresentatives, ElectedPositions electedPosition) {

        repName.setText(electedRepresentatives.getName());
        repParty.setText(electedRepresentatives.getParty());
        repPosition.setText(electedPosition.getPositionName());

        /*Picasso picasso = new Picasso
                .Builder(itemView.getContext())
                .loggingEnabled(true)
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        exception.printStackTrace();
                    }
                }).build();

         picasso.load(electedRepresentatives.getPhotoUrl())
                .fit()
                .centerCrop()
                .into(repImage);*/

        Glide.with(itemView.getContext())
                .load(electedRepresentatives.getPhotoUrl())
                .centerCrop()
                .placeholder(R.drawable.image_na)
                .into(repImage);

    }
}
