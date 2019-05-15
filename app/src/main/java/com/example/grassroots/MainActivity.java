package com.example.grassroots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.grassroots.fragment.RepresentativeDirectoryFragment;
import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.network.CivicInfoListener;
import com.example.grassroots.network.CivicInfoRetrofit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "findme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendToRepresentativeDirectoryFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, new RepresentativeDirectoryFragment())
                .commit();
    }
}
