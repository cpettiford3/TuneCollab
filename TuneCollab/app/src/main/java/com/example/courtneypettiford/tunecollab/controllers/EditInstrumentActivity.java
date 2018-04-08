package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.courtneypettiford.tunecollab.R;

/**
 * Created by courtneypettiford on 3/23/18.
 */

public class EditInstrumentActivity extends AppCompatActivity{

    private ListView instrumentListView;
    private Button back, save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_instrument);


        back = findViewById(R.id.);
        save = findViewById(R.id.);

        instrumentListView = findViewById(R.id.instrument_list_view);
        ListAdapter instrumentList = new ListAdapter() {
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditInstrumentActivity.this, EditProfileActivity.class));
            }
        });

    }
}
