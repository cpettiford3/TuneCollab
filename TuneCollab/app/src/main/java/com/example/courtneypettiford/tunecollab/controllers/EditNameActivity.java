package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by courtneypettiford on 3/23/18.
 */

public class EditNameActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private EditText inputFirstName, inputLastName, inputStageName;
    private String firstName, lastName, stageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);


        Button back = findViewById(R.id.editNameBackButton);
        Button submit = findViewById(R.id.submitNameEditButton);

        inputFirstName = findViewById(R.id.firstName);
        inputLastName = findViewById(R.id.lastName);
        inputStageName = findViewById(R.id.stageName);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditNameActivity.this, ProfileActivity.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                firstName = inputFirstName.getText().toString();
                lastName = inputLastName.getText().toString();
                stageName = inputStageName.getText().toString();


            }
        });

    }

    private void updateUserName(String firstName, String lastName, String stageName) {
        String userId = mDatabase.push().getKey();
        User user = new User(firstName, lastName, stageName);
        mDatabase.child(userId).setValue(user);
    }
}
