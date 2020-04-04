package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_17 extends AppCompatActivity {
    DatabaseHelper dbh;
    String userMsgInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_17);

        dbh = new DatabaseHelper(this);
        Intent i = getIntent();
        final String email = i.getStringExtra("email");
        final String docEmail = i.getStringExtra("docEmail");

        final EditText patientMsg = findViewById(R.id.editComplaint);
        Button btnPatientMsg = findViewById(R.id.btnSubmitComplaint);


        btnPatientMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userMsgInput = patientMsg.getText().toString();
                dbh.addrecordComment(docEmail,email,userMsgInput,"");

                //put this input to the database somehow
                Intent i = new Intent(Activity_17.this,Activity_18.class);


                startActivity(i);
            }
        });
    }
}
