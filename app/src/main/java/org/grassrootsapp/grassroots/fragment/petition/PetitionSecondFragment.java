package org.grassrootsapp.grassroots.fragment.petition;

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
import android.widget.EditText;
import android.widget.TextView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.petition.PetitionViewModel;
import org.grassrootsapp.grassroots.utils.PetitionFragmentsListener;

public class PetitionSecondFragment extends Fragment {

    private EditText editTextPetitionProblem;
    private PetitionFragmentsListener listener;
    private PetitionViewModel petitionViewModel;

    public PetitionSecondFragment() {}

    public static PetitionSecondFragment newInstance() {
        return new PetitionSecondFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petition_second_v2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView saveAndContinueButton = view.findViewById(R.id.save_button2);
        editTextPetitionProblem = view.findViewById(R.id.problem_edit_text);

        petitionViewModel = ViewModelProviders.of((FragmentActivity) requireContext()).get(PetitionViewModel.class);
        saveAndContinueButton.setOnClickListener(v -> {
            listener.moveToPetitionThirdPart(new PetitionThirdFragment());
            petitionViewModel.setmPetitionDescription(editTextPetitionProblem.getText().toString().trim());
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof org.grassrootsapp.grassroots.utils.PetitionFragmentsListener) {
            listener = (PetitionFragmentsListener) context;
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