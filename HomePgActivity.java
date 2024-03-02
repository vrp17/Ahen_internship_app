package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePgActivity extends AppCompatActivity {


    Button signout,booknow,insurenow,license;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pg);

        signout = findViewById(R.id.signout);

        signout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePgActivity.this, MainActivity.class));
            }
        });

        booknow = findViewById(R.id.book1);

        booknow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePgActivity.this, BookingTabActivity.class));
            }
        });


        license = findViewById(R.id.book2);

        license.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePgActivity.this, LicensePgActivity.class));
            }
        });



        insurenow = findViewById(R.id.book3);

        insurenow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePgActivity.this, InsuranceTabActivity.class));
            }
        });



    }
}