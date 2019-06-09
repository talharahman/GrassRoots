package com.example.grassroots.network.PetitionDB;

import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionUpdates;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference petitionReference;
    private Throwable t = new Throwable();

    /*public List<Petition> getAllPetitions() {

    }

    public Petition getPetition(String petitionKey) {

    }*/

    public void getPetitionUpdates(String petitionKey, PetitionUpdateOnCompleteListener petitionUpdateListener) {
        List<PetitionUpdates> petitionUpdatesList = new ArrayList<>();

        petitionReference = db.collection("Petitioncol").document(petitionKey);
        petitionReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Petition petition = documentSnapshot.toObject(Petition.class);
                        petition.setPetitionKey(documentSnapshot.getId());

                        for(PetitionUpdates p: petition.getmPetitionUpdatesList()) {
                            petitionUpdatesList.add(p);
                        }

                        petitionUpdateListener.onPetitionUpdatesOnSucces(petitionUpdatesList);
                        petitionUpdateListener.onPetitionUpdatesOnfialer(t);
                    }
                });
    }


    public void updatePetitionSignatures(String userID, Petition petition, PetitionSignatureListener petitionSignatureListener) {
        List<String> signersList = new ArrayList<>();

        petitionReference = db.collection("Petitioncol").document(petition.getPetitionKey());
        signersList.add(userID);
        petition.setmPetitionSignature(petition.getmPetitionSignature() + 1);

        petitionReference.update("mPetitionSignature", petition.getmPetitionSignature(),
                "signers", signersList)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        petitionSignatureListener.petitionSignatureSuccess(petition.getmPetitionSignature(), signersList);

                        petitionSignatureListener.petitionSignatureFailure(t);
                    }
                });
    }

    /*public Petition createPetition(Petition petition) {

    }*/


}
