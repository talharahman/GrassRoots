package com.example.grassroots.network.CivicInfo;

import com.example.grassroots.fragment.LocalRepsFragmentListener;
import com.example.grassroots.model.CivicInfo.CivicInfoModel;

public class CivicInfoPresenter {

    private LocalRepsFragmentListener localRepsFragmentListener;

    public CivicInfoPresenter( LocalRepsFragmentListener localRepsFragmentListener) {
        this.localRepsFragmentListener = localRepsFragmentListener;
    }

    public void networkCall(String civicAPIKey, String zipCode){
        CivicInfoRepository instance = new CivicInfoRepository();
        instance.fetchElectedRepresentatives(zipCode, civicAPIKey, new CivicInfoListener() {
            @Override
            public void onSuccess(CivicInfoModel civicInfoModel) {
                localRepsFragmentListener.updateUI(civicInfoModel);
            }

            @Override
            public void onFailure() {
                //insert callback method
            }
        });
    }


}
