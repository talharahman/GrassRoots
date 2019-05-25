package com.example.grassroots.fragment.petition;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetitionSharedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetitionSharedFragment extends Fragment {

    private static final int REQUEST_VIDEO_CODE = 100;
    private PetitionFragmentsListener mListener;
    private PetitionViewModel petitionViewModel;
    private Button sharedVideoButton,sharedPhotoButton,shareLinkButton,shareInstagramButton,shareTwiterButton;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private StorageReference mStorageRef;


    public PetitionSharedFragment() {
        // Required empty public constructor
    }


    public static PetitionSharedFragment newInstance(String param1, String param2) {
        PetitionSharedFragment fragment = new PetitionSharedFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petition_shared, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPhotoButton=view.findViewById(R.id.share_photo_button);
        sharedVideoButton=view.findViewById(R.id.share_video_button);
        shareLinkButton=view.findViewById(R.id.share_link_button);
        shareInstagramButton=view.findViewById(R.id.share_Instagram_button);
        shareTwiterButton=view.findViewById(R.id.share_twitter_button);
        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ben", "onActivityResult: on Activity");
        if(requestCode==RESULT_OK)
        {
            if (requestCode==REQUEST_VIDEO_CODE)
            {
                Uri selectedVido=data.getData();
                ShareVideo video=new ShareVideo.Builder()
                        .setLocalUrl(selectedVido)
                        .build();

                ShareVideoContent videoContent=new ShareVideoContent.Builder()
                        .setContentTitle("This is Useful video")
                        .setContentDescription("Funny video from BEn DEv Download fo youtube")
                        .setVideo(video)
                        .build();

                if(ShareDialog.canShow(ShareVideoContent.class))
                    shareDialog.show(videoContent);
            }
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        callbackManager= CallbackManager.Factory.create();
        shareDialog=new ShareDialog((Activity) requireContext());

        shareLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(requireContext(),"shared successful!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(requireContext(),"shared cancel!",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(requireContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                });
                ShareLinkContent linkContent=new ShareLinkContent.Builder()
                        .setQuote("This is UseFul Link \n my descrption\n ")
                        .setContentUrl(Uri.parse("https://media-waterdeep.cursecdn.com/avatars/thumbnails/0/13/1000/1000/636238871029832086.jpeg"))
                        // .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=b5W5US7sZNQ"))
                        //.setImageUrl()
                        .build();

                if (ShareDialog.canShow(ShareLinkContent.class)){

                    shareDialog.show(linkContent);
                }
            }
        });

        shareTwiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =requireContext().getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                if (shareIntent != null) {
                    shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, petitionViewModel.getmPetitionImage());
                    shareIntent.putExtra(Intent.EXTRA_TEXT,petitionViewModel.getmPetitionName()+". \n "+petitionViewModel.getmPetitionDescription()+"\n join us \n https://play.google.com/store/apps/details?id=com.nexon.durango.global");
                    shareIntent.putExtra(Intent.EXTRA_TITLE, "I am ben");
                    shareIntent.setPackage("com.twitter.android");
                    startActivity(shareIntent);
                }
                else
                {
                    // bring user to the market to download the app.
                    // or let them choose an app?
                    shareIntent = new Intent(Intent.ACTION_VIEW);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.setData(Uri.parse("market://details?id="+"com.twitter.android"));
                    startActivity(shareIntent);
                }



//
//                Uri uri = Uri
//                        .parse("android.resource://com.code2care.example.sharetextandimagetwitter/drawable/mona");
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, "mohamed ");
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_STREAM, petitionViewModel.getmPetitionImage());
//                intent.setType("image/jpeg");
//                intent.setPackage("com.twitter.android");
//                startActivity(intent);

            }
        });

        shareInstagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent =requireContext().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                if (shareIntent != null) {
                    shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, petitionViewModel.getmPetitionImage());
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"mohamed");
                    shareIntent.putExtra(Intent.EXTRA_TITLE, "I am ben");
                    shareIntent.setPackage("com.instagram.android");
                    startActivity(shareIntent);
                }
                else
                {
                    // bring user to the market to download the app.
                    // or let them choose an app?
                    shareIntent = new Intent(Intent.ACTION_VIEW);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.setData(Uri.parse("market://details?id="+"com.instagram.android"));
                    startActivity(shareIntent);
                }




//                Bitmap image=BitmapFactory.decodeResource(getResources(),R.drawable.der);
//                Intent intent =requireContext().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
//                if (intent != null)
//                {
//                    Intent shareIntent = new Intent();
//                    shareIntent.setAction(Intent.ACTION_SEND);
//                    shareIntent.setPackage("com.instagram.android");
//                    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(requireContext().getContentResolver(),image, "I am Happy", "Share happy !")));
//                    shareIntent.setType("image/jpeg");
//                    startActivity(shareIntent);
//                }
//                else
//                {
//                    // bring user to the market to download the app.
//                    // or let them choose an app?
//                    intent = new Intent(Intent.ACTION_VIEW);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setData(Uri.parse("market://details?id="+"com.instagram.android"));
//                    startActivity(intent);
//                }

            }
        });




        sharedPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////
                Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.ic_grassroots_color);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .setCaption("fhfhf")

                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .setPageId("hkjhkjhkjhkjhkjhkjh")
                        .setPlaceId("bkjhkjhkjhkjhkj")
                        .setRef("dddddd")
                        //.setQuote("Connect on a global scale.")

                        .build();

//                if (!requireActivity().isFinishing()) {
//                    Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();
                shareDialog.show(content);
                //                } else {
//                    return;
//                }

                ////

//                // we will fetch photo from link and convert to bitmap
//                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//                    @Override
//                    public void onSuccess(Sharer.Result result) {
//                        Toast.makeText(requireContext(),"shared successful!",Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(requireContext(),"shared cancel!",Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                        Toast.makeText(requireContext(),error.toString(),Toast.LENGTH_LONG).show();
//
//                    }
//                });
//                //Picasso picasso = new Picasso.Builder(getContext()).loggingEnabled(true).build();
//                Picasso.get().load("https://www.miniscollector.com/sites/default/files/WZK71815-Dragon.jpg")
//                        .into(target);
//

            }
        });





        sharedVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select video"),REQUEST_VIDEO_CODE);

                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(requireContext(),"shared successful!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(requireContext(),"shared cancel!",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(requireContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                });

            }
        });



    }

}
