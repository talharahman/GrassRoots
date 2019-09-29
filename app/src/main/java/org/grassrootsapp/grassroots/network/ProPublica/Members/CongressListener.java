package org.grassrootsapp.grassroots.network.ProPublica.Members;


import org.grassrootsapp.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressListener {
    void successfulCall(CongressResponse congressResponse);

    void failedCall();

}
