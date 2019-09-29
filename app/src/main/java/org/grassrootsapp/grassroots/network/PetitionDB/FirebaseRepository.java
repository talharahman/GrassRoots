package org.grassrootsapp.grassroots.network.PetitionDB;

import org.grassrootsapp.grassroots.model.petition.Petition;
import org.grassrootsapp.grassroots.model.petition.PetitionUpdates;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference petitionReference;
    private Throwable t = new Throwable();

    public void getAllPetitions(MyPetitionHistoryInterface myPetitionHistoryInterface) {
        CollectionReference petitionRef = db.collection("Petitioncol");
        petitionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Petition> petitions = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Petition petition = documentSnapshot.toObject(Petition.class);
                            petitions.add(petition);
                        }
                        myPetitionHistoryInterface.myHistoryOfPetitions(petitions);
                    }
                });
    }

   /* public Petition getPetition(String petitionKey) {

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

                        petitionUpdateListener.onPetitionUpdatesOnSuccess(petitionUpdatesList);
                        petitionUpdateListener.onPetitionUpdatesOnFailure(t);
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
