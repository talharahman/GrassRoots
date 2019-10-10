package org.grassrootsapp.grassroots.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.activity.user.UserAuthorization;
import org.grassrootsapp.grassroots.model.petition.Petition;
import org.grassrootsapp.grassroots.model.user.UserActionViewModel;
import org.grassrootsapp.grassroots.utils.UserPagerAdapter;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActionActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference petitionRef = db.collection("Petitioncol");
    public static String CURRENT_USER_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    UserActionViewModel userActionViewModel;
    CircleImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                userImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void initialize() {
        getFirebaseData();
        userImage = findViewById(R.id.user_image_upload);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_user_view_home);
        floatingActionButton.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), MainDashboard.class);
            startActivity(home);
        });

        ViewPager viewPager = findViewById(R.id.user_activity_container);
        viewPager.setAdapter(new UserPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabs_user);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        TextView userName = findViewById(R.id.user_name);
        userName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        TextView signOutButton = findViewById(R.id.signout_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignOut();
            }
        });

        Map<String, Drawable> userImageTracker = new HashMap<>();
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 1);

//                userImageTracker.put(FirebaseAuth.getInstance().getCurrentUser().getUid(),);
//                userImage.setImageDrawable();
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
        userActionViewModel.setCurrentUserID(CURRENT_USER_ID);
        // User ID checks out at this point

        petitionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Petition> petitions = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Petition petition = documentSnapshot.toObject(Petition.class);
                            if (petition.getSigners().contains(CURRENT_USER_ID)) {
                                petitions.add(petition);
                            }
                        }
                        userActionViewModel.setUserHistoryPetitions(petitions);
                        // User signed petitions checks out at this point
                    }
                });
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
