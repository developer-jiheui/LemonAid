package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity_6 extends AppCompatActivity {
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        dbh = new DatabaseHelper(this);

        final TextView txtUsername = findViewById(R.id.txtUsername);
        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etMSP = findViewById(R.id.etMSP);
        Button btnFindProfile = findViewById(R.id.btnFindProfile);

        Intent intentUsername = getIntent();
        if (intentUsername != null) {
            txtUsername.setText(intentUsername.getStringExtra("email"));

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
                    String usernameQuery = etUsername.getText().toString();
                    String msp = etMSP.getText().toString();

                    if (!TextUtils.isEmpty(etUsername.getText().toString())) {

                        String email = dbh.getdataStaff(usernameQuery, 6);


                        if (!email.equals("")) {

                            String fName = dbh.getdataStaff(usernameQuery, 1);
                            String lName = dbh.getdataStaff(usernameQuery, 2);
                            String phone = dbh.getdataStaff(usernameQuery, 3);
                            String staffType = dbh.getdataStaff(usernameQuery, 4);
                            String officeID = dbh.getdataStaff(usernameQuery, 5);


                            if (!fName.equals("") && !lName.equals("") && !phone.equals("") && !staffType.equals("") && !officeID.equals("")) {
                                Intent i = new Intent(Activity_6.this, Activity_7.class);
                                i.putExtra("fName", fName);
                                i.putExtra("lName", lName);
                                i.putExtra("phone", phone);
                                i.putExtra("staffType", staffType);
                                i.putExtra("officeID", officeID);
                                i.putExtra("email", email);
                                i.putExtra("editRequest", true);
                                i.putExtra("adminEdit", true);

                                startActivity(i);
                            }
                        }
                        else

                            email = dbh.getdataPatient(usernameQuery, 9);

                        if (!email.equals("")) {

                            String fName = dbh.getdataPatient(usernameQuery, 1);
                            String lName = dbh.getdataPatient(usernameQuery, 2);
                            String phone = dbh.getdataPatient(usernameQuery, 3);
                            String MSP = dbh.getdataPatient(usernameQuery, 4);
                            String age = dbh.getdataPatient(usernameQuery, 5);
                            String postal = dbh.getdataPatient(usernameQuery, 6);

                            if (!fName.equals("") && !lName.equals("") && !phone.equals("") && !MSP.equals("") && !age.equals("") && !postal.equals("")) {
                                Intent i = new Intent(Activity_6.this, Activity_8.class);
                                i.putExtra("fName", fName);
                                i.putExtra("lName", lName);
                                i.putExtra("phone", phone);
                                i.putExtra("msp", MSP);
                                i.putExtra("age", age);
                                i.putExtra("postal", postal);
                                i.putExtra("email", email);
                                i.putExtra("isAdminC", "yes");
                                i.putExtra("editRequest", true);
                                i.putExtra("adminEdit", true);

                                startActivity(i);
                            }

                            else
                                Toast.makeText(Activity_6.this, "No staff record found", Toast.LENGTH_LONG).show();
                        }
                    }

                    else if (!TextUtils.isEmpty(etMSP.getText().toString())) {
                        Toast.makeText(Activity_6.this, "msp in", Toast.LENGTH_LONG);
                        String fName = dbh.getDataPatientMsp(msp, 1);
                        String lName = dbh.getDataPatientMsp(msp, 2);
                        String phone = dbh.getDataPatientMsp(msp, 3);
                        String MSP = dbh.getDataPatientMsp(msp, 4);
                        String age = dbh.getDataPatientMsp(msp, 5);
                        String postal = dbh.getDataPatientMsp(msp, 6);
                        String email = dbh.getDataPatientMsp(msp, 9);



                        if (!fName.equals("") && !lName.equals("") && !phone.equals("") && !MSP.equals("") && !age.equals("") && !postal.equals("")) {
                            Intent i = new Intent(Activity_6.this, Activity_8.class);
                            i.putExtra("fName", fName);
                            i.putExtra("lName", lName);
                            i.putExtra("phone", phone);
                            i.putExtra("msp", MSP);
                            i.putExtra("email", email);
                            i.putExtra("age", age);
                            i.putExtra("postal", postal);
                            i.putExtra("isAdminC", "yes");
                            i.putExtra("editRequest", true);

                            startActivity(i);
                        } else
                            Toast.makeText(Activity_6.this, "no patient record found", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Activity_6.this, "Both fields are empty", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}