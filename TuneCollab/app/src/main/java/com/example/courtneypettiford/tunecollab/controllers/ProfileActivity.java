package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.Genre;
import com.example.courtneypettiford.tunecollab.model.Instrument;
import com.example.courtneypettiford.tunecollab.model.Role;
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by courtneypettiford on 3/10/18.
 */

public class ProfileActivity extends AppCompatActivity {

    private String firstName, lastName, stageName, influences;
    private EditText inputFirstName, inputLastName, inputStageName, inputInfluences;

    private MultiSpinner instrumentSpinner;
    private MultiSpinner genreSpinner;
    private MultiSpinner userRoleSpinner;
    private MultiSpinner lookingForRoleSpinner;
    private ArrayAdapter<Instrument> instrumentAdapter;
    private ArrayAdapter<Genre> genreAdapter;
    private ArrayAdapter<Role> userRoleAdapter;
    private ArrayAdapter<Role> lookingForRoleAdapter;

    private List<String> influencesArr;
    private List<Instrument> instruments;
    private List<Role> roles;
    private List<Role> lookingForRole;
    private List<Genre> genres;

    private Button back, updateInfo;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inputFirstName = findViewById(R.id.firstName);
        inputLastName = findViewById(R.id.lastName);
        inputStageName = findViewById(R.id.stageName);
        inputInfluences = findViewById(R.id.influences);

        firstName = inputFirstName.getText().toString();
        lastName = inputLastName.getText().toString();
        stageName = inputStageName.getText().toString();
        influences = inputInfluences.getText().toString();

        back = findViewById(R.id.backFromEditProfile);
        updateInfo = findViewById(R.id.updateProfileInfo);

        instruments = new ArrayList<>();
        roles = new ArrayList<>();
        lookingForRole = new ArrayList<>();
        genres = new ArrayList<>();
        influencesArr = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();


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
        instrumentSpinner.setAdapter(instrumentAdapter, false, onInstrumentSelectedListener);
        boolean[] selectedInstrumentItems = new boolean[instrumentAdapter.getCount()];
        selectedInstrumentItems[1] = true; // select second item
        instrumentSpinner.setSelected(selectedInstrumentItems);

        genreSpinner = findViewById(R.id.genresSpinner);
        genreSpinner.setAdapter(genreAdapter, false, onGenreSelectedListener);
        boolean[] selectedGenreItems = new boolean[genreAdapter.getCount()];
        selectedGenreItems[1] = true;
        genreSpinner.setSelected(selectedGenreItems);

        userRoleSpinner = findViewById(R.id.userRoleSpinner);
        userRoleSpinner.setAdapter(userRoleAdapter, false, onUserRoleSelectedListener);
        boolean[] selectedUserRoleItems = new boolean[userRoleAdapter.getCount()];
        selectedUserRoleItems[1] = true;
        userRoleSpinner.setSelected(selectedUserRoleItems);

        lookingForRoleSpinner = findViewById(R.id.lookingForRoleSpinner);
        lookingForRoleSpinner.setAdapter(lookingForRoleAdapter, false, onLookingForRoleSelectedListener);
        boolean[] selectedLookingForRoleItems = new boolean[lookingForRoleAdapter.getCount()];
        selectedLookingForRoleItems[1] = true;
        lookingForRoleSpinner.setSelected(selectedLookingForRoleItems);

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = inputFirstName.getText().toString();
                lastName = inputLastName.getText().toString();
                stageName = inputStageName.getText().toString();
                influences = inputInfluences.getText().toString();

                influencesArr = Arrays.asList(influences.split(","));

                addUser(firstName, lastName, stageName, influencesArr,genres, roles,
                        lookingForRole, instruments);


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

    //instrument----multispinner getSelected items method
    private MultiSpinner.MultiSpinnerListener onInstrumentSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < instrumentAdapter.getCount(); i++) {
                if (selected[i]) {
                    instruments.add(instrumentAdapter.getItem(i)); //get the list of selected items
                    if (sb.length() == 0) {
                        sb.append(instrumentAdapter.getItem(i));
                    } else {
                        sb.append(",").append(instrumentAdapter.getItem(i));
                    }
                }
            }
        }
    };

    //genre---multispinner getSelected items method
    private MultiSpinner.MultiSpinnerListener onGenreSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < genreAdapter.getCount(); i++) {
                if (selected[i]) {
                    genres.add(genreAdapter.getItem(i)); //get the list of selected items
                    if (sb.length() == 0) {
                        sb.append(genreAdapter.getItem(i));
                    } else {
                        sb.append(",").append(genreAdapter.getItem(i));
                    }
                }
            }
        }
    };

    //userRole -- multispinner getSelected items method
    private MultiSpinner.MultiSpinnerListener onUserRoleSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < userRoleAdapter.getCount(); i++) {
                if (selected[i]) {
                    roles.add(userRoleAdapter.getItem(i)); //get the list of selected items
                    if (sb.length() == 0) {
                        sb.append(userRoleAdapter.getItem(i));
                    } else {
                        sb.append(",").append(userRoleAdapter.getItem(i));
                    }
                }
            }
        }
    };

    private MultiSpinner.MultiSpinnerListener onLookingForRoleSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lookingForRoleAdapter.getCount(); i++) {
                if (selected[i]) {
                    lookingForRole.add(lookingForRoleAdapter.getItem(i)); //get the list of selected items
                    if (sb.length() == 0) {
                        sb.append(lookingForRoleAdapter.getItem(i));
                    } else {
                        sb.append(",").append(lookingForRoleAdapter.getItem(i));
                    }
                }
            }
        }
    };

    private void addUser(String firstName, String lastName, String stageName,
                         List<String> influencesArr, List<Genre> genres, List<Role> roles,
                         List<Role> lookingForRole, List<Instrument> instruments) {

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(stageName)) {
            User user = new User(firstName, lastName, stageName, influencesArr,genres, roles,
                    lookingForRole, instruments);
            mDatabase.child(userId).setValue(user);
        }
    }

}
