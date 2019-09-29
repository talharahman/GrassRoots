package org.grassrootsapp.grassroots.network.CivicInfo;

import org.grassrootsapp.grassroots.utils.LocalRepsUIListener;
import org.grassrootsapp.grassroots.model.CivicInfo.CivicInfoModel;

public class CivicInfoPresenter {

    private LocalRepsUIListener localRepsUIListener;

    public CivicInfoPresenter(LocalRepsUIListener localRepsUIListener) {
        this.localRepsUIListener = localRepsUIListener;
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
