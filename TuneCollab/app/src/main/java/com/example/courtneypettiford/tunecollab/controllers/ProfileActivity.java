package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.courtneypettiford.tunecollab.R;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class ProfileActivity extends AppCompatActivity {

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btnSubmit = findViewById(R.id.submitProfile);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
