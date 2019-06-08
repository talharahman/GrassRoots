package com.example.grassroots.fragment.petition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.R;
import com.example.grassroots.activity.MainDashboard;
import com.example.grassroots.utils.PetitionsFeedInterface;

public class PetitionSignAnim extends Fragment {

    private static final int SPLASH_TIME_OUT = 2000;
    private View anim;
    private PetitionsFeedInterface listener;

    public PetitionSignAnim() { }

    public static PetitionSignAnim newInstance() {
        return new PetitionSignAnim();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petition_sign_anim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anim = view.findViewById(R.id.av_check);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                anim.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(requireContext(), MainDashboard.class);
                startActivity(intent);
            }
        }, SPLASH_TIME_OUT);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetitionsFeedInterface) {
            listener = (PetitionsFeedInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
