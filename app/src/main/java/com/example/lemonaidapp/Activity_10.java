package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_10 extends AppCompatActivity {

    DatabaseHelper dbh;

    ArrayList<String> listOfDoctors = new ArrayList<String>();
    ArrayList<String> listOfficeIDs = new ArrayList<String>();

    String letter;
    String patientEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);
        final List<HashMap<String, String>> aList = new ArrayList
                <HashMap<String, String>>();

        dbh = new DatabaseHelper(this);
        TextView patientLocation = findViewById(R.id.txtLocation);
        final EditText patientAddress = findViewById(R.id.etLocation);
        Button findDoctor = findViewById(R.id.btnSearchDr);
        final ListView doctorslist = findViewById(R.id.lstDoctors);

        Intent intent = getIntent();
        patientEmail = intent.getStringExtra("email");

        //Patient clicks use location: This  method uses the patients stored postal code information
        patientLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letter = dbh.getdataPatient(patientEmail,6).substring(0,1);

                listOfficeIDs = dbh.getOfficeIDs(letter.toUpperCase());
                listOfDoctors.clear();
                for (int i = 0; i < listOfficeIDs.size(); i++) {

                    listOfDoctors = dbh.getListDoctors(listOfficeIDs.get(i), listOfDoctors);
                }
                if (listOfDoctors.size()==0) {
                    Toast.makeText(Activity_10.this, "No nearby Doctors", Toast.LENGTH_LONG).show();}
                else{

                    for (int in = 0; in < listOfDoctors.size(); in++) {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("doc", listOfDoctors.get(in));
                        aList.add(hm);
                    }
                    String[] from = {"doc"};
                    int[] to = {R.id.doctorInfo};
                    SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),
                            aList, R.layout.listviewdoctors, from, to);
                    doctorslist.setAdapter(adapter);

                }
            }
        });
        //patient clicks find to search for doctors near entered location
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letter = patientAddress.getText().toString().substring(0,1);
                listOfficeIDs = dbh.getOfficeIDs(letter.toUpperCase());
                listOfDoctors.clear();
                for (int i = 0; i < listOfficeIDs.size(); i++) {

                    listOfDoctors = dbh.getListDoctors(listOfficeIDs.get(i), listOfDoctors);
                }
                if (listOfDoctors.size()==0) {
                    Toast.makeText(Activity_10.this, "No nearby Doctors", Toast.LENGTH_LONG).show();}
                else{

                    for (int in = 0; in < listOfDoctors.size(); in++) {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("doc", listOfDoctors.get(in));
                        aList.add(hm);
                    }
                    String[] from = {"doc"};
                    int[] to = {R.id.doctorInfo};
                    SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),
                            aList, R.layout.listviewdoctors, from, to);
                    doctorslist.setAdapter(adapter);

                }
            }
        });

        // patient selects a doctor
        doctorslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Activity_10.this,Activity_11.class);
                String emailDoc = listOfDoctors.get(position).split(" ")[2];
                intent.putExtra("docEmail", emailDoc);
                intent.putExtra("patientEmail", patientEmail);
                startActivity(intent);
            }
        });

    }
}
