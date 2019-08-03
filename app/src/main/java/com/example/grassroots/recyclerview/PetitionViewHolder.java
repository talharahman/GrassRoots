package com.example.grassroots.recyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.fragment.petition.DetailsPetitionFragment;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.utils.PetitionsFeedInterface;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionViewHolder extends RecyclerView.ViewHolder {

    private TextView petitionNameTextView;
    private TextView pettitonDescrptionTextView;
    private TextView petitionSignatureTextView;
    private ImageView petitionImageImageView;
    private ProgressBar petitionProgressBarSignatures;
    private  ObjectAnimator progressBarAnimator;

    public PetitionViewHolder(@NonNull View itemView) {
        super(itemView);
        petitionNameTextView=itemView.findViewById(R.id.petition_name_text_view);
        petitionImageImageView=itemView.findViewById(R.id.petition_image_image_view);
        pettitonDescrptionTextView=itemView.findViewById(R.id.petition_description_text_view);
        petitionSignatureTextView=itemView.findViewById(R.id.signatures_text_view);

        petitionProgressBarSignatures=itemView.findViewById(R.id.progress_bar_signatures);

        //init();
        //progressBarAnimator.setDuration(70);
//        progressBarAnimator.setupEndValues();
//        progressBarAnimator.setValues([);
//        progressBarAnimator.set
//        progressBarAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Toast.makeText(getApplicationContext(),"Op complete",Toast.LENGTH_LONG).show();
//                petitionProgressBarSignatures.setVisibility(View.GONE);
//                progressBarAnimator=ObjectAnimator.ofInt(petitionProgressBarSignatures,"progress",0,5000);
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                progressBarAnimator=ObjectAnimator.ofInt(petitionProgressBarSignatures,"progress",10,5);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                progressBarAnimator=ObjectAnimator.ofInt(petitionProgressBarSignatures,"progress",0,5);
//
//            }
//        });


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onBind(final Petition currentPetition,PetitionsFeedInterface mListener) {
        petitionNameTextView.setText(currentPetition.getmPetitionName());
     //   pettitonDescrptionTextView.setText(currentPetition.getmPetitionDescription());

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberAsString = numberFormat.format(currentPetition.getmPetitionSignature());
        String numberAsString2 = numberFormat.format(currentPetition.getmPetitionSignatureGoal());

        petitionSignatureTextView.setText(numberAsString +" / "+ numberAsString2+" goal");
        petitionProgressBarSignatures.setMax(currentPetition.getmPetitionSignatureGoal());


        petitionProgressBarSignatures.setProgress(currentPetition.getmPetitionSignature());

        Glide.with(getApplicationContext())
                .load(currentPetition.getmPetitionImageURL())
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.petition_placeholder)
                .into(petitionImageImageView);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToDetailsPetition(DetailsPetitionFragment.newInstance(currentPetition));
            }
        });
    }
}
