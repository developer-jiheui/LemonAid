package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_15 extends AppCompatActivity {
    DatabaseHelper dbh;
    double payBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_15);

        dbh = new DatabaseHelper(this);
        final TextView totalBalance = findViewById(R.id.txtNewBalance);

        Button btnPatientProfile = findViewById(R.id.btnPayLater);
        Button btnPayNow = findViewById(R.id.btnPayNow);

        //I have to calculate and populate the final balance here

        btnPatientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_15.this,Activity_9.class);
                startActivity(i);

            }
        });
        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_15.this,Activity_14.class);
                startActivity(i);
            }
        });


    }
}
