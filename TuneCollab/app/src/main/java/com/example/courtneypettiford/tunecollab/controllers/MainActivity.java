package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.courtneypettiford.tunecollab.R;

public class MainActivity extends AppCompatActivity {

    Button btnViewProfile, btnConnectSpotify, btnViewRecommendedProfiles, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewProfile = findViewById(R.id.viewProfile);
        btnConnectSpotify = findViewById(R.id.connectSpotify);
        btnViewRecommendedProfiles = findViewById(R.id.viewRecommendProfiles);
        btnLogout = findViewById(R.id.logout);

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
            }
        });

        btnConnectSpotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConnectSpotifyActivity.class));
            }
        });

        btnViewRecommendedProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecommendUsersActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }
        });

    }
}
