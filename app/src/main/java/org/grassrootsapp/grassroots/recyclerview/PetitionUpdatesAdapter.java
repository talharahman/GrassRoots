package org.grassrootsapp.grassroots.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.model.petition.PetitionUpdates;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PetitionUpdatesAdapter extends RecyclerView.Adapter <PetitionUpdatesViewHolder>{

    private List<PetitionUpdates> petitionUpdatesList;

    @NonNull
    @Override
    public PetitionUpdatesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View childViewHolder= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itemview_petition_updates,viewGroup,false);

        return new PetitionUpdatesViewHolder(childViewHolder);    }

    @Override
    public void onBindViewHolder(@NonNull PetitionUpdatesViewHolder petitionUpdatesViewHolder, int i) {
        PetitionUpdates currentPetitionUpdate=petitionUpdatesList.get(i);
        petitionUpdatesViewHolder.onBind(currentPetitionUpdate);

    }

    public List<PetitionUpdates> getPetitionUpdatesList() {
        return petitionUpdatesList;
    }

    public void setPetitionUpdatesList(List<PetitionUpdates> petitionUpdatesList) {
        this.petitionUpdatesList = petitionUpdatesList;
    }

    @Override
    public int getItemCount() {
        return petitionUpdatesList.size();
    }
}
