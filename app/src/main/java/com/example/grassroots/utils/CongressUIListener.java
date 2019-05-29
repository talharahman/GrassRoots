package com.example.grassroots.utils;


import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressUIListener {
    void updateCongressDirectoryUI(CongressResponse congressResponse);

}
