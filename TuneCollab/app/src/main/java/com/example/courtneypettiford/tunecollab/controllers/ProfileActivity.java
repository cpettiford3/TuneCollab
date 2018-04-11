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
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thomashaertel.widget.MultiSpinner;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class ProfileActivity extends AppCompatActivity {

    private MultiSpinner instrumentSpinner;
    private MultiSpinner genreSpinner;
    private MultiSpinner userRoleSpinner;
    private MultiSpinner lookingForRoleSpinner;
    private ArrayAdapter<Instrument> instrumentAdapter;
    private ArrayAdapter<Genre> genreAdapter;
    private ArrayAdapter<Role> userRoleAdapter;
    private ArrayAdapter<Role> lookingForRoleAdapter;

    private Button back, updateInfo;

    private DatabaseReference mDatabase;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        updateInfo = findViewById(R.id.updateProfileInfo);

        instrumentAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Instrument.values());

        genreAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Genre.values());

        userRoleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Role.values());

        lookingForRoleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Role.values());



        instrumentSpinner = findViewById(R.id.instrumentSpinner);
        instrumentSpinner.setAdapter(instrumentAdapter, false, onSelectedListener);
        boolean[] selectedInstrumentItems = new boolean[instrumentAdapter.getCount()];
        selectedInstrumentItems[1] = true; // select second item
        instrumentSpinner.setSelected(selectedInstrumentItems);

        genreSpinner = findViewById(R.id.genreSpinner);
        genreSpinner.setAdapter(genreAdapter, false, onSelectedListener);
        boolean[] selectedGenreItems = new boolean[genreAdapter.getCount()];
        selectedGenreItems[1] = true;
        genreSpinner.setSelected(selectedGenreItems);

        userRoleSpinner = findViewById(R.id.roleSpinner);
        userRoleSpinner.setAdapter(userRoleAdapter, false, onSelectedListener);
        boolean[] selectedUserRoleItems = new boolean[userRoleAdapter.getCount()];
        selectedUserRoleItems[1] = true;
        userRoleSpinner.setSelected(selectedUserRoleItems);

        lookingForRoleSpinner = findViewById(R.id.lookingForRoleSpinner);
        lookingForRoleSpinner.setAdapter(lookingForRoleAdapter, false, onSelectedListener);
        boolean[] selectedLookingForRoleItems = new boolean[lookingForRoleAdapter.getCount()];
        selectedLookingForRoleItems[1] = true;
        lookingForRoleSpinner.setSelected(selectedLookingForRoleItems);

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userId != null) {
                    userId = mDatabase.push().getKey();
                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
        }
    };
}
