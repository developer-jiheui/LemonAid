package com.example.lemonaidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3actual);
        /**
         Intent intent = getIntent();
         final String email = intent.getStringExtra("email");
         Button btnEdit = findViewById(R.id.btnEditProfile);
         Button btnLogOut = findViewById(R.id.btnLogOut);
         Button btnFindDoc = findViewById(R.id.btnFindDoc);
         Button btnViewPayment = findViewById(R.id.btnHistory);
         Button btnMakePayment = findViewById(R.id.btnMakePay);
         TextView helloName = findViewById(R.id.txtHelloFname);
         TextView amountOwe = findViewById(R.id.txtAccountBalance);
         dbh = new DatabaseHelper(this);
         helloName.setText("Hello, " + dbh.getdataPatient(email,1));
         amountOwe.setText("$" + dbh.getdataPatient(email,8));


         if (dbh.getdataPatient(email,7).equals(true)){
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_3actual.this);


         final EditText et = new EditText(Activity_3actual.this);
         et.setText("Input New Password!");

         // set prompts.xml to alertdialog builder
         alertDialogBuilder.setView(et);

         // set dialog message
         alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
         String password = et.getText().toString();
         dbh.updatePassword(email,password);
         }
         });

         // create alert dialog
         AlertDialog alertDialog = alertDialogBuilder.create();
         // show it
         alertDialog.show();
         }

         btnEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_3actual.this,Activity_3.class);
        i.putExtra("email",email);
        i.putExtra("password",dbh.getPasswordForLogin(email));
        startActivity(i);
        }
        });
         btnLogOut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        startActivity(new Intent(Activity_3actual.this, Activity_2.class));
        }
        });
         btnViewPayment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_3actual.this,Activity_13.class);
        i.putExtra("email",email);
        startActivity(i);
        }
        });
         btnMakePayment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_3actual.this,Activity_14.class);
        i.putExtra("email",email);
        startActivity(i);
        }
        });
         btnFindDoc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_3actual.this,Activity_10.class);
        i.putExtra("email",email);
        startActivity(i);
        }
        });
         **/
    }
}
