package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity_6 extends AppCompatActivity {
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etMSP = findViewById(R.id.etMSP);
        Button btnFindProfile = findViewById(R.id.btnFindProfile);

        etUsername.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etMSP.setText(null);
            }
        });

        etMSP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etUsername.setText(null);
            }
        });

        btnFindProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mspQuery = etMSP.getText().toString();
                String usernameQuery = etUsername.getText().toString();
                Cursor patientC = dbh.getPatientData();
                Cursor staffC = dbh.getStaffData();

                if (Integer.parseInt(mspQuery) > 0) {
                    while (patientC.moveToNext()) {
                        if (patientC.getString(5).equals(mspQuery)) {
                            Intent i = new Intent(Activity_6.this, Activity_8.class);
                            i.putExtra("userFName", patientC.getString(1));
                            i.putExtra("userLName", patientC.getString(2));
                            i.putExtra("userEmail", patientC.getString(3));
                            i.putExtra("userPhone", patientC.getString(4));
                            i.putExtra("userMSP", patientC.getString(5));
                            i.putExtra("userAge", patientC.getString(6));
                            i.putExtra("userPostal", patientC.getString(7));
                            i.putExtra("editRequest", true);

                            startActivity(new Intent(Activity_6.this, Activity_8.class));
                            break;
                        }
                    }
                } else {
                    while (staffC.moveToNext()) {
                        if (staffC.getString(5).equals(usernameQuery)) {
                            Intent i = new Intent(Activity_6.this, Activity_7.class);
                            i.putExtra("staffFName", staffC.getString(1));
                            i.putExtra("staffLName", staffC.getString(2));
                            i.putExtra("staffEmail", staffC.getString(3));
                            i.putExtra("staffPhone", staffC.getString(4));
                            i.putExtra("staffType", staffC.getString(5));
                            i.putExtra("staffOfficeID", staffC.getString(6));
                            i.putExtra("editRequest", true);

                            startActivity(new Intent(Activity_6.this, Activity_7.class));
                            break;
                        }

                    }
                }
            }
        });

    }
}