package org.grassrootsapp.grassroots.recyclerview;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.CivicInfo.ElectedRepresentatives;
import org.grassrootsapp.grassroots.network.PetitionDB.SendPetitionToRepCallBack;

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

    private ImageView send;
    private ImageView arrow;
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

        send = itemview.findViewById(R.id.send_petition);
        arrow = itemview.findViewById(R.id.arrow);
        socialsCardview = itemview.findViewById(R.id.local_rep_socials);
    }

    void onBind(ElectedRepresentatives electedRepresentatives, String position, SendPetitionToRepCallBack listener) {
        repName.setText(electedRepresentatives.getName());
        repPosition.setText(position);

        urlView(electedRepresentatives);
        phoneView(electedRepresentatives);
        emailView(electedRepresentatives);
        facebookView(electedRepresentatives);
        twitterView(electedRepresentatives);

        setImages(electedRepresentatives);
        sendView(listener, electedRepresentatives);

        boolean expanded = electedRepresentatives.isExpanded();
        if (expanded) {
            socialsCardview.setVisibility(View.VISIBLE);
            showText.setText("hide contact");
            arrow.setImageResource(R.drawable.ic_arrow_up);
        } else {
            socialsCardview.setVisibility(View.GONE);
            showText.setText("show contact");
            arrow.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    private void setImages(ElectedRepresentatives electedRepresentatives) {
        if (electedRepresentatives.getName().equals("Andrew M. Cuomo")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.andrew_cuomo)
                    .centerCrop()
                    .into(repImage);

        } else if (electedRepresentatives.getName().equals("Thomas P. DiNapoli")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.thomas_dinapoli)
                    .centerCrop()
                    .into(repImage);
        } else if (electedRepresentatives.getName().equals("Letitia A. James")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.letitia_james)
                    .centerCrop()
                    .into(repImage);
        } else if (electedRepresentatives.getName().equals("Jumaane Williams")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.jumaane_williams)
                    .centerCrop()
                    .into(repImage);
        } else if (electedRepresentatives.getName().equals("Kathy Hochul")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.kathy_hochul)
                    .centerCrop()
                    .into(repImage);
        } else if (electedRepresentatives.getName().equals("Lee M. Zeldin")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.lee_zeldin)
                    .centerCrop()
                    .into(repImage);
        } else {
            Glide.with(itemView.getContext())
                    .load(electedRepresentatives.getPhotoUrl())
                    .centerCrop()
                    .placeholder(R.drawable.silhouette)
                    .into(repImage);
        }
    }

    private void sendView(SendPetitionToRepCallBack listener, ElectedRepresentatives representative) {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sendMyPetitionToRep(representative);
            }

        });
    }

    private void urlView(ElectedRepresentatives representative) {
        repUrlIcon.setImageResource(R.drawable.web);
        repUrlIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                if (representative.getUrls() == null) {
                    alertDialog.setTitle(representative.getName());
                    alertDialog.setIcon(R.drawable.web);
                    alertDialog.setMessage("No web url available for this representative");
                } else {
                    Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(representative.getUrls().get(0)));
                    v.getContext().startActivity(uriIntent);
                }
            }
        });
    }

    private void phoneView(ElectedRepresentatives representative) {
        repPhoneIcon.setImageResource(R.drawable.phone);
        repPhoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                if (representative.getPhones() == null) {
                    alertDialog.setTitle(representative.getName());
                    alertDialog.setIcon(R.drawable.phone);
                    alertDialog.setMessage("No phone number available for this representative");
                } else {
                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: " + representative.getPhones().get(0)));
                    v.getContext().startActivity(phoneIntent);
                }
            }
        });
    }

    private void emailView(ElectedRepresentatives representative) {
        repEmailIcon.setImageResource(R.drawable.email);
        repEmailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                if (representative.getEmails() == null) {
                    alertDialog.setTitle(representative.getName());
                    alertDialog.setIcon(R.drawable.email);
                    alertDialog.setMessage("No e-mail available for this representative");
                } else {
                    Intent email = new Intent(Intent.ACTION_SENDTO,
                            Uri.parse("mailto: " + representative.getEmails().get(0)));
                    v.getContext().startActivity(email);
                }
                alertDialog.show();
            }
        });
    }

    private void facebookView(ElectedRepresentatives representative) {
        repFacebookIcon.setImageResource(R.drawable.facebook);
        repFacebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                if (representative.getChannels() == null) {
                    alertDialog.setTitle(representative.getName());
                    alertDialog.setIcon(R.drawable.facebook);
                    alertDialog.setMessage("No Facebook page available for this representative");
                } else {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://facebook.com/" + representative.getChannels().get(0).getId()));
                    v.getContext().startActivity(facebookIntent);
                }
            }
        });
    }

    private void twitterView(ElectedRepresentatives representative) {
        repTwitterIcon.setImageResource(R.drawable.twitter);
        repTwitterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
                // TODO fix bug
                if (representative.getChannels() == null) {
                    alertDialog.setTitle(representative.getName());
                    alertDialog.setIcon(R.drawable.twitter);
                    alertDialog.setMessage("No Twitter page available for this representative");
                } else {
                    Intent twitterIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/" + representative.getChannels().get(1).getId()));
                    v.getContext().startActivity(twitterIntent);
                }
            }
        });
    }

}