package com.example.grassroots.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.grassroots.R;
import com.example.grassroots.utils.PetitionsFeedInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link splashscreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class splashscreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Thread splashTread;
    private ImageView imageView;
    private ImageView imageViewtext;


    private PetitionsFeedInterface mListener;



    public splashscreenFragment() {
        // Required empty public constructor
    }


    public static splashscreenFragment newInstance(String param1, String param2) {
        splashscreenFragment fragment = new splashscreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         imageView = view.findViewById(R.id.splash);
         imageViewtext = view.findViewById(R.id.splash_text);
        StartAnimations();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splashscreen, container, false);
    }


    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(requireContext(), R.anim.alpha);
        anim.reset();
//        ConstraintLayout constraintLayout=findViewById(R.id.lin_lay);
//        constraintLayout.clearAnimation();
//        constraintLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(requireContext(), R.anim.translate);
        anim.reset();
        imageView.clearAnimation();
        imageView.startAnimation(anim);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);

                    }catch (InterruptedException e) {

                       e.printStackTrace();
                } finally {
                    mListener.openMainfeed();
                   // finish():
                }

            }
        };
        splashTread.start();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionsFeedInterface) {
            mListener = (PetitionsFeedInterface) context;
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
}
