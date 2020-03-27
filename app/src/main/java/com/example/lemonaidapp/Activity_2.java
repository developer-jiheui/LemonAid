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
        dbh.addrecordComment("em","emaildfd","I am dying30","");

        dbh.addrecordOffice("Canada","AB","111 1st Street","T6B 6B6");
        dbh.addrecordOffice("Canada","AB","222 2nd Street","T7H 7H7");
        dbh.addrecordOffice("Canada","BC","11 Douglas Street","V3L 2G5");
        dbh.addrecordOffice("Canada","BC","22 College Street","V4G 1T6");
        dbh.addrecordOffice("Canada","BC","33 Student Street","V7Y 8H0");
        dbh.addrecordOffice("Canada","BC","44 CSIS Street","V6S 7Y4");

        dbh.addrecordLogin("email","111");
        dbh.addrecordLogin("email12","12");
        dbh.addrecordLogin("email123","123");
        dbh.addrecordLogin("email1234","1234");
        dbh.addrecordLogin("em","0");
        dbh.addrecordLogin("Cashier","1");

        dbh.addrecordOffice("Vancouver", "BC","111 1st","V4f 3F5");
        dbh.addrecordStaff("jak","bil","em","343242","Doctor",1);
        dbh.addrecordStaff("jak","bil","Cashier","343242","Cashier",123);
        dbh.addrecordPatient("Bill","B","email","667-969-5656","no",50,"x1x 1x1",false);

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
                        //Jiheui: I changed this to Activity8 (Patient Profile page)
                        Intent i = new Intent(Activity_2.this, Activity_8.class);
                        i.putExtra("email", userEmail);
                        startActivity(i);
                    }
                    else{
                        if (dbh.getdataStaff(userEmail,4).equals("Doctor")) {
                            Intent iD = new Intent(Activity_2.this, Activity_3.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        if (dbh.getdataStaff(userEmail,4).equals("Cashier")) {
                            Intent iD = new Intent(Activity_2.this, Activity_4.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        if (dbh.getdataStaff(userEmail,4).equals("Admin")) {
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
