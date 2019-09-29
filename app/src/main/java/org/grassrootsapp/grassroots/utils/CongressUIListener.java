package org.grassrootsapp.grassroots.utils;


import org.grassrootsapp.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressUIListener {
    void updateCongressDirectoryUI(CongressResponse congressResponse);

}
