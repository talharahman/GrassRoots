package com.example.grassroots.fragment.petition;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.grassroots.R;
import com.example.grassroots.model.petition.PetitionViewModel;

import java.util.Objects;


public class PetitionFirstFragment extends Fragment {

    private Button saveAndContinueButton;
    private EditText editTextPetitionName;
    private EditText editTextPetitionSupporter;
    private PetitionViewModel petitionViewModel;
    private PetitionFragmentsListener mListener;



    public PetitionFirstFragment() {
        // Required empty public constructor
    }

    public static PetitionFirstFragment newInstance() {
        PetitionFirstFragment fragment = new PetitionFirstFragment();
        Bundle args = new Bundle();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petition_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextPetitionName=view.findViewById(R.id.edit_text_petition_name);
        editTextPetitionSupporter=view.findViewById(R.id.edit_text_supporter_name);
        saveAndContinueButton=view.findViewById(R.id.save_button1);
        petitionViewModel= ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(PetitionViewModel.class);
        saveAndContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToPetitionSecondPart(new PetitionSecondFragment());
                petitionViewModel.setmPetitionName(editTextPetitionName.getText().toString().trim());
                petitionViewModel.setmPetitionSupporter(editTextPetitionSupporter.getText().toString().trim());
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
