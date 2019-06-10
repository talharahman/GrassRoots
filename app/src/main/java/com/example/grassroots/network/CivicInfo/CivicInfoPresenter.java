package com.example.grassroots.network.CivicInfo;

import com.example.grassroots.utils.LocalRepsUIListener;
import com.example.grassroots.model.CivicInfo.CivicInfoModel;

public class CivicInfoPresenter {

    private LocalRepsUIListener localRepsUIListener;

    public CivicInfoPresenter(LocalRepsUIListener localRepsUIListener) {
        this.localRepsUIListener = localRepsUIListener;
    }

    public void networkCall(String civicAPIKey){
        CivicInfoRepository instance = new CivicInfoRepository();
        instance.fetchElectedRepresentatives(civicAPIKey, new CivicInfoListener() {
            @Override
            public void onSuccess(CivicInfoModel civicInfoModel) {
                localRepsUIListener.updateUI(civicInfoModel);
            }

            @Override
            public void onFailure() {
                //insert callback method
            }
        });
    }

    public void networkCall(String civicAPIKey, String zipCode){
        CivicInfoRepository instance = new CivicInfoRepository();
        instance.fetchElectedRepresentatives(zipCode, civicAPIKey, new CivicInfoListener() {
            @Override
            public void onSuccess(CivicInfoModel civicInfoModel) {
                localRepsUIListener.updateUI(civicInfoModel);
            }

            @Override
            public void onFailure() {
                //insert callback method
            }
        });
    }


}
