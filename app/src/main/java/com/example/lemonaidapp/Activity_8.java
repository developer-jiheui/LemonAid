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
import android.widget.TextView;
import android.widget.Toast;

public class Activity_8 extends AppCompatActivity {
    DatabaseHelper dbh;
    String email;
    String toastMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        TextView greeting = findViewById(R.id.txtGreeting);
        final EditText etFirstName = findViewById(R.id.etFName);
        final EditText etLastName = findViewById(R.id.etLName);
        final EditText etPhoneNum = findViewById(R.id.etPh);
        final EditText etPassword = findViewById(R.id.edPassword);
        final EditText etMSP = findViewById(R.id.etMSP);
        final EditText etAge = findViewById(R.id.etAge);
        final EditText etPostalCode = findViewById(R.id.etPostal);
        final CheckBox hasNoMSP = findViewById(R.id.checkBoxMSP);

        dbh = new DatabaseHelper(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Toast.makeText(Activity_8.this,email+ " is loged in", Toast.LENGTH_LONG).show();

        final boolean adminEdit = intent.getBooleanExtra("adminEdit", false);

        final String oldfName = dbh.getdataPatient(email,1).toString();
        final String oldlName = dbh.getdataPatient(email,2).toString();
        final String oldphone = dbh.getdataPatient(email,3).toString();
        final String oldMsp= dbh.getdataPatient(email,4).toString();
        final String oldage = dbh.getdataPatient(email,5).toString();
        final String oldPostal = dbh.getdataPatient(email,6).toString();
        final String oldpassword = dbh.getPasswordForLogin(email).toString();



        greeting.setText("Hello, " + oldfName+"\n"+email);
        etFirstName.setText(oldfName);
        etLastName.setText(oldlName);
        etPassword.setText(oldpassword);
        etPhoneNum.setText(oldphone);
        etAge.setText(oldage);
        etPostalCode.setText(oldPostal);
        if(oldMsp.equals("no")){
            hasNoMSP.setChecked(true);
        }
        else{
            etMSP.setText(oldMsp);
        }



        btnEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent activity_6 = new Intent(Activity_8.this, Activity_6.class);
                    Intent activity_9 = new Intent(Activity_8.this, Activity_9.class);
                toastMsg="";

                String newfName = etFirstName.getText().toString();
                String newlName = etLastName.getText().toString();
                String newphone = etPhoneNum.getText().toString();
                String newpassword = etPassword.getText().toString();
                String newage = etAge.getText().toString();
                String newmsp;
                String newpostal = etPostalCode.getText().toString();

                if(hasNoMSP.isChecked()){
                    newmsp = "no";
                }
                else{
                    newmsp = etMSP.getText().toString();
                }

                boolean isUpdatedPatientFirstName = !oldfName.equals(newfName);
                boolean isUpdatedPatientLastName = !oldlName.equals(newlName);
                boolean isUpdatedPatientPhone = !oldphone.equals(newphone);
                boolean isUpdatedPatientMsp = !oldMsp.equals(newmsp);
                boolean isUpdatedPatientAge = !oldage.equals(newage);
                boolean isUpdatedPatientPostal = !oldPostal.equals(newpostal);
                boolean isUpdatedPatientPassword = !oldpassword.equals(newpassword);



                if(!isUpdatedPatientFirstName&&!isUpdatedPatientLastName&&
                   !isUpdatedPatientPhone&&!isUpdatedPatientMsp&&!isUpdatedPatientAge&&
                   !isUpdatedPatientPostal&&!isUpdatedPatientPassword){
                    Toast.makeText(Activity_8.this, "No edit has been done", Toast.LENGTH_LONG).show();
                }
                else{
                    if(isUpdatedPatientFirstName){
                        dbh.updatePatientInfo(email,1,newfName);
                        addMsg("First Name");
                    }
                    if(isUpdatedPatientLastName){
                        dbh.updatePatientInfo(email,2,newlName);
                        addMsg("Last Name");
                    }if(isUpdatedPatientPhone){
                        dbh.updatePatientInfo(email,6,newphone);
                        addMsg("Phone number");
                    }if(isUpdatedPatientMsp){
                        dbh.updatePatientInfo(email,4,newmsp);
                        addMsg("MSP information");
                    }if(isUpdatedPatientAge){
                        dbh.updatePatientInfo(email,5,newage);
                        addMsg("Age");
                    }if(isUpdatedPatientPostal){
                        dbh.updatePatientInfo(email,8,newpostal);
                        addMsg("Postal Code");
                    }
                    if(isUpdatedPatientPassword){
                        dbh.updatePassword(email,newpassword);
                        addMsg("Password");
                    }
                    addMsg("is successfully updated");
                    Toast.makeText(Activity_8.this,toastMsg, Toast.LENGTH_LONG).show();
                }
                if(adminEdit)
                    Activity_8.super.onBackPressed();
                else {
                    activity_9.putExtra("email", email);
                    startActivity(activity_9);
                    }
            }
        });
    }

    private void addMsg(String msg){
        if(toastMsg==""){
            toastMsg = "Account: "+email+ "\n"+msg;
        }
        else{
            toastMsg +="\n"+ msg;
        }
    }

}
