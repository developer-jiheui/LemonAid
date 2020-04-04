package com.example.lemonaidapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Activity_16 extends AppCompatActivity {
    DatabaseHelper dbh;
    double payBalance;
    double noMSPamt =135.00;
    String userMsgInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_16);

        dbh = new DatabaseHelper(this);
        final TextView noMspText = findViewById(R.id.txtMessageNote);
        final EditText patientMsg = findViewById(R.id.editComplainNoMSP);
        Button btnNoMSPSendMsg = findViewById(R.id.btnSendNoMSP);

        //also show the noMSP fee (How much is it?)
        Intent i = getIntent();
        final String email = i.getStringExtra("email");
        final String docEmail = i.getStringExtra("docEmail");


        noMspText.setText("Please note for users with no MSP online consultation is $"+noMSPamt);



        btnNoMSPSendMsg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                LocalDate localDate = LocalDate.now();
                userMsgInput = patientMsg.getText().toString();
                dbh.addrecordComment(docEmail,email,userMsgInput,"");
                // add record of the transaction to the database ~Darya
                dbh.addrecordTransaction(docEmail,(int)noMSPamt,email,localDate.toString());

                // update the amount owing in the patient_table ~Darya
                int amountOwingPrior = Integer.parseInt(dbh.getdataPatient(email,8));
                int amountCurrent = amountOwingPrior + (int)noMSPamt;
                dbh.updateAmountOwed(email,Integer.toString(amountCurrent));

                //put this input to the database somehow
                Intent i = new Intent(Activity_16.this,Activity_15.class);

                startActivity(i);
            }
        });




    }
}
