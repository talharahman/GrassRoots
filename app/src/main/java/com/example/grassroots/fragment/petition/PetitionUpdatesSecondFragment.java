package com.example.grassroots.fragment.petition;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.activity.MainDashboard;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionUpdates;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PetitionUpdatesSecondFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private String mParam1;
    private String mParam2;
    private Uri mImageUri;
    private ImageView petitionUpdatesImageView;
    private Button uploadImageButton;
    private Button saveReviewButton;
    private PetitionViewModel petitionViewModel;
    private StorageTask mUploadTask;
    private ProgressBar mProgressBar;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    public PetitionUpdatesSecondFragment() { }

    public static PetitionUpdatesSecondFragment newInstance() {
        return new PetitionUpdatesSecondFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petition_updates_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);
        petitionViewModel.setmPetitionImage(mImageUri);


        uploadImageButton=view.findViewById(R.id.button_choose_image);
        petitionUpdatesImageView=view.findViewById(R.id.image_view);
        saveReviewButton=view.findViewById(R.id.save_button2);
        mProgressBar=view.findViewById(R.id.progressBar);


        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        saveReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(),"hi "+petitionViewModel.getPetitionKey(),Toast.LENGTH_LONG).show();
                uploadfiledcumentway();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            petitionViewModel.getPetitionUpdates().setmPetitionImageUri(mImageUri);
            Glide.with(requireContext())
                    .load(mImageUri)
                    .into(petitionUpdatesImageView);
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        Context applicationContext = requireActivity().getApplicationContext();

        applicationContext.getContentResolver();
        ContentResolver cR = applicationContext.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadFile() {
        if (petitionViewModel.getmPetitionImage() != null) {

            StorageReference fileReference = mStorageRef.child("updates").child(System.currentTimeMillis()
                    + "." + getFileExtension(petitionViewModel.getmPetitionImage()));

            mUploadTask = fileReference.putFile(petitionViewModel.getmPetitionImage())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("testtoday", "onSuccess: uri= "+ uri.toString());
                                }
                            });

                            petitionViewModel.setmPetitionSignature(1);


                            fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
//                                Petition petition = new Petition(petitionViewModel.getmPetitionName(),
//                                        petitionViewModel.getmPetitionSupporter(),
//                                        petitionViewModel.getmPetitionDescription(),
//                                        uri.toString(),
//                                        petitionViewModel.getmPetitionSignatureGoal(),
//                                        petitionViewModel.getmPetitionSignature()
//
//                                );
//
//                                String petitionId = mDatabaseRef.push().getKey();
//                                mDatabaseRef.child(petitionId).setValue(petition);
//                                mDatabaseRef.child(petitionViewModel.getPetitionKey());


                            });

                        }
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

    private void  uploadfiledcumentway(){

        if (petitionViewModel.getPetitionUpdates().getmPetitionImageUri() != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(petitionViewModel.getPetitionUpdates().getmPetitionImageUri()));

            mUploadTask = fileReference.putFile(petitionViewModel.getPetitionUpdates().getmPetitionImageUri())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("test today", "onSuccess: uri= "+ uri.toString());
                                }
                            });

                            DocumentReference documentReference = db.collection("Petitioncol").document(petitionViewModel.getPetitionKey());

                            fileReference.getDownloadUrl().addOnSuccessListener(uri -> {

                                PetitionUpdates petitionUpdates = new PetitionUpdates (
                                        petitionViewModel.getPetitionUpdates().getmPetitionHeadLine(),
                                        petitionViewModel.getPetitionUpdates().getmPetitionDescription(),
                                        uri.toString()
                                );

                                documentReference.update("mPetitionUpdatesList", FieldValue.arrayUnion(petitionUpdates))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(requireContext(), "hi is done", Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();
                                                Log.d("TEst", e.toString());
                                            }
                                        });
                            });

                        }
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

//
//            List<PetitionUpdates> updatesList = new ArrayList<>();
//            PetitionUpdates updates2 = new PetitionUpdates();
//            updates2.setmPetitionDescription("ben nhhggggggggg");
//            updatesList.add(updates2);
//
//            Log.d("tst", "uploadfiledcumentway: " + petitionViewModel.getPetitionKey());
//
//            DocumentReference documentReference = db.collection("Petitioncol").document(petitionViewModel.getPetitionKey());
//
//            Log.d("test", "uploadfiledcumentway: " + documentReference);
//
//            //documentReference.
//            // mDatabaseRef.child("1").setValue(updates2);
//
//            documentReference.update("mPetitionUpdatesList", FieldValue.arrayUnion(updates2))
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(requireContext(), "hi is done", Toast.LENGTH_LONG).show();
//                        }
//                    });
        }


//        StorageReference fileReference = mStorageRef.child("updates").child(System.currentTimeMillis()
//                + "." + getFileExtension(petitionViewModel.getmPetitionImage()));
//
//        mUploadTask = fileReference.putFile(petitionViewModel.getmPetitionImage())
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    }
//                })
//
//
//        StorageReference imageRef = mStorageRef.getDownloadUrl(selectedItem.getImageUrl());
//        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                mDatabaseRef.child(petitionViewModel.getPetitionKey()).setValue()
//                Toast.makeText(ImagesActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
//            }
//       });
}
