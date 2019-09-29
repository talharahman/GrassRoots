package org.grassrootsapp.grassroots.fragment.petition;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.petition.Petition;
import org.grassrootsapp.grassroots.utils.PetitionsFeedInterface;

public class PetitionSignAnim extends Fragment {

    private static final int SPLASH_TIME_OUT = 2000;
    private View anim;
    private PetitionsFeedInterface listener;
    public static final String PETITION_ARG = "signedPetition";
    private Petition petitionArg = new Petition();

    public PetitionSignAnim() { }

    public static PetitionSignAnim newInstance(Petition petition) {
        PetitionSignAnim fragment = new PetitionSignAnim();
        Bundle args = new Bundle();
        args.putSerializable(PETITION_ARG, petition);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petitionArg = (Petition) getArguments().getSerializable(PETITION_ARG);
        }
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
        TextView petitionSigned = view.findViewById(R.id.signed_text);
        petitionSigned.setText("You signed\n" + petitionArg.getmPetitionName());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                anim.setVisibility(View.INVISIBLE);
                listener.moveToDetailsPetition(DetailsPetitionFragment.newInstance(petitionArg));
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
