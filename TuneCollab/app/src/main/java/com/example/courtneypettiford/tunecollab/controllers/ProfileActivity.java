package com.example.courtneypettiford.tunecollab.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends Activity {

    TextView userName, instruments, genres, roles, influences;
    Button back, accept, reject;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.userNameText);
        genres = findViewById(R.id.genreProfile);
        instruments = findViewById(R.id.instrumentProfile);
        roles = findViewById(R.id.roleProfile);
        influences = findViewById(R.id.influencesProfile);

        back = findViewById(R.id.backFromProfile);
        accept = findViewById(R.id.acceptProfile);
        reject = findViewById(R.id.rejectProfile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (mAuth.getCurrentUser() != null) {
                            for (DataSnapshot ds: dataSnapshot.getChildren()) {
                                User user = ds.getValue(User.class);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

            }
        });



        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser() != null) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        userName.setText(user.getFirstName() + " " +  user.getLastName());
                        genres.setText(user.getGenres().toString());
                        instruments.setText(user.getInstruments().toString());
                        roles.setText(user.getRoles().toString());
                        influences.setText(user.getInfluences().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
