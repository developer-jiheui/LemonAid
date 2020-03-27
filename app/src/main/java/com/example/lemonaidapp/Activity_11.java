package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_11 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);

        dbh= new DatabaseHelper(this);
        Intent i = getIntent();
        final String email = i.getStringExtra("email");
        final String docEmail = i.getStringExtra("docEmail");


        Button btnOnlineHelp = findViewById(R.id.btnOnlineHelp);



        btnOnlineHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dbh.getdataPatient(email,4).equals("NO")){
                    Intent intent = new Intent(Activity_11.this, Activity_17.class);
                    intent.putExtra("email",email);
                    intent.putExtra("docEmail",email);

                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Activity_11.this, Activity_16.class);
                    intent.putExtra("email",email);
                    intent.putExtra("docEmail",email);
                    startActivity(intent);
                }
            }
        });
    }
}
