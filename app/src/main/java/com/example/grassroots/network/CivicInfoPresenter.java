package com.example.grassroots.network;

import android.util.Log;

import com.example.grassroots.CivicInfoAdapter;
import com.example.grassroots.MainActivity;
import com.example.grassroots.fragment.RepDirectoryFragmentListener;
import com.example.grassroots.fragment.RepresentativeDirectoryFragment;
import com.example.grassroots.model.CivicInfoModel;

public class CivicInfoPresenter {

    private RepresentativeDirectoryFragment representativeDirectoryFragment;
    private RepDirectoryFragmentListener repDirectoryFragmentListener;

    public CivicInfoPresenter( RepDirectoryFragmentListener repDirectoryFragmentListener) {
        this.repDirectoryFragmentListener = repDirectoryFragmentListener;
    }

    public void networkCall(String civicAPIKey, String zipCode){
        CivicInfoRepository instance = new CivicInfoRepository();
        instance.fetchElectedRepresentatives(zipCode, civicAPIKey, new CivicInfoListener() {
            @Override
            public void onSuccess(CivicInfoModel civicInfoModel) {
                repDirectoryFragmentListener.updateUI(civicInfoModel);
            }

            @Override
            public void onFailure() {
                //insert callback method
            }
        });
    }


}
