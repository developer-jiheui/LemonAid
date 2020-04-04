package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_3 extends AppCompatActivity {
    DatabaseHelper dbh;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        dbh = new DatabaseHelper(this);
        final TextView emailEdit = findViewById(R.id.txtEm);
        final EditText fNameEdit = findViewById(R.id.editFName);
        final EditText lNameEdit = findViewById(R.id.editLName);
        final EditText phoneEdit = findViewById(R.id.editPNum);
        final TextView officeEdit = findViewById(R.id.txtOL);

        Button btnViewComplaints = findViewById(R.id.btnViewComplaint);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        if (intent!=null) {
            email = intent.getStringExtra("email");
        }
        emailEdit.setText(email);
        fNameEdit.setText(dbh.getdataStaff(email,1));
        lNameEdit.setText(dbh.getdataStaff(email,2));
        phoneEdit.setText(dbh.getdataStaff(email,3));
        officeEdit.setText(dbh.getOfficeInfo(Integer.parseInt(dbh.getdataStaff(email,5))));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = "Data Changed: ";
                String newFName = fNameEdit.getText().toString();
                String newLName = lNameEdit.getText().toString();
                String newPNum = phoneEdit.getText().toString();
                if (!newFName.equals(dbh.getdataStaff(email,1))){
                    dbh.updateDoctorInfo(email,1,newFName);
                    Message += " \n First Name";
                }
                if (!newLName.equals(dbh.getdataStaff(email,2))){
                    dbh.updateDoctorInfo(email,2,newLName);
                    Message += "\n Last Name";
                }
                if (!newPNum.equals(dbh.getdataStaff(email,3))){
                    dbh.updateDoctorInfo(email,3,newPNum);
                    Message += "\n PhoneNumber";

                }
                Toast.makeText(Activity_3.this,Message, Toast.LENGTH_LONG).show();

            }
        });
        btnViewComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iD = new Intent(Activity_3.this, Activity_3actual.class);
                iD.putExtra("email", email);
                startActivity(iD);
            }
        });
    }
}