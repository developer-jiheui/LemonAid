package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            @Override
            public void onClick(View v) {
                userMsgInput = patientMsg.getText().toString();
                dbh.addrecordComment(docEmail,email,userMsgInput,null);
                //put this input to the database somehow
                Intent i = new Intent(Activity_16.this,Activity_15.class);

                startActivity(i);
            }
        });




    }
}
