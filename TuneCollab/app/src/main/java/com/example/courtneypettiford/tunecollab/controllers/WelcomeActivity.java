package com.example.courtneypettiford.tunecollab.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.courtneypettiford.tunecollab.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnRegister = (Button) findViewById(R.id.registerButton);
        btnLogin = (Button) findViewById(R.id.loginButton);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }


    private void register() {
        startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
    }

    private void login() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}
