package com.example.grassroots.model.petition;

import java.io.Serializable;

public class PetitionSignatures implements Serializable {
    String signatureID;
    String timeSigned;

    public PetitionSignatures(String signatureID, String timeSigned) {
        this.signatureID = signatureID;
        this.timeSigned = timeSigned;
    }

    public void setSignatureID(String signatureID) {
        this.signatureID = signatureID;
    }

    public void setTimeSigned(String timeSigned) {
        this.timeSigned = timeSigned;
    }

    public String getSignatureID() {
        return signatureID;
    }

    public String getTimeSigned() {
        return timeSigned;
    }

}
