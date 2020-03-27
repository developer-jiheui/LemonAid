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

        //Retrieve user type from activity_5 and check if this is an edit profile request from activity_6
        String userType;
        boolean activityRequest = false;

        // Passing the email from Activity_2 (loginPage)
        String userEmail;
        Intent intent = getIntent();
        final boolean createdByAdmin = intent.getBooleanExtra("createdByAdmin", false);
        if(intent != null) {
            userType = intent.getStringExtra("userType");
            activityRequest = intent.getBooleanExtra("editRequest", false);
            userEmail = intent.getStringExtra("email");
        }

        if(!activityRequest) {
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
                    if (isInsertedPatient) {
                        Toast.makeText(Activity_8.this, "Patient Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_8.this, "Patient Record not added", Toast.LENGTH_LONG).show();
                    }
                    //Verify Login insert
                    if (isInsertedLogin) {
                        Toast.makeText(Activity_8.this, "Login Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_8.this, "Login Record not added", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }
        else {
            etFirstName.setText(intent.getStringExtra("userFName"));
            etLastName.setText(intent.getStringExtra("userLName"));
            etPhoneNum.setText(intent.getStringExtra("userPhone"));
            etEmail.setText(intent.getStringExtra("userEmail"));
            etMSP.setText(intent.getStringExtra("userMSP"));
            etAge.setText(intent.getStringExtra("userAge"));
            etPostalCode.setText(intent.getStringExtra("userPostal"));

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
                    if (isInsertedPatient) {
                        Toast.makeText(Activity_8.this, "Patient Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_8.this, "Patient Record not added", Toast.LENGTH_LONG).show();
                    }
                    //Verify Login insert
                    if (isInsertedLogin) {
                        Toast.makeText(Activity_8.this, "Login Record added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity_8.this, "Login Record not added", Toast.LENGTH_LONG).show();
                    }
                }

            });

        }
    }
}
/**
 *      dbh = new DatabaseHelper(this);
 *         final EditText emailEdit = findViewById(R.id.editEmAdd);
 *         final EditText fNameEdit = findViewById(R.id.editFname);
 *         final EditText lNameEdit = findViewById(R.id.editLname);
 *         final EditText phoneEdit = findViewById(R.id.editPnum);
 *         final EditText mspEdit = findViewById(R.id.editMsp);
 *         final RadioButton noMsp = findViewById(R.id.rdNoMsp);
 *         final EditText ageEdit = findViewById(R.id.editAge);
 *         final EditText postalEdit = findViewById(R.id.editPostal);
 *         Button btnRegisterInfo = findViewById(R.id.btnRegisterAcc);
 *
 *         Intent i = getIntent() ;
 *         final String email = i.getStringExtra("email");
 *         final String password = i.getStringExtra("password");
 *         emailEdit.setText(email);
 *
 *         btnRegisterInfo.setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View v) {
 *             String fName = fNameEdit.getText().toString();
 *             String lName = lNameEdit.getText().toString();
 *             String phoneNum = phoneEdit.getText().toString();
 *             String msp = mspEdit.getText().toString();
 *             int age = Integer.parseInt(ageEdit.getText().toString());
 *             String postalC = postalEdit.getText().toString();
 *
 *             if (!isValidName(fName)){
 *                 Toast.makeText(Activity_3.this,"Invalid First Name!No digits!", Toast.LENGTH_LONG).show();
 *             }
 *             if (!isValidName(lName)){
 *                 Toast.makeText(Activity_3.this,"Invalid Last Name! No digits!", Toast.LENGTH_LONG).show();
 *             }
 *             if (!isValidAge(age)){
 *                 Toast.makeText(Activity_3.this,"Invalid age!", Toast.LENGTH_LONG).show();
 *             }
 *             if (!isValidPNum(phoneNum)){
 *                 Toast.makeText(Activity_3.this,"Invalid phone number! Follow specified pattern!", Toast.LENGTH_LONG).show();
 *             }
 *             if (!isValidPostal(postalC)){
 *                 Toast.makeText(Activity_3.this,"Invalid Postal Code! Followed Specified Pattern!", Toast.LENGTH_LONG).show();
 *             }
 *
 *             if (noMsp.isChecked()){
 *                 msp = "NO";
 *             }
 *             if (isValidName(fName)&&isValidName(lName) && isValidAge(age) && isValidPostal(postalC)&& isValidPNum(phoneNum)){
 *                 dbh.addrecordPatient(fName,lName,email,phoneNum,msp,age,postalC,false);
 *                 dbh.addrecordLogin(email,password);
 *                 Intent intent = new Intent(Activity_3.this,Activity_3actual.class);
 *                 intent.putExtra("email",email);
 *                 startActivity(intent);
 *
 *             }
 *
 *
 *
 *
 *             }
 *         });
 *
 *
 *
 *
 *
 *     }
 *     public boolean isValidName (String name){
 *         boolean valid = true;
 *         for (int i = 0; i<name.length();i++){
 *             if (!Character.isLetter(name.charAt(i))){
 *                 valid = false;
 *             }
 *         }
 *         return valid;
 *     }
 *     public boolean isValidPNum (String pNum){
 *         if  (pNum.length()!=12){
 *             return false;
 *         }
 *         for (int i = 0; i<pNum.length();i++){
 *             if (i==3 || i ==7){
 *                 if (pNum.charAt(i)!='-'){
 *                     return false;
 *                 }
 *             }
 *             else if (!Character.isDigit(pNum.charAt(i))){
 *                 return false;
 *             }
 *         }
 *         return true;
 *     }
 *     public boolean isValidAge(int age){
 *         return (age>0 && age<120);
 *     }
 *     public boolean isValidPostal (String postal){
 *         if  (postal.length()!=7){
 *             return false;
 *         }
 *         for (int i = 0; i<postal.length();i++){
 *             if (i==0 || i ==2 || i==5){
 *                 if (!Character.isLetter(postal.charAt(i))){
 *                     return false;
 *                 }
 *             }
 *             else if (i ==3){
 *                 if (postal.charAt(i)!=' '){
 *                     return false;
 *                 }
 *             }
 *            else {
 *                if(!Character.isDigit(postal.charAt(i))){
 *                 return false;
 *             }
 *            }
 *         }
 *         return true;
 *     }
 * }
 */
