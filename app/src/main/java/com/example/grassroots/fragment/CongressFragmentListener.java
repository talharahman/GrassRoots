package com.example.grassroots.fragment;


import com.example.grassroots.model.ProPublica.Members.CongressResponse;

public interface CongressFragmentListener {
    void updateCongressDirectoryUI(CongressResponse congressResponse);

}
