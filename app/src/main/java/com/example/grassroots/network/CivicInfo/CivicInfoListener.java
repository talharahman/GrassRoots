package com.example.grassroots.network.CivicInfo;

import com.example.grassroots.model.CivicInfo.CivicInfoModel;

public interface CivicInfoListener {
    void onSuccess(CivicInfoModel civicInfoModel);
    void onFailure();
}
