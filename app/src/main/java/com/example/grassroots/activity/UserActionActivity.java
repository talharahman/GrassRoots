package com.example.grassroots.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.activity.user.UserAuthorization;
import com.example.grassroots.model.petition.Petition;
import com.example.grassroots.model.petition.PetitionSignatures;
import com.example.grassroots.model.user.UserActionViewModel;
import com.example.grassroots.utils.UserPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserActionActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference petitionRef = db.collection("Petitioncol");
    public static String CURRENT_USER_ID;
    UserActionViewModel userActionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        initialize();
    }

    private void initialize() {
        getFirebaseData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_user_view_home);
        floatingActionButton.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), MainDashboard.class);
            startActivity(home);
        });

        ViewPager viewPager = findViewById(R.id.user_activity_container);
        viewPager.setAdapter(new UserPagerAdapter(this, getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabs_user);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //TODO: will display user name here
        TextView userName = findViewById(R.id.user_name);
        userName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        Button signOutButton = findViewById(R.id.signout_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignOut();
            }
        });


    }

    private void userSignOut(){
        FirebaseAuth.getInstance().signOut();
        Intent signOut = new Intent(getApplicationContext(), UserAuthorization.class);
        startActivity(signOut);
    }

    private void getFirebaseData() {

        userActionViewModel = ViewModelProviders.of(this).get(UserActionViewModel.class);

//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        currentUser.getUid();
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth
//                .signInWithEmailAndPassword("talharahman@pursuit.org", "password2")
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            userActionViewModel.setCurrentUserID(firebaseAuth.getUid());
//                            Log.d("MYCURRENTID", "onComplete: " + firebaseAuth.getUid());
//                        } else {
//                            Toast.makeText(UserActionActivity.this.getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        UserActionViewModel userActionViewModel = ViewModelProviders.of(this).get(UserActionViewModel.class);

        petitionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Petition> petitions = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Petition petition = documentSnapshot.toObject(Petition.class);
                            petitions.add(petition);
                        }
                        userActionViewModel.setPetitions(petitions);
                    }
                });

      //  firebaseAuth.signOut();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bot_add_action:
                Intent action = new Intent(this, PetitionHostActivity.class);
                startActivity(action);
                return true;
            case R.id.bot_nav_contact:
                Intent contact = new Intent(this, LocalRepsActivity.class);
                startActivity(contact);
                return true;
            case R.id.bot_nav_search:
                Intent search = new Intent(this, CongressActivity.class);
                startActivity(search);
                return true;
        }
        return true;
    }
}
