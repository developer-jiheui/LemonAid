package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_13 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);
        dbh= new DatabaseHelper(this);

        Intent i = getIntent();
        final String email = i.getStringExtra("email");

        TextView helloForm  = findViewById(R.id.txtUsername);
        TextView paymentList = findViewById(R.id.txtPaymentHistory);
        Button btnProfile = findViewById(R.id.btnBackProfile);
        helloForm.setText("Hello, " + dbh.getdataPatient(email,1));

        if (!dbh.getdataPatient(email,4).equals("NO")){
            paymentList.setText("You have MSP coverage \n Services are Free");
        }
        else{
            ArrayList<Integer> listOfCharges = dbh.getAllAmountOwed(email);
            ArrayList<String> listOfDates = dbh.getAllDatesOfCharges(email);
            String chargeHistory = "";
            for (int index = 0; index<listOfCharges.size();index++){
                chargeHistory += listOfDates.get(index) + " " + listOfCharges.get(index) + "$\n" ;
            }
            paymentList.setText(chargeHistory);
        }

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                                                    //Jiheui: I changed this from 3_actual to 9(Patient profile)
                Intent intent = new Intent (Activity_13.this,Activity_8.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

    }
}
