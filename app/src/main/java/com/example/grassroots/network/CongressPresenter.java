package com.example.grassroots.network;

import com.example.grassroots.fragment.CongressFragmentListener;
import com.example.grassroots.model.CongressResponse;

public class CongressPresenter {

    private CongressFragmentListener congressFragmentListener;

    public CongressPresenter(CongressFragmentListener congressFragmentListener){
        this.congressFragmentListener = congressFragmentListener;
    }

    public void networkCall(String congressApiKey){
        CongressRepository instance = new CongressRepository();
        instance.fetchHouseDirectory(congressApiKey, "116", "House", new CongressListener() {
            @Override
            public void successfulCall(CongressResponse congressResponse) {
                congressFragmentListener.updateCongressDirectoryUI(congressResponse);
            }

            @Override
            public void failedCall() {
                //tbd
            }
        });

        instance.fetchSenateDirectory(congressApiKey, "116", "Senate", new CongressListener() {
            @Override
            public void successfulCall(CongressResponse congressResponse) {
                congressFragmentListener.updateCongressDirectoryUI(congressResponse);
            }

            @Override
            public void failedCall() {
                //tbd
            }
        });
    }
}

