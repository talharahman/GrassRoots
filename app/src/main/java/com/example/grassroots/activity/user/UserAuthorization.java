package com.example.grassroots.activity.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grassroots.R;
import com.example.grassroots.activity.MainDashboard;
import com.example.grassroots.activity.UserActionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthorization extends AppCompatActivity {

    Button signUpButton, loginButton;
    EditText emailEditText, passwordEditText;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authorization);
        findViewByIds();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(getApplicationContext());
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Intent sendToMainFeed = new Intent(getApplicationContext(), UserActionActivity.class);
            startActivity(sendToMainFeed);
        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendToSignUp = new Intent(getApplicationContext(), UserSignUpActivity.class);
                startActivity(sendToSignUp);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateAndLogin();
            }
        });

    }

    private void authenticateAndLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid password.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            this.user = firebaseAuth.getCurrentUser();
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                emailVerificationCheckAndPermitLogin();
            } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.failed_login_text), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViewByIds() {
        signUpButton = findViewById(R.id.sign_up_button);
        loginButton = findViewById(R.id.log_in_button);
        emailEditText = findViewById(R.id.login_email_edittext);
        passwordEditText = findViewById(R.id.login_password_edittext);
    }

    private void emailVerificationCheckAndPermitLogin() {
        if (user != null) {
            if (user.isEmailVerified()) {

                Intent sendToMainFeed = new Intent(getApplicationContext(), MainDashboard.class);
                startActivity(sendToMainFeed);
            } else {
                Toast.makeText(getApplicationContext(), "Verify your email", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
            }
        }
    }
}
