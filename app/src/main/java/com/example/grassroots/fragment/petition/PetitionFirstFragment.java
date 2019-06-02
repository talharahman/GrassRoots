package com.example.grassroots.fragment.petition;


import android.app.AlertDialog;
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
import com.example.grassroots.utils.NewPetitionFragmentsListener;

import java.util.Objects;

public class PetitionFirstFragment extends Fragment {

    private EditText editTextPetitionName;
    private EditText editTextPetitionSupporter;
    private EditText editTextPetitionSignatures;

    private PetitionViewModel petitionViewModel;
    private NewPetitionFragmentsListener listener;
    private View rootView;

    public PetitionFirstFragment() {}

    public static PetitionFirstFragment newInstance() {
        return new PetitionFirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_petition_first, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setPetitionName();
        setPetitionTarget();

        editTextPetitionSignatures=view.findViewById(R.id.petition_signature_goal);

        petitionViewModel= ViewModelProviders.of
                (Objects.requireNonNull(getActivity()))
                .get(PetitionViewModel.class);

        Button saveAndContinueButton = view.findViewById(R.id.save_button1);
        saveAndContinueButton.setOnClickListener(v -> {
            listener.moveToPetitionSecondPart(new PetitionSecondFragment());
            petitionViewModel.setmPetitionName(editTextPetitionName.getText().toString().trim());
            petitionViewModel.setmPetitionSupporter(editTextPetitionSupporter.getText().toString().trim());
            petitionViewModel.setmPetitionSignatureGoal(Integer.valueOf(editTextPetitionSignatures.getText().toString()));
        });
    }

    private void setPetitionName() {
        editTextPetitionName = rootView.findViewById(R.id.edit_text_petition_name);
        Button titleDialog = rootView.findViewById(R.id.title_dialog_button);
        titleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(PetitionFirstFragment.this.requireContext()).create();
                alertDialog.setTitle("Petition Title");
                alertDialog.setIcon(R.drawable.ic_info);
                alertDialog.setMessage(PetitionFirstFragment.this.getResources().getString(R.string.petition_title_info));
                alertDialog.show();
            }
        });
    }

    private void setPetitionTarget() {
        editTextPetitionSupporter = rootView.findViewById(R.id.edit_text_target);
        Button targetDialog = rootView.findViewById(R.id.target_dialog_button);
        targetDialog.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(requireContext()).create();
            alertDialog.setTitle("Petition Target");
            alertDialog.setIcon(R.drawable.ic_info);
            alertDialog.setMessage(getResources().getString(R.string.petition_target_info));
            alertDialog.show();
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewPetitionFragmentsListener) {
            listener = (NewPetitionFragmentsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
