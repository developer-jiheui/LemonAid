package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_2 extends AppCompatActivity {
    DatabaseHelper dbh;
    String userEmail;
    String userPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        final EditText emailEdit = findViewById(R.id.idText);
        final EditText passEdit = findViewById(R.id.passText);
        Button buttonLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        dbh = new DatabaseHelper(this);

        dbh.addrecordComment("em","email","I am dying","");
        dbh.addrecordComment("em","email1233","I am dying20","zxzxz");
        dbh.addrecordComment("em","emaildfd","2020-03-29 10:38:59.849 29695-29728/com.example.lemonaidapp D/EGL_emulation: eglMakeCurrent: 0xec005300: ver 2 0 (tinfo 0xec0036a0)\n" +
                "2020-03-29 10:38:59.884 29695-29728/com.example.lemonaidapp D/EGL_emulation: eglMakeCurrent: 0xec005300: ver 2 0 (tinfo 0xec0036a0)\n" +
                "2020-03-29 10:38:59.909 29695-29728/com.example.lemonaidapp D/EGL_emulation: eglMakeCurrent: 0xec005300: ver 2 0 (tinfo 0xec0036a0)","");


        dbh.addrecordOffice("Vancouver", "BC","111 1st","V4f 3F5");

        dbh.addrecordLogin("email","111");
        dbh.addrecordLogin("em","0");
        dbh.addrecordLogin("Cashier","1");
        dbh.addrecordLogin("Fuller@gmail.com","111");

        //Joel: Added an admin log-in to lead to admin page (activity_5)
        dbh.addrecordLogin("Admin", "admin123");


        dbh.addrecordStaff("jak","bil","em","343242","Doctor","1");

        //Joel: Added admin staff
        dbh.addrecordStaff("admin", "admin", "Admin", "000000", "Admin", "0");
        dbh.addrecordStaff("jak","bil","Cashier","343242","Cashier","123");
        dbh.addrecordPatient("Bill","B","email","667-969-5656","no","50","x1x 1x1","no");
        dbh.addrecordPatient("Bob","Fuller","Fuller@gmail.com","667-969-5656","n45456456","50","x1x 1x1","no");
        dbh.addrecordTransaction("em",135,"email","October 1, 2020");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  userEmail = emailEdit.getText().toString();
              //  userPass = passEdit.getText().toString();
                //Jiheui: I changed this to Activity8 (Patient Profile page)
                Intent i = new Intent(Activity_2.this,Activity_8.class);
             //   i.putExtra("email",userEmail);
              //  i.putExtra("password", userPass);
                startActivity(i);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = emailEdit.getText().toString();
                userPass = passEdit.getText().toString();
                String correctPass = dbh.getPasswordForLogin(userEmail);
                if (userPass.equals(correctPass)){
                    String patientName = dbh.getdataPatient(userEmail,1);

                    if (!patientName.equals("")) {
                                         //Jiheui: I changed this to Activity 9 (Patient Profile page)
                        Intent i = new Intent(Activity_2.this, Activity_9.class);
                        i.putExtra("email", userEmail);
                        startActivity(i);
                    }
                    else{
                        if (dbh.getdataStaff(userEmail,4).equals("Doctor")) {
                            Intent iD = new Intent(Activity_2.this, Activity_3.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        else if (dbh.getdataStaff(userEmail,4).equals("Cashier")) {
                            Intent iD = new Intent(Activity_2.this, Activity_4.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        else if (dbh.getdataStaff(userEmail,4).equals("Admin")) {
                            Intent iD = new Intent(Activity_2.this, Activity_5.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                    }
                }
                else{
                    Toast.makeText(Activity_2.this,"Wrong Email or Password! Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
