package com.example.grassroots.network;

import com.example.grassroots.model.CivicInfoModel;

public interface CivicInfoListener {
    void onSuccess(CivicInfoModel civicInfoModel);
    void onFailure();
}
