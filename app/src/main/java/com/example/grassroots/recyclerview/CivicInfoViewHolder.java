package com.example.grassroots.recyclerview;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;

class CivicInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView repName;
    private TextView showText;
    private TextView repPosition;
    private ImageView repImage;

    private ImageView repUrlIcon;
    private ImageView repPhoneIcon;
    private ImageView repEmailIcon;
    private ImageView repFacebookIcon;
    private ImageView repTwitterIcon;

    private CardView repCardview;
    private CardView socialsCardview;

    CivicInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        setRepresentativeReferences(itemView);
    }

    private void setRepresentativeReferences(View itemview) {
        repName = itemView.findViewById(R.id.rep_name);
        showText = itemView.findViewById(R.id.contact_show);
        repPosition = itemView.findViewById(R.id.rep_position);
        repImage = itemView.findViewById(R.id.Rep_image);

        repUrlIcon = itemview.findViewById(R.id.rep_url_icon);
        repPhoneIcon = itemview.findViewById(R.id.rep_phone_icon);
        repEmailIcon = itemview.findViewById(R.id.rep_email_icon);
        repFacebookIcon = itemview.findViewById(R.id.rep_facebook_icon);
        repTwitterIcon = itemview.findViewById(R.id.rep_twitter_icon);

        repCardview = itemView.findViewById(R.id.local_rep_card_view);
        socialsCardview = itemview.findViewById(R.id.local_rep_socials);
    }

    void onBind(ElectedRepresentatives electedRepresentatives, String position) {
        repName.setText(electedRepresentatives.getName());
        repPosition.setText(position);

        Glide.with(itemView.getContext())
                .load(electedRepresentatives.getPhotoUrl())
                .centerCrop()
                .placeholder(R.drawable.image_na)
                .into(repImage);

        if (electedRepresentatives.getParty() != null) {
            if (electedRepresentatives.getParty().startsWith("D")) {
                repCardview.setCardBackgroundColor(Color.rgb(129, 163, 251));
                socialsCardview.setCardBackgroundColor(Color.rgb(129, 163, 251));
            } else if (electedRepresentatives.getParty().startsWith("R")) {
                repCardview.setCardBackgroundColor(Color.rgb(234, 94, 128));
                socialsCardview.setCardBackgroundColor(Color.rgb(234, 94, 128));
            } else {
                repCardview.setCardBackgroundColor(Color.WHITE);
                socialsCardview.setCardBackgroundColor(Color.WHITE);
            }
        }

        urlView(electedRepresentatives);
        phoneView(electedRepresentatives);
        emailView(electedRepresentatives);
        facebookView(electedRepresentatives);
        twitterView(electedRepresentatives);

        boolean expanded = electedRepresentatives.isExpanded();
        if (expanded) {
            socialsCardview.setVisibility(View.VISIBLE);
            showText.setText("hide");
        } else {
            socialsCardview.setVisibility(View.GONE);
            showText.setText("show");
        }
    }

    private void urlView(ElectedRepresentatives representative) {
        repUrlIcon.setImageResource(R.drawable.web);
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
        repEmailIcon.setImageResource(R.drawable.email);
        repEmailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                alertDialog.setTitle(representative.getName());
                alertDialog.setIcon(R.drawable.email);
                if (representative.getEmails() == null) {
                    alertDialog.setMessage("No e-mail available for this representative");
                } else {
                    alertDialog.setMessage(representative.getEmails().get(0));
                }
                alertDialog.show();
            }
        });
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