package com.example.grassroots.fragment.petition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.Petition;

public class DetailsPetitonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_PETITION = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private TextView petitionNameTextView;
    private TextView petitionDescrptionTextView;
    private TextView petitionSupporterTextView;
    private TextView petitionSignature;
    private ImageView petitionImageImageView;
    private ProgressBar petitionProgressBar;
    private Petition mParam1;


    public DetailsPetitonFragment() {}


    public static DetailsPetitonFragment newInstance(Petition petition) {
        DetailsPetitonFragment fragment = new DetailsPetitonFragment();
        Bundle args = new Bundle();
        args.putParcelable("ARG_PARAM_PETITION",petition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM_PETITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_petiton, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        petitionNameTextView=view.findViewById(R.id.petition_name_text_view);
        petitionDescrptionTextView=view.findViewById(R.id.petition_description_text_view);
        petitionImageImageView=view.findViewById(R.id.petition_image_image_view);

    }
}
