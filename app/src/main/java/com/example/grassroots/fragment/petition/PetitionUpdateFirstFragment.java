package com.example.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.PetitionViewModel;
import com.example.grassroots.recyclerview.PetitionViewHolder;
import com.example.grassroots.utils.PetitionsFeedInterface;

import java.util.Objects;

public class PetitionUpdateFirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private EditText petitionDescrptionEditText;
    private EditText petitionHeadlineEditText;
    private TextView saveButton;
    private PetitionViewModel petitionViewModel;
    private PetitionsFeedInterface mListener;

    public PetitionUpdateFirstFragment() {
        // Required empty public constructor
    }

    public static PetitionUpdateFirstFragment newInstance() {
        PetitionUpdateFirstFragment fragment = new PetitionUpdateFirstFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petition_update_first_v2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        petitionViewModel= ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);
        petitionDescrptionEditText=view.findViewById(R.id.petition_description_edit_text);
        petitionHeadlineEditText=view.findViewById(R.id.petition_headline_edit_text);
        saveButton=view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petitionViewModel.getPetitionUpdates().setmPetitionDescription(petitionDescrptionEditText.getText().toString());
                petitionViewModel.getPetitionUpdates().setmPetitionHeadLine(petitionHeadlineEditText.getText().toString());
                mListener.moveToPetitionUpdatesSecondFragment(new PetitionUpdatesSecondFragment());

            }
        });
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
