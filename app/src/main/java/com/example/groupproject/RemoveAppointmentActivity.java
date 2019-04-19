package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

import static Controller.IO.writeToFile;

public class RemoveAppointmentActivity extends AppCompatActivity {
    Owner owner;
    Customer customer;
    AllUsers allUsers;

    Calendar tempCal;
    Customer tempCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_appointment);

        final Intent intent = getIntent();
        //String tempI = (String) intent.getSerializableExtra("index");
        String tempI = intent.getStringExtra("index");
        String custID = intent.getStringExtra("customerID");
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        customer = allUsers.getCustomerBasedOnID(custID);
        owner = customer.getBus();
        int index = Integer.valueOf(tempI);

        tempCal = owner.getAppointmentDateList().get(index);
        tempCust = owner.getAppointmentCustList().get(index);

        Button butYes = findViewById(R.id.butY);
        butYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempCal != null || tempCust != null){
                    owner.removeAppointment(tempCal,tempCust);

                    intent.putExtra("AllUsers",allUsers);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else {
                    Toast.makeText(RemoveAppointmentActivity.this, "not remove yet!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button butNo = findViewById(R.id.butN);
        butNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
    }
}
