package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity_8 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        Button btnRegister = findViewById(R.id.btnRegister);
        final EditText etFirstName = findViewById(R.id.etFName);
        final EditText etLastName = findViewById(R.id.etLName);
        final EditText etPhoneNum = findViewById(R.id.etPh);
        final EditText etEmail = findViewById(R.id.etEm);
        final EditText etMSP = findViewById(R.id.etMSP);
        final EditText etAge = findViewById(R.id.etAge);
        final EditText etPostalCode = findViewById(R.id.etPostal);
        final CheckBox hasNoMSP = findViewById(R.id.checkBoxMSP);
        dbh = new DatabaseHelper(this);

        //Retrieve user type from activity_5
        String userType;

        Intent intent = getIntent();
        final boolean createdByAdmin = intent.getBooleanExtra("createdByAdmin", false);
        if(intent != null) {
            userType = intent.getStringExtra("userType");
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            boolean isInsertedPatient;
            boolean isInsertedLogin;
            String MSP;
            @Override
            public void onClick(View v) {
                // If person doesn't have MSP, assign 0 as MSP number
                if (hasNoMSP.isChecked())
                    MSP = "no";
                //Add record to Patient table
                isInsertedPatient = dbh.addrecordPatient(etFirstName.getText().toString(), etLastName.getText().toString(),
                        etEmail.getText().toString(), etPhoneNum.getText().toString(),
                        MSP, Integer.parseInt(etAge.getText().toString()),
                        etPostalCode.getText().toString(), createdByAdmin);

                // Insert to Login table
                isInsertedLogin = dbh.addrecordLogin(etEmail.getText().toString(), etLastName.getText().toString() + etLastName.getText().toString().charAt(0));
                //Verify staff insert
                if(isInsertedPatient) {
                    Toast.makeText(Activity_8.this, "Patient Record added", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Activity_8.this, "Patient Record not added", Toast.LENGTH_LONG).show();
                }
                //Verify Login insert
                if(isInsertedLogin) {
                    Toast.makeText(Activity_8.this, "Login Record added", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Activity_8.this, "Login Record not added", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
