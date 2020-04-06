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
        final String patientEmail = i.getStringExtra("patientEmail");
        final String docEmail = i.getStringExtra("docEmail");

        //Toast.makeText(Activity_11.this,docEmail, Toast.LENGTH_LONG).show();
        TextView docName = findViewById(R.id.txtDoctorName);
        TextView officeStreet = findViewById(R.id.txtOfficeStreet);
        TextView officeCity = findViewById(R.id.txtOfficeCity);
        TextView postCode = findViewById(R.id.txtDocPostCode);
        TextView docCell = findViewById(R.id.txtDocCell);
        TextView email = findViewById(R.id.txtDocEmail);
        Button btnbooKAppointment =findViewById(R.id.btnBookAppoint);
        Button btnOnlineHelp = findViewById(R.id.btnOnlineHelp);
        Button btnReturnToSearch = findViewById(R.id.btnSearch);

        //Populate textfields for docotors info
        docName.setText(dbh.getdataStaff(docEmail,1) + " " + dbh.getdataStaff(docEmail,2));
        officeStreet.setText(dbh.getOfficeData(dbh.getdataStaff(docEmail,5),4));
        officeCity.setText(dbh.getOfficeData(dbh.getdataStaff(docEmail,5),2) + " , "+dbh.getOfficeData(dbh.getdataStaff(docEmail,5),3));
        postCode.setText(dbh.getOfficeData(dbh.getdataStaff(docEmail,5),5));
        docCell.setText(dbh.getdataStaff(docEmail, 3));
        email.setText(docEmail);

        //PatientClicks Book appointment
        btnbooKAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_11.this,Activity_12.class);
                intent.putExtra("email",patientEmail);
                intent.putExtra("docEmail",docEmail);
                startActivity(intent);
            }
        });

        //Patient clicks get online help
        btnOnlineHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dbh.getdataPatient(patientEmail,4).equals("no")){
                    Intent intent = new Intent(Activity_11.this, Activity_17.class);
                    intent.putExtra("email",patientEmail);
                    intent.putExtra("docEmail",docEmail);

                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Activity_11.this, Activity_16.class);
                    intent.putExtra("email",patientEmail);
                    intent.putExtra("docEmail",docEmail);
                    startActivity(intent);
                }
            }
        });
        btnReturnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_11.this, Activity_10.class);
                startActivity(intent);
            }
        });

    }
}
