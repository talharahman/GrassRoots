package org.grassrootsapp.grassroots.network.PetitionDB;

import java.util.List;

public interface PetitionSignatureListener {

    void petitionSignatureSuccess(int newSignatures, List<String> newSignersList);

    void petitionSignatureFailure(Throwable t);
}
