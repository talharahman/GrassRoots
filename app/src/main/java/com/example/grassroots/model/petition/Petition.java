package com.example.grassroots.model.petition;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Petition implements Parcelable {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImageUri;
    private String mPetitionImageURL;
    private int mPetitionSignature;
    private int mPetitionSignatureGoal;

    public Petition() { }

    public Petition( String petitionName, String petitionSupporter, String petitionDescription, String petitionImage,int petitionSignatureGoal,int petitionSignature) {
        mPetitionName =petitionName;
        mPetitionSupporter =petitionSupporter;
        mPetitionDescription =petitionDescription;
        mPetitionImageURL=petitionImage;
        mPetitionSignatureGoal=petitionSignatureGoal;
        mPetitionSignature=petitionSignature;
    }

    protected Petition(Parcel in) {
        mPetitionName = in.readString();
        mPetitionSupporter = in.readString();
        mPetitionDescription = in.readString();
        mPetitionImageUri = in.readParcelable(Uri.class.getClassLoader());
        mPetitionImageURL = in.readString();
        mPetitionSignature = in.readInt();
        mPetitionSignatureGoal = in.readInt();
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

    public int getmPetitionSignatureGoal() {
        return mPetitionSignatureGoal;
    }

    public void setmPetitionSignatureGoal(int mPetitionSignatureGoal) {
        this.mPetitionSignatureGoal = mPetitionSignatureGoal;
    }

    public void setmPetitionSignature(int mPetitionSignature ){
        this.mPetitionSignature=mPetitionSignature;
    }
    public int getmPetitionSignature(){
        return mPetitionSignature;
    }
    public String getmPetitionName() {
        return mPetitionName;
    }

    public void setmPetitionName(String mPetitionName) {
        this.mPetitionName = mPetitionName;
    }

    public String getmPetitionSupporter() {
        return mPetitionSupporter;
    }

    public void setmPetitionSupporter(String mPetitionSupporter) {
        this.mPetitionSupporter = mPetitionSupporter;
    }

    public String getmPetitionDescription() {
        return mPetitionDescription;
    }

    public void setmPetitionDescription(String mPetitionDescription) {
        this.mPetitionDescription = mPetitionDescription;
    }

    public Uri getmPetitionImageUri() {
        return mPetitionImageUri;
    }

    public void setmPetitionImageUri(Uri mPetitionImageUri) {
        this.mPetitionImageUri = mPetitionImageUri;
    }

    public String getmPetitionImageURL() {
        return mPetitionImageURL;
    }

    public void setmPetitionImageURL(String mPetitionImageURL) {
        this.mPetitionImageURL = mPetitionImageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPetitionName);
        dest.writeString(mPetitionSupporter);
        dest.writeString(mPetitionDescription);
        dest.writeParcelable(mPetitionImageUri, flags);
        dest.writeString(mPetitionImageURL);
        dest.writeInt(mPetitionSignature);
        dest.writeInt(mPetitionSignatureGoal);
    }
}

