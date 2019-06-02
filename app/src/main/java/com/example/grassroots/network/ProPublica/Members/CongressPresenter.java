package com.example.grassroots.network.ProPublica.Members;

import com.example.grassroots.utils.CongressUIListener;
import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public class CongressPresenter {

    private CongressUIListener congressUIListener;

    public CongressPresenter(CongressUIListener congressUIListener){
        this.congressUIListener = congressUIListener;
    }

    public void networkCall(String congressApiKey){
        CongressRepository instance = new CongressRepository();
        instance.fetchHouseDirectory(congressApiKey, "116", "House", new CongressListener() {
            @Override
            public void successfulCall(CongressResponse congressResponse) {
                congressUIListener.updateCongressDirectoryUI(congressResponse);
            }

            @Override
            public void failedCall() {
                //tbd
            }
        });

        instance.fetchSenateDirectory(congressApiKey, "116", "Senate", new CongressListener() {
            @Override
            public void successfulCall(CongressResponse congressResponse) {
                congressUIListener.updateCongressDirectoryUI(congressResponse);
            }

            @Override
            public void failedCall() {
                //tbd
            }
        });
    }
}

