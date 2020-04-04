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
DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

         Intent intent = getIntent();

         Button btnLogOut = findViewById(R.id.btnLogOut);
         Button btnFindDoc = findViewById(R.id.btnFindDoc);
         Button btnEditProfile = findViewById(R.id.btnEditProfile);
         Button btnViewPayment = findViewById(R.id.btnPaymentHistory);
         Button btnMakePayment = findViewById(R.id.btnMkPymt);
         TextView greeting = findViewById(R.id.txtGreeting);
         TextView accountBal = findViewById(R.id.txtAccBal);


         dbh = new DatabaseHelper(this);



        final String email = intent.getStringExtra("email");
        greeting.setText("Hello, " + dbh.getdataPatient(email, 1));
        accountBal.setText("Amount due: $" + dbh.getdataPatient(email, 8));

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_8 = new Intent(Activity_9.this, Activity_8.class);
                activity_8.putExtra("editRequest", true);
                activity_8.putExtra("fName", dbh.getdataPatient(email, 1));
                activity_8.putExtra("lName", dbh.getdataPatient(email, 2));
                activity_8.putExtra("phone", dbh.getdataPatient(email, 3));
                activity_8.putExtra("msp", dbh.getdataPatient(email, 4));
                activity_8.putExtra("age", dbh.getdataPatient(email, 5));
                activity_8.putExtra("postal", dbh.getdataPatient(email, 6));
                activity_8.putExtra("email", email);
                startActivity(activity_8);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_2 = new Intent(Activity_9.this, Activity_2.class);
                startActivity(activity_2);
            }
        });






        // amountOwe.setText("$" + dbh.getdataPatient(email,8));


         if (dbh.getdataPatient(email,7).equals(true)){
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_9.this);


         final EditText et = new EditText(Activity_9.this);
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

         btnFindDoc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Activity_9.this, Activity_10.class));
             }
         });

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        Intent i = new Intent(Activity_3actual.this,Activity_3.class);
//        i.putExtra("email",email);
//        i.putExtra("password",dbh.getPasswordForLogin(email));
//        startActivity(i);
//        }
//        });
//         btnLogOut.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        startActivity(new Intent(Activity_3actual.this, Activity_2.class));
//        }
//        });
         btnViewPayment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_9.this,Activity_13.class);
        i.putExtra("email",email);
        startActivity(i);
        }
        });
         btnMakePayment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent i = new Intent(Activity_9.this,Activity_14.class);
        i.putExtra("email",email);
        startActivity(i);
        }
        });
//         btnFindDoc.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        Intent i = new Intent(Activity_3actual.this,Activity_10.class);
//        i.putExtra("email",email);
//        startActivity(i);
//        }
//        });
//         **/
    }

    @Override
    protected void onResume() {
        TextView accountBal = findViewById(R.id.txtAccBal);
        super.onResume();
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        dbh = new DatabaseHelper(this);
        accountBal.setText("Amount due: $" + dbh.getdataPatient(email, 8));

    }
}
