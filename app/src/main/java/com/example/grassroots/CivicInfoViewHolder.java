package com.example.grassroots;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;

import org.json.JSONException;
import org.json.JSONObject;

class CivicInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView repName;
    private TextView repParty;
    private TextView repPosition;
    private ImageView repImage;

    private ImageView repUrlIcon;
    private ImageView repPhoneIcon;
    private ImageView repEmailIcon;
    private ImageView repFacebookIcon;
    private ImageView repTwitterIcon;

    private TextView repUrlInfo;
    private TextView repPhoneInfo;
    private TextView repEmailInfo;
    private TextView repFacebookInfo;
    private TextView repTwitterInfo;

    private JSONObject myRep;
    private ConstraintLayout childLayout;

    CivicInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        setRepresentativeReferences(itemView);

    }

    void setRepresentativeReferences(View itemview) {
        repName = itemView.findViewById(R.id.rep_name);
        repParty = itemView.findViewById(R.id.rep_party);
        repPosition = itemView.findViewById(R.id.rep_position);
        repImage = itemView.findViewById(R.id.Rep_image);

        repUrlIcon = itemview.findViewById(R.id.rep_url_icon);
        repPhoneIcon = itemview.findViewById(R.id.rep_phone_icon);
        repEmailIcon = itemview.findViewById(R.id.rep_email_icon);
        repFacebookIcon = itemview.findViewById(R.id.rep_facebook_icon);
        repTwitterIcon = itemview.findViewById(R.id.rep_twitter_icon);

        repUrlInfo = itemview.findViewById(R.id.rep_url_info);
        repPhoneInfo = itemview.findViewById(R.id.rep_phone_info);
        repEmailInfo = itemview.findViewById(R.id.rep_email_info);
        repFacebookInfo = itemview.findViewById(R.id.rep_facebook_info);
        repTwitterInfo = itemview.findViewById(R.id.rep_twitter_info);

        childLayout = itemView.findViewById(R.id.rep_contact_details);
    }

    void onBind(ElectedRepresentatives electedRepresentatives, String position) {
        repName.setText(electedRepresentatives.getName());
        repParty.setText(electedRepresentatives.getParty());
        repPosition.setText(position);

        Glide.with(itemView.getContext())
                .load(electedRepresentatives.getPhotoUrl())
                .centerCrop()
                .placeholder(R.drawable.image_na)
                .into(repImage);

        repUrlIcon.setImageResource(R.drawable.url);
        repPhoneIcon.setImageResource(R.drawable.phone);
        repEmailIcon.setImageResource(R.drawable.email);
        repFacebookIcon.setImageResource(R.drawable.facebook);
        repTwitterIcon.setImageResource(R.drawable.twitter);

        String NA = "Not Available";
        repUrlInfo.setText(NA);
        repPhoneInfo.setText(NA);
        repEmailInfo.setText(NA);
        repFacebookInfo.setText(NA);
        repTwitterInfo.setText(NA);

        boolean expanded = electedRepresentatives.isExpanded();
        childLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
    }
}