package com.example.grassroots.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;
import com.example.grassroots.model.CivicInfo.SocialChannels;

import java.util.List;

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

    private CardView cardView;

    CivicInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        setRepresentativeReferences(itemView);
    }

    private void setRepresentativeReferences(View itemview) {
        repName = itemView.findViewById(R.id.rep_name);
        repParty = itemView.findViewById(R.id.rep_party);
        repPosition = itemView.findViewById(R.id.rep_position);
        repImage = itemView.findViewById(R.id.Rep_image);

        repUrlIcon = itemview.findViewById(R.id.rep_url_icon);
        repPhoneIcon = itemview.findViewById(R.id.rep_phone_icon);
        repEmailIcon = itemview.findViewById(R.id.rep_email_icon);
        repFacebookIcon = itemview.findViewById(R.id.rep_facebook_icon);
        repTwitterIcon = itemview.findViewById(R.id.rep_twitter_icon);

        cardView = itemview.findViewById(R.id.local_rep_socials);
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

        urlView(electedRepresentatives);
        phoneView(electedRepresentatives);
        emailView(electedRepresentatives);
        facebookView(electedRepresentatives);
        twitterView(electedRepresentatives);

        boolean expanded = electedRepresentatives.isExpanded();
        cardView.setVisibility(expanded ? View.VISIBLE : View.GONE);
    }

    private void urlView(ElectedRepresentatives representative) {
        repUrlIcon.setImageResource(R.drawable.url);
        if (representative.getUrls() == null) {
            repUrlIcon.setVisibility(View.GONE);
        } else {
            repUrlIcon.setOnClickListener(v -> {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(representative.getUrls().get(0)));
                v.getContext().startActivity(uriIntent);
            });
        }
    }

    private void phoneView(ElectedRepresentatives representative) {
        repPhoneIcon.setImageResource(R.drawable.phone);
        if (representative.getPhones() == null) {
            repPhoneIcon.setVisibility(View.GONE);
        } else {
            repPhoneIcon.setOnClickListener(v -> {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + representative.getPhones().get(0)));
                v.getContext().startActivity(phoneIntent);
            });
        }
    }

    private void emailView(ElectedRepresentatives representative) {
        // TODO fix email button not showing up issue
        repEmailIcon.setImageResource(R.drawable.email);
        if (representative.getEmails() == null) {
            repEmailIcon.setVisibility(View.GONE);
        } else {
            repEmailIcon.setOnClickListener(v -> {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: " + representative.getEmails().get(0)));
                v.getContext().startActivity(emailIntent);
            });
        }
    }

    private void facebookView(ElectedRepresentatives representative) {
        repFacebookIcon.setImageResource(R.drawable.facebook);
        if (representative.getName().equals("Donald J. Trump") || representative.getName().equals("Mike Pence")) {
            repFacebookIcon.setOnClickListener(v -> {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/" + representative.getChannels().get(1).getId()));
                v.getContext().startActivity(facebookIntent);
            });
        } else {
            repFacebookIcon.setOnClickListener(v -> {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/" + representative.getChannels().get(0).getId()));
                v.getContext().startActivity(facebookIntent);
            });
        }
    }

    private void twitterView(ElectedRepresentatives representative) {
        repTwitterIcon.setImageResource(R.drawable.twitter);
        if (representative.getName().equals("Donald J. Trump") || representative.getName().equals("Mike Pence")) {
            repTwitterIcon.setOnClickListener(v -> {
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + representative.getChannels().get(2).getId()));
                v.getContext().startActivity(twitterIntent);
            });
        } else {
            repTwitterIcon.setOnClickListener(v -> {
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + representative.getChannels().get(1).getId()));
                v.getContext().startActivity(twitterIntent);
            });
        }
    }
}