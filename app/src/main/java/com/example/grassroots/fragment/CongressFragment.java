package com.example.grassroots.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grassroots.CongressAdapter;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.Members.CongressResponse;
import com.example.grassroots.network.ProPublica.Members.CongressListener;
import com.example.grassroots.network.ProPublica.Members.CongressRetrofit;
import com.example.grassroots.network.ProPublica.Members.CongressService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongressFragment extends Fragment implements SearchView.OnQueryTextListener {

    public static final String TAG = "HERE";

    private CongressAdapter congressAdapter;

    private List<CongressMember> congressMembersList = new ArrayList<>();

    private CongressListener congressListener;

    public CongressFragment() {}

    public static CongressFragment newInstance(){
        return new CongressFragment();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if(context instanceof CongressListener){
//            congressListener = (CongressListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + "must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_congress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchView searchView = view.findViewById(R.id.sv_congress);
        RecyclerView recyclerView = view.findViewById(R.id.rv_congress);

        congressAdapter = new CongressAdapter(congressMembersList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        recyclerView.setAdapter(congressAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        searchView.setOnQueryTextListener(this);

        CongressRetrofit.getInstance()
                .create(CongressService.class)
                .getCongress(requireContext().getString(R.string.ProPublica_Congress_API_Key), "116", "Senate")
                .enqueue(new Callback<CongressResponse>() {
                    @Override
                    public void onResponse(Call<CongressResponse> call, Response<CongressResponse> response) {
                        congressMembersList.addAll(response.body().getResults().get(0).getMembers());
                        Collections.sort(congressMembersList);
                        congressAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onResponse: " + response.body().getStatus());
                        Log.d(TAG, "onResponse: " + response.body().getResults().get(0).getMembers().get(0).getFirst_name());
                        Log.d(TAG, "onResponse: " + response.raw());

                    }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure " + t.toString());
                    }
                });

        CongressRetrofit.getInstance()
                .create(CongressService.class)
                .getCongress(requireContext().getString(R.string.ProPublica_Congress_API_Key), "116", "House")
                .enqueue(new Callback<CongressResponse>() {
                    @Override
                    public void onResponse(Call<CongressResponse> call, Response<CongressResponse> response) {
                        congressMembersList.addAll(response.body().getResults().get(0).getMembers());
                        Collections.sort(congressMembersList);
                        congressAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onResponse: " + response.body().getStatus());
                        Log.d(TAG, "onResponse: " + response.body().getResults().get(0).getMembers().get(0).getFirst_name());
                        Log.d(TAG, "onResponse: " + response.raw());

                    }

                    @Override
                    public void onFailure(Call<CongressResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure " + t.toString());
                    }
                });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        final List<CongressMember> newMemberList = new ArrayList<>();
        for (CongressMember congressMember : congressMembersList) {
            if (congressMember.getFirst_name().toLowerCase().startsWith(s.toLowerCase())) {
                newMemberList.add(congressMember);
            }
        }
        congressAdapter.setData(newMemberList);
        return false;
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        congressListener = null;
//    }
}
