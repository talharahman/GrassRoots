package com.example.grassroots.model.petition;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Petition implements Parcelable {

    private String petitionName;
    private String petitionTarget;
    private String petitionTargetContact;
    private String petitionDescription;
    private Uri petitionImageUri;
    private String petitionImageURL;
    private int petitionSignature;
    private int petitionSignatureGoal;

    public Petition() {}

    public Petition(String petitionName,
                    String petitionTarget,
                    String petitionTargetContact,
                    String petitionDescription,
                    Uri petitionImageUri,
                    String petitionImageURL,
                    int petitionSignature,
                    int petitionSignatureGoal) {
        this.petitionName = petitionName;
        this.petitionTarget = petitionTarget;
        this.petitionTargetContact = petitionTargetContact;
        this.petitionDescription = petitionDescription;
        this.petitionImageUri = petitionImageUri;
        this.petitionImageURL = petitionImageURL;
        this.petitionSignature = petitionSignature;
        this.petitionSignatureGoal = petitionSignatureGoal;
    }

    protected Petition(Parcel in) {
        petitionName = in.readString();
        petitionTarget = in.readString();
        petitionTargetContact = in.readString();
        petitionDescription = in.readString();
        petitionImageUri = in.readParcelable(Uri.class.getClassLoader());
        petitionImageURL = in.readString();
        petitionSignature = in.readInt();
        petitionSignatureGoal = in.readInt();
    }

    public static final Creator<Petition> CREATOR = new Creator<Petition>() {
        @Override
        public Petition createFromParcel(Parcel in) {
            return new Petition(in);
        }

        @Override
        public Petition[] newArray(int size) {
            return new Petition[size];
        }
    };

    public String getPetitionName() {
        return petitionName;
    }

    public String getPetitionTarget() {
        return petitionTarget;
    }

    public String getPetitionTargetContact() {
        return petitionTargetContact;
    }

    public String getPetitionDescription() {
        return petitionDescription;
    }

    public Uri getPetitionImageUri() {
        return petitionImageUri;
    }

    public String getPetitionImageURL() {
        return petitionImageURL;
    }

    public int getPetitionSignature() {
        return petitionSignature;
    }

    public int getPetitionSignatureGoal() {
        return petitionSignatureGoal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(petitionName);
        dest.writeString(petitionTarget);
        dest.writeString(petitionDescription);
        dest.writeParcelable(petitionImageUri, flags);
        dest.writeString(petitionImageURL);
        dest.writeInt(petitionSignature);
        dest.writeInt(petitionSignatureGoal);
    }
}
