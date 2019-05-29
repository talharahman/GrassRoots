package com.example.grassroots.fragment;


import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressUIListener {
    void updateCongressDirectoryUI(CongressResponse congressResponse);

}
