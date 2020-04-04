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
    ArrayList<String> dispMessageList = new ArrayList<String>();
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        this.setTitle("Active Messages!");
        dbh = new DatabaseHelper(this);

        Intent i = getIntent();
        email = i.getStringExtra("email");
        messageList = dbh.getActiveMessages(email);
        for (int ind=0; ind<messageList.size();ind++){
            if (messageList.get(ind).length()>70) {
                dispMessageList.add(messageList.get(ind).substring(0, 70) + "...");
            }
            else
            dispMessageList.add(messageList.get(ind));
        }
        messageList = dbh.getActiveMessages(email);

        setListAdapter(new ArrayAdapter<String>
                (this,R.layout.activity_3actual,R.id.message,
                        dispMessageList));
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
        alertDialogBuilder.setCancelable(false).setPositiveButton("Send Message", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (!et.getText().toString().equals("")){
                    dbh.updateDoctorReply(email,et.getText().toString());
                    messageList.remove(position);
                    dispMessageList.remove(position);
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