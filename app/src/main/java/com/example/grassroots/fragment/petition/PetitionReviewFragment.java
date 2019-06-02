package com.example.grassroots.fragment.petition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.utils.PetitionFragmentsListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PetitionReviewFragment extends Fragment {

    private PetitionViewModel petitionViewModel;
    private TextView petitionNameTextView;
    private TextView petitionSupporterTextView;
    private TextView petitionDescriptionTextView;
    private ImageView petitionImageView;
    private Button publishButton, shareButton;
    private StorageTask mUploadTask;
    private ProgressBar mProgressBar;
    private PetitionFragmentsListener mListener;
    public static Context contextOfApplication;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    public PetitionReviewFragment() { }

    public static PetitionReviewFragment newInstance() {
        return new PetitionReviewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petition_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        petitionNameTextView = view.findViewById(R.id.text_view_petition_name);
        petitionSupporterTextView = view.findViewById(R.id.text_view_petition_supporter);
        petitionDescriptionTextView = view.findViewById(R.id.text_view_petition_description);
        petitionImageView = view.findViewById(R.id.image_view_petition);
        publishButton = view.findViewById(R.id.publish_button);
        mProgressBar = view.findViewById(R.id.progress_bar);
        shareButton = view.findViewById(R.id.share_button);

        petitionViewModel = ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);
        petitionNameTextView.setText(petitionViewModel.getmPetitionName());
        petitionSupporterTextView.setText(petitionViewModel.getmPetitionSupporter());
        petitionDescriptionTextView.setText(petitionViewModel.getmPetitionDescription());


        Glide.with((FragmentActivity) requireContext())
                .load(petitionViewModel.getmPetitionImage())
                .centerCrop()
                .into(petitionImageView);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToSharePetition(new PetitionSharedFragment());
            }
        });
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionFragmentsListener) {
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


    private void uploadFile() {
        printKeyHash();
        if (petitionViewModel.getmPetitionImage() != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(petitionViewModel.getmPetitionImage()));

            mUploadTask = fileReference.putFile(petitionViewModel.getmPetitionImage())
                    .addOnSuccessListener(taskSnapshot -> {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        }, 500);

                        fileReference.getDownloadUrl().addOnSuccessListener(uri -> Log.d("testtoday", "onSuccess: uri= " + uri.toString()));
                        petitionViewModel.setmPetitionSignature(1);

                        fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            Petition petition = new Petition(petitionViewModel.getmPetitionName(),
                                    petitionViewModel.getmPetitionSupporter(),
                                    petitionViewModel.getmPetitionDescription(),
                                    uri.toString(),
                                    petitionViewModel.getmPetitionSignatureGoal(),
                                    petitionViewModel.getmPetitionSignature()

                            );

                            String petitionId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(petitionId).setValue(petition);

                        });

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    private String getFileExtension(Uri uri) {
        Context applicationContext = requireActivity().getApplicationContext();

        applicationContext.getContentResolver();
        ContentResolver cR = applicationContext.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo("com.example.petition", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}