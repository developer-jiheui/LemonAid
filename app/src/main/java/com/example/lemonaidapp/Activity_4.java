package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import java.util.ArrayList;

public class Activity_4 extends ListActivity {
    DatabaseHelper dbh;
    ArrayList<String> listOfPatients;
    ArrayList<String> numberOfReminders;
    ArrayList<String> display = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_4);
        this.setTitle("Cashier logged in: Payment Reminders");
        final ListView listView = getListView();

        Intent i = getIntent();
        String cEmail = i.getStringExtra("email");
        dbh = new DatabaseHelper(this);
        listOfPatients = dbh.getAllPatientsOwing(1);
        numberOfReminders = dbh.getNumberOfReminders();
        for (int ind = 0; ind < listOfPatients.size(); ind++) {
            display.add(listOfPatients.get(ind) + numberOfReminders.get(ind));
        }

        adapter = new ArrayAdapter<String>
                (this, R.layout.activity_4, R.id.patientOwed,
                        display) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(R.id.patientOwed);


                if (Integer.parseInt(numberOfReminders.get(position))>=5) {
                    tv.setTextColor(Color.RED);
                }

                return view;
            }
        };
        setListAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Message = "Reminder sent to " + dbh.getAllPatientsOwing(2).get(position);
                Toast.makeText(Activity_4.this, Message, Toast.LENGTH_LONG).show();
                int number = Integer.parseInt(numberOfReminders.get(position)) + 1;

                dbh.updateNumRemin(dbh.getAllPatientsOwing(2).get(position), Integer.toString(number));
                numberOfReminders = dbh.getNumberOfReminders();
                display.clear();
                for (int ind = 0; ind < listOfPatients.size(); ind++) {
                    display.add(listOfPatients.get(ind) + numberOfReminders.get(ind));
                }
                if (number >= 5) {
                    ((TextView) listView.getChildAt(position).findViewById(R.id.patientOwed)).setTextColor(Color.RED);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}




