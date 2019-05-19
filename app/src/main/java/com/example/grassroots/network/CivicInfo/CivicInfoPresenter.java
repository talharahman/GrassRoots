package com.example.grassroots.network.CivicInfo;

import com.example.grassroots.fragment.RepDirectoryFragmentListener;
import com.example.grassroots.fragment.RepresentativeDirectoryFragment;
import com.example.grassroots.model.CivicInfo.CivicInfoModel;

public class CivicInfoPresenter {

    private RepDirectoryFragmentListener repDirectoryFragmentListener;

    public CivicInfoPresenter( RepDirectoryFragmentListener repDirectoryFragmentListener) {
        this.repDirectoryFragmentListener = repDirectoryFragmentListener;
    }

    public void networkCall(String civicAPIKey){
        CivicInfoRepository instance = new CivicInfoRepository();
        instance.fetchElectedRepresentatives("11101", civicAPIKey, new CivicInfoListener() {
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
