package com.example.easytripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void logout(View view) {

        FirebaseAuth.getInstance().signOut(); //to logout from that user profile
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}