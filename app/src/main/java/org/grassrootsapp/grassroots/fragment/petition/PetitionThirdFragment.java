package org.grassrootsapp.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.petition.PetitionViewModel;
import org.grassrootsapp.grassroots.utils.PetitionFragmentsListener;

import static android.app.Activity.RESULT_OK;


public class PetitionThirdFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;

    private PetitionViewModel petitionViewModel;
    private PetitionFragmentsListener mListener;

    private Uri mImageUri;
    private ImageView mImageView;

    public PetitionThirdFragment() {}

    public static PetitionThirdFragment newInstance() {
       return new PetitionThirdFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petition_third_v2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonChooseImage = view.findViewById(R.id.button_choose_image);
        mImageView = view.findViewById(R.id.image_view);
        TextView saveReviewButton = view.findViewById(R.id.save_button3);

        petitionViewModel = ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);


        saveReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petitionViewModel.setmPetitionImage(mImageUri);
                mListener.moveToPetitionPreview(new PetitionReviewFragment());
            }
        });


        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            // Picasso.get().load(mImageUri).into(mImageView);
            Glide.with(requireContext())
                    .load(mImageUri)
                    .into(mImageView);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof org.grassrootsapp.grassroots.utils.PetitionFragmentsListener) {
            mListener = (PetitionFragmentsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

}