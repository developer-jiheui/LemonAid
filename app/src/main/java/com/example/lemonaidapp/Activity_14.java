package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_14 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_14);
        dbh = new DatabaseHelper(this);
        Intent i = getIntent();
       final String email = i.getStringExtra("email");
        TextView amountOwe = findViewById(R.id.txtAmountDue);
        final int amountOwed = Integer.parseInt(dbh.getdataPatient(email,8));
        amountOwe.setText(" Amount Due:\n $" + amountOwed);
        Button btnReturn = findViewById(R.id.btnBack);
        Button btnAuthorize = findViewById(R.id.btnAuthPay);
        final TextView payReceived =findViewById(R.id.txtPayReceived);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Activity_14.this,Activity_3actual.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        btnAuthorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountOwed == 0){
                    payReceived.setText("Balance is $0, no payment required!");
                }
                else{
                    payReceived.setText("Payment of " + amountOwed + "$ has been received! \n Thank you!");
                    dbh.updateAmountOwed(email);
                }
            }
        });


    }
}
