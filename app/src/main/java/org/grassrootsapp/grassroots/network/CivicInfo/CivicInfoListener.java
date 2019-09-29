package org.grassrootsapp.grassroots.network.CivicInfo;

import org.grassrootsapp.grassroots.model.CivicInfo.CivicInfoModel;

public interface CivicInfoListener {
    void onSuccess(CivicInfoModel civicInfoModel);
    void onFailure();
}
