package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.Genre;
import com.example.courtneypettiford.tunecollab.model.Instrument;
import com.example.courtneypettiford.tunecollab.model.Role;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class EditProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        Button btnEditName = findViewById(R.id.editName);
        Button btnEditInstrument = findViewById(R.id.instrument);
        Button btnEditSelfRole = findViewById(R.id.userRole);
        Button btnEditOtherUserRole = findViewById(R.id.otherUserRole);
        Button back = findViewById(R.id.backFromEditProfile);


        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this,
                        EditNameActivity.class));
            }
        });

        btnEditInstrument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });

    }
}
