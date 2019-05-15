package com.example.grassroots.network;

import android.content.Context;

import com.example.grassroots.fragment.RepresentativeDirectoryFragment;
import com.example.grassroots.model.CivicInfoModel;

public class CivicInfoPresenter {

    private RepresentativeDirectoryFragment representativeDirectoryFragment;

    public CivicInfoPresenter(RepresentativeDirectoryFragment representativeDirectoryFragment) {
        this.representativeDirectoryFragment = representativeDirectoryFragment;
    }

    public void networkCall(Context context){
        CivicInfoRetrofit instance = CivicInfoRetrofit.getInstance();
        instance.setListener(new CivicInfoListener() {
            @Override
            public void onConnected(CivicInfoModel civicInfoModel) {
                representativeDirectoryFragment.updateUI(civicInfoModel);
            }
        });
        instance.onSuccess("11101", context);
    }
}
