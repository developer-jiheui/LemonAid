package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        //Retrieve username from activity_2
        String username = null;
        Intent intent = getIntent();
        if(intent != null) {
            username = intent.getStringExtra("email");
        }
        final TextView txtInfo = findViewById(R.id.txtUsernameInfo);
        //set username at top left of activity_5
        txtInfo.setText(username);

        Button btnRegNewUser = findViewById(R.id.btnRegNewUser);
        Button btnChAccount = findViewById(R.id.btnChAccount);
        Button btnAppMtn = findViewById(R.id.btnAppMtn);
        Button btnLogOut = findViewById(R.id.btnLogOut);
        final RadioButton rdBtnPatient = findViewById(R.id.rdBtnPatient);
        final RadioButton rdBtnDoctor = findViewById(R.id.rdBtnDoctor);
        final RadioButton rdBtnAdmin = findViewById(R.id.rdBtnAdmin);
        final RadioButton rdBtnCachier = findViewById(R.id.rdBtnCachier);


        // If Register new user is clicked
        btnRegNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            /*
            If Patient is checked, send to patient to registration page together with user 'type' patient.
            Otherwise, send to the staff registration page together with user 'type'.
            */

            public void onClick(View v) {
                if(rdBtnPatient.isChecked()) {
                    Log.d("Activity5 try", "Clicked Button");

                    Intent i = new Intent(Activity_5.this, Activity_1.class);
                    i.putExtra("userType", "Patient");
                    i.putExtra("isAdminC", "yes");
                    startActivity(i);
                }
                else if(rdBtnDoctor.isChecked()){
                    Intent i = new Intent(Activity_5.this, Activity_7.class);
                    i.putExtra("userType", "Doctor");
                    startActivity(i);

                }

                else if(rdBtnAdmin.isChecked()){
                    Intent i = new Intent(Activity_5.this, Activity_7.class);
                    i.putExtra("userType", "Admin");
                    startActivity(new Intent(Activity_5.this, Activity_7.class));
                    startActivity(i);
                }
                else if(rdBtnCachier.isChecked()) {
                    Intent i = new Intent(Activity_5.this, Activity_7.class);
                    i.putExtra("userType", "Cachier");
                    startActivity(i);
                }

                else
                    Toast.makeText(Activity_5.this, "no option selected", Toast.LENGTH_LONG).show();

            }
        });

        //If Change Account is clicked, send them to account change page
        btnChAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_5.this, Activity_6.class);
                i.putExtra("email", txtInfo.getText().toString());
                startActivity(i);
            }
        });

        btnAppMtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_5.this, "App maintained", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_5.this, Activity_2.class));
            }
        });
    }
}
