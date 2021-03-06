package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_7 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        dbh = new DatabaseHelper(this);

        Button btnRegister = findViewById(R.id.btnRegister);
        final EditText etFirstName = findViewById(R.id.etFirstName);
        final EditText etLastName = findViewById(R.id.etLastName);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etOfficeID = findViewById(R.id.etOfficeID);
        final EditText etPhoneNum = findViewById(R.id.etPhoneNum);
        final EditText etEmail = findViewById(R.id.etEm);
        dbh = new DatabaseHelper(this);


        //Retrieve user type from activity_5
        final String userType;
        boolean editRequest = false;
        Intent intent = getIntent();
        if(intent != null) {
            userType = intent.getStringExtra("userType");
            //Retrieve editRequest from Activity_6
            editRequest = intent.getBooleanExtra("editRequest", false);
        }
        else {
            userType = "New User";
        }

        if(!editRequest) {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                boolean isInsertedStaff;
                boolean isInsertedLogin;

                @Override
                public void onClick(View v) {
                    // Insert to staff table
                    isInsertedStaff = dbh.addrecordStaff(etFirstName.getText().toString(), etLastName.getText().toString(),
                            etEmail.getText().toString(), etPhoneNum.getText().toString(), userType,
                            etOfficeID.getText().toString());
                    // Insert to Login table
                    isInsertedLogin = dbh.addrecordLogin(etEmail.getText().toString(), etPassword.getText().toString());
                    //Verify staff insert
                    if (isInsertedStaff) {
                        Toast.makeText(Activity_7.this, "Staff Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_7.this, "Staff Record not added", Toast.LENGTH_LONG).show();
                    }
                    //Verify Login insert
                    if (isInsertedLogin) {
                        Toast.makeText(Activity_7.this, "Login Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_7.this, "Login Record not added", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }
        else {
            etFirstName.setText(intent.getStringExtra("fName"));
            etLastName.setText(intent.getStringExtra("lName"));
            etOfficeID.setText(intent.getStringExtra("officeID"));
            etPhoneNum.setText(intent.getStringExtra("phone"));
            etEmail.setText(intent.getStringExtra("email"));
            etPassword.setText("");

            btnRegister.setOnClickListener(new View.OnClickListener() {
                boolean isUpdatedStaff1;
                boolean isUpdatedStaff2;
                boolean isUpdatedStaff3;
                boolean isUpdatedStaff4;
                boolean isUpdatedStaff5;
                boolean isUpdatedLogin;

                @Override
                public void onClick(View v) {
                    String email = etEmail.getText().toString();
                    String fName = etFirstName.getText().toString();
                    String lName = etLastName.getText().toString();
                    String officeID = etOfficeID.getText().toString();
                    String phone = etPhoneNum.getText().toString();
                    String password = etPassword.getText().toString();

                    // Insert to staff table
                   isUpdatedStaff1 = dbh.updateDoctorInfo(email, 1, fName);
                   isUpdatedStaff2 = dbh.updateDoctorInfo(email, 2, lName);
                   isUpdatedStaff3 = dbh.updateDoctorInfo(email, 3, email);
                   isUpdatedStaff4 = dbh.updateDoctorInfo(email, 5, officeID);
                   isUpdatedStaff5 = dbh.updateDoctorInfo(email, 6, phone);
                    // Insert to Login table
                    isUpdatedLogin = dbh.updatePassword(email, password);
                    //Verify staff insert
                    if (isUpdatedStaff1 && isUpdatedStaff2 && isUpdatedStaff3 && isUpdatedStaff4 && isUpdatedStaff5) {
                        Toast.makeText(Activity_7.this, "Staff Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_7.this, "Some fields are missing", Toast.LENGTH_LONG).show();
                    }
                    //Verify Login insert
                    if (isUpdatedLogin) {
                        Toast.makeText(Activity_7.this, "Login Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_7.this, "Login Record not added", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }


    }
}
