package com.example.grassroots.activity.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.activity.MainDashboard;
import com.example.grassroots.activity.UserActionActivity;
import com.example.grassroots.model.user.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserSignUpActivity extends AppCompatActivity {
    private EditText enterName, enterEmail, enterPassword;
    private TextView registerNewUserButton;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference appUsersDatabaseReference;
    private String userName, userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up_v2);

        findViewByIds();
        registerNewUserButton = findViewById(R.id.register_user_button);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        registerNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        userName = enterName.getText().toString().trim();
        userEmail = enterEmail.getText().toString().trim();
        userPassword = enterPassword.getText().toString().trim();
        if (userName.length() > 0
                && userEmail.length() > 0
                && userPassword.length() >= 6) {
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                            sendVerificationEmailToTheNewUser();
                        } else {
                            Toast.makeText(getApplicationContext(), "Try Again Please!", Toast.LENGTH_SHORT).show();
                        }

                    });
        } else {
            enterEmail.setError("Enter all input to proceed");
        }
    }

    private void sendVerificationEmailToTheNewUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        appUsersDatabaseReference = firebaseDatabase.getReference(("/appUsers/" + user.getUid()));
        user.sendEmailVerification().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Email Verification is Sent! Check Your Email", Toast.LENGTH_SHORT).show();



//                appUsersDatabaseReference.setValue(new UserModel(
//                        enterName.getText().toString(),
//                        enterEmail.getText().toString()));


                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName.toString()).build();

                firebaseAuth.getCurrentUser().updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("DISPLAYUSERNAME",firebaseAuth.getCurrentUser().getDisplayName());
                    }
                });


                Intent sendToMainFeed = new Intent(getApplicationContext(), MainDashboard.class);
                startActivity(sendToMainFeed);
            } else {
                Toast.makeText(getApplicationContext(), "verification will be sent soon! check soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewByIds() {
        enterName = findViewById(R.id.create_account_name_edittext);
        enterEmail = findViewById(R.id.create_email_edittext);
        enterPassword = findViewById(R.id.create_account_password_edittext);
    }

}
