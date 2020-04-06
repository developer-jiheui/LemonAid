package com.example.lemonaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_2 extends AppCompatActivity {
    DatabaseHelper dbh;
    String userEmail;
    String userPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        final EditText emailEdit = findViewById(R.id.idText);
        final EditText passEdit = findViewById(R.id.passText);
        Button buttonLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        dbh = new DatabaseHelper(this);

        //Jiheui : Patients with MSP
        dbh.addrecordLogin("Fuller@gmail.com","111");
        dbh.addrecordLogin("Patient1","1");
        //Patient with MSP and Admin Created
        dbh.addrecordLogin("Patient2","2");
        dbh.addrecordLogin("Patient3","3");

        //Jiheui : Patients without MSP
        dbh.addrecordLogin("email","111");
        dbh.addrecordLogin("email12","12");
        //Patient without MSP Admin Created
        dbh.addrecordLogin("email123","123");
        dbh.addrecordLogin("email1234","1234");

        //Jiheui : Doctor login
        dbh.addrecordLogin("em","0");
        dbh.addrecordLogin("doctor1","1");
        dbh.addrecordLogin("doctor2","2");
        dbh.addrecordLogin("doctor3","3");
        dbh.addrecordLogin("doctor4","4");
        dbh.addrecordLogin("doctor5","5");
        //Jiheui : Cashier login
        dbh.addrecordLogin("Cashier","1");
        dbh.addrecordLogin("Cashier2","2");
        dbh.addrecordLogin("Cashier3","3");
        dbh.addrecordLogin("Cashier4","4");
        dbh.addrecordLogin("Cashier5","5");
        //Jiheui : Admin login
        //Joel: Added an admin log-in to lead to admin page (activity_5)
        dbh.addrecordLogin("Admin", "admin123");
        dbh.addrecordLogin("Admin1","1");
        dbh.addrecordLogin("Admin2","2");
        dbh.addrecordLogin("Admin3","3");
        dbh.addrecordLogin("Admin4","4");
        dbh.addrecordLogin("Admin5","5");

        //Jiheui: Patients with MSP
        dbh.addrecordPatient("Bob","Fuller","Fuller@gmail.com","667-969-5656","4545645623","50","x1x 1x1","no");
        dbh.addrecordPatient("ABC","Lastname","Patient1","123-333-4567","1234567891","22","D4L 8G7","no");
        dbh.addrecordPatient("A","La","Patient3","123-314-4567","2345678910","62","V4H 8G7","yes");
        dbh.addrecordPatient("C","Las","Patient4","123-435-4567","3456789123","29","T4G 8G7","yes");

        //Jiheui: Patients without MSP
        dbh.addrecordPatient("Bill","B","email","667-969-5656","no","50","x1x 1x1","no");
        dbh.addrecordPatient("West","D","email12",null, "no","40","V3Y 6H9","no");
        dbh.addrecordPatient("West","A","email123","123-456-1111", "no","40","V3Y 6H9","yes");
        dbh.addrecordPatient("West","C","email1234","000-123-1111", "no","40","V3Y 6H9","yes");

        //Office
        dbh.addrecordOffice("Canada","AB","111 1st Street","T6B 6B6");
        dbh.addrecordOffice("Canada","AB","222 2nd Street","T7H 7H7");
        dbh.addrecordOffice("Canada","BC","11 Douglas Street","V3L 2G5");
        dbh.addrecordOffice("Canada","BC","22 College Street","V4G 1T6");
        dbh.addrecordOffice("Canada","BC","33 Student Street","V7Y 8H0");
        dbh.addrecordOffice("Canada","BC","44 CSIS Street","V6S 7Y4");
        dbh.addrecordOffice("Canada","BC","55 douglas Street","D6S 7Y4");

        //Jiheui : Doctors
        dbh.addrecordStaff("jak","bil","em","667-345-5656","Doctor","1");
        dbh.addrecordStaff("Melissa","Jo","doctor1","778-969-5656","Doctor","2");
        dbh.addrecordStaff("el","John","doctor2","778-969-0000","Doctor","3");
        dbh.addrecordStaff("Mel","what","doctor3","778-012-5656","Doctor","4");
        dbh.addrecordStaff("issa","Kaufman","doctor4","673-123-9827","Doctor","5");
        dbh.addrecordStaff("Jas","Lee","doctor5","123-888-0987","Doctor","6");

        //Jiheui : Cashiers
        dbh.addrecordStaff("giveMe","Money","Cashier","131-452-1412","Cashier","1");
        dbh.addrecordStaff("gav","vin","Cashier2","443-235-9876","Cashier","2");
        dbh.addrecordStaff("Mon","ney","Cashier3","425-377-8885","Cashier","3");
        dbh.addrecordStaff("Pay","ney","Cashier4","875-156-3678","Cashier","4");
        dbh.addrecordStaff("Doll","Lar","Cashier5","964-258-7832","Cashier","5");

        //Joel: Added admin staff
        //Jiheui : Admins
        dbh.addrecordStaff("admin", "admin", "Admin", "563-457-1837", "Admin", "1");
        dbh.addrecordStaff("a", "min", "Admin2", "291-634-8839", "Admin", "0");
        dbh.addrecordStaff("ad", "adn", "Admin3", "563-123-1249", "Admin", "7");
        dbh.addrecordStaff("ve", "asdn", "Admin4", "525-368-7941", "Admin", "5");
        dbh.addrecordStaff("hej", "dfdh", "Admin5", "945-125-8246", "Admin", "3");

        //Jiheui : Comments
        dbh.addrecordComment("em","email","I am dying",null);
        dbh.addrecordComment("em","email1234","I am dying20","zxzxz");
        dbh.addrecordComment("em","email123","I am dying30",null);
        dbh.addrecordComment("doctor2","email12","I am dying corona 30","call 911");
        dbh.addrecordComment("doctor1","email","I am dying ahhh","sorry to hear that");
        dbh.addrecordComment("doctor2","Patient1","I am dying20","zxzxz");
        dbh.addrecordComment("doctor3","Patient1","I am dying30",null);
        dbh.addrecordComment("doctor3","Patient2","I am dying30",null);
        dbh.addrecordComment("doctor4","Patient3","I am dying30",null);
        dbh.addrecordComment("doctor5","Patient4","I am dying30",null);

        //Jiheui : Transactions
        dbh.addrecordTransaction("em",135,"email","October 1, 2020");
        dbh.addrecordTransaction("em",75,"email1234","March 21, 2020");
        dbh.addrecordTransaction("em",270,"email123","March 17, 2020");
        dbh.addrecordTransaction("doctor2",500,"email12","April 4, 2020");





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  userEmail = emailEdit.getText().toString();
              //  userPass = passEdit.getText().toString();
                //Jiheui: I changed this to Activity1 (Patient Profile page)
                Intent i = new Intent(Activity_2.this,Activity_1.class);
                i.putExtra("isAdminC", "no");
             //   i.putExtra("email",userEmail);
              //  i.putExtra("password", userPass);
                startActivity(i);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = emailEdit.getText().toString();
                userPass = passEdit.getText().toString();
                String correctPass = dbh.getPasswordForLogin(userEmail);
                if (userPass.equals(correctPass)){
                    String patientName = dbh.getdataPatient(userEmail,1);

                    if (!patientName.equals("")) {
                                         //Jiheui: I changed this to Activity 9 (Patient Profile page)
                        Intent i = new Intent(Activity_2.this, Activity_9.class);
                        i.putExtra("email", userEmail);
                        startActivity(i);
                    }
                    else{
                        if (dbh.getdataStaff(userEmail,4).equals("Doctor")) {
                            Intent iD = new Intent(Activity_2.this, Activity_3.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        else if (dbh.getdataStaff(userEmail,4).equals("Cashier")) {
                            Intent iD = new Intent(Activity_2.this, Activity_4.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                        else if (dbh.getdataStaff(userEmail,4).equals("Admin")) {
                            Intent iD = new Intent(Activity_2.this, Activity_5.class);
                            iD.putExtra("email", userEmail);
                            startActivity(iD);
                        }
                    }
                }
                else{
                    Toast.makeText(Activity_2.this,"Wrong Email or Password! Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
