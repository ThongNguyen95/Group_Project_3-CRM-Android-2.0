package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Model.AllUsers;
import Model.Owner;

import static Controller.IO.writeToFile;

public class AddAppointmentActivity extends AppCompatActivity {

    Owner owner;
    AllUsers allUsers;
    private EditText mm,dd,yy,custId;
    private int month,day,year;
    private String customerId;
    private Calendar cal;

    private Button cretApt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        // get owner
        final Intent intent = getIntent();
        String ownerId = intent.getStringExtra("ownerid");
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        owner = allUsers.getOwnerBasedOnID(ownerId);

        // get user input
        mm = findViewById(R.id.mm);
        dd = findViewById(R.id.dd);
        yy = findViewById(R.id.yy);
        custId = findViewById(R.id.editText5);

        // create an appointment
        cretApt = findViewById(R.id.crtapt);
        cretApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set up day, month, year in gregoriancalendar format
                month = Integer.valueOf(mm.getText().toString());
                day = Integer.valueOf(dd.getText().toString());
                year = Integer.valueOf(yy.getText().toString());
                customerId = custId.getText().toString();

                cal = new GregorianCalendar(year,month,day);
                // add into appoint list that inside of owner class
                owner.addAppointment(cal,allUsers.getCustomerBasedOnID(customerId));

                // save the data
                try {
                    writeToFile(AddAppointmentActivity.this,allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }
                intent.putExtra("AllUsers", allUsers);

                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}
