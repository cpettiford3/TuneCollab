package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.courtneypettiford.tunecollab.R;

/**
 * Created by courtneypettiford on 3/23/18.
 */

public class EditNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);


        Button back = findViewById(R.id.editNameBackButton);
        Button submit = findViewById(R.id.submitNameEditButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditNameActivity.this, EditProfileActivity.class));
            }
        });

    }
}
