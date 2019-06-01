package com.example.grassroots.recyclerview;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.DetailsPetitonFragment;
import com.example.grassroots.utils.PetitionFragmentsListener;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.utils.PetitionsFeedInterface;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionViewHolder extends RecyclerView.ViewHolder {

    private TextView petitionNameTextView;
    private TextView pettitonDescrptionTextView;
    private TextView petitionSignatureTextView;
    private ImageView petitionImageImageView;
    private ProgressBar petitionProgressBarSignatures;

    public PetitionViewHolder(@NonNull View itemView) {
        super(itemView);
        petitionNameTextView=itemView.findViewById(R.id.petition_name_text_view);
        petitionImageImageView=itemView.findViewById(R.id.petition_image_image_view);
        pettitonDescrptionTextView=itemView.findViewById(R.id.petition_description_text_view);
        petitionProgressBarSignatures=itemView.findViewById(R.id.progress_bar_signatures);
        petitionSignatureTextView=itemView.findViewById(R.id.signatures_text_view);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onBind(Petition currentPetition, PetitionsFeedInterface listener) {
        petitionNameTextView.setText(currentPetition.getmPetitionName());
        pettitonDescrptionTextView.setText(currentPetition.getmPetitionDescription());
        petitionSignatureTextView.setText(currentPetition.getmPetitionSignature() + " signed of " + currentPetition.getmPetitionSignatureGoal()+" goal");
        petitionProgressBarSignatures.setMax(currentPetition.getmPetitionSignatureGoal());
        petitionProgressBarSignatures.setProgress(currentPetition.getmPetitionSignature(),true);

        Glide.with(getApplicationContext())
                .load(currentPetition.getmPetitionImageURL())
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.petition_placeholder)
                .into(petitionImageImageView);

        itemView.setOnClickListener(v -> listener.moveToDetailsPetition(DetailsPetitonFragment.newInstance(currentPetition)));
    }
}
