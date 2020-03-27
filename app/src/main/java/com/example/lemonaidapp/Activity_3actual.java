package com.example.lemonaidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class Activity_3actual extends ListActivity {
    DatabaseHelper dbh;
    ArrayList<String> messageList;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        this.setTitle("Active Messages!");
        dbh = new DatabaseHelper(this);
        dbh.addrecordComment("em","email","I am dying","");
        dbh.addrecordComment("em","email1233","I am dying20","zxzxz");
        dbh.addrecordComment("em","emaildfd","I am dying30","");
        Intent i = getIntent();
        email = i.getStringExtra("email");
        messageList = dbh.getActiveMessages(email);

        setListAdapter(new ArrayAdapter<String>
                (this,R.layout.activity_3actual,R.id.message,
                        messageList));
    }

    public void onListItemClick(ListView l, View view, final int position, long id){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_3actual.this);
        final TextView message = new TextView(this);
        message.setText(messageList.get(position));
        alertDialogBuilder.setCustomTitle(message);
        final EditText et = new EditText(Activity_3actual.this);
        et.setHint("Type your Message");

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(et);

        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (!et.getText().toString().equals("")){
                    dbh.updateDoctorReply(email,et.getText().toString());
                    messageList.remove(position);
                }
                else{
                    Toast.makeText(Activity_3actual.this,"Please Enter reply!", Toast.LENGTH_LONG).show();
                }

            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}