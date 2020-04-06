package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_18 extends AppCompatActivity {
    DatabaseHelper dbh;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_18);

        dbh = new DatabaseHelper(this);
        Intent i = getIntent();
        email = i.getStringExtra("email");

        Button btnReturnToProfile = findViewById(R.id.btnReturn);


        btnReturnToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put this input to the database somehow
                Intent i = new Intent(Activity_18.this,Activity_9.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
    }
}
