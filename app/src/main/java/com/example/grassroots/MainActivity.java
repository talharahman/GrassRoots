package com.example.grassroots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.grassroots.fragment.RepresentativeDirectoryFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "findme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendToRepresentativeDirectoryFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, new RepresentativeDirectoryFragment())
                .commit();
    }
}
