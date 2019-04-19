package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import Model.AllUsers;
import Model.Customer;

import static Controller.IO.writeToFile;

public class CustomerMainMenuActivity extends AppCompatActivity {

    AllUsers allUsers;
    Customer customer;
    String customerID;
    private int SECOND_ACTIVITY_REQUEST_CODE = 2;
    private int VIEWCHANGE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main_menu);

        final Intent intent = getIntent();
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        customerID = intent.getStringExtra("customerID");
        customer = allUsers.getCustomerBasedOnID(customerID);
        final Context context = this;

        //Display customer and business name
        TextView custName = findViewById(R.id.customer_name);
        custName.setText(customer.getCustomerName());

        TextView ownerName = findViewById(R.id.owner_name);
        ownerName.setText("Customer of: " + customer.getBus().getCompanyName());


        TextView textView = findViewById(R.id.customer_credits);
        textView.setText(Double.toString(customer.getCredit()));

        //Display Owner's Announcement
        TextView textAnnounce = findViewById(R.id.customer_menu_announcement_content);
        textAnnounce.setText(customer.getAnnouncement());

        //manage credits
        Button butCredits = findViewById(R.id.manage_credits);
        butCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainMenuActivity.this, CreditActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                intent.putExtra("id", customerID);
                intent.putExtra("AllUsers", allUsers);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });


        //cancel button
        Button butCancel = findViewById(R.id.sign_out_button);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send data back to MainActivity
                intent.putExtra("AllUsers", allUsers);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        // view appointment
        Button viewApts = findViewById(R.id.viewapts);
        viewApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainMenuActivity.this, DisplayCustomerAppointmentListActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                intent.putExtra("customerID", customer.getID());
                intent.putExtra("AllUsers", allUsers);
                startActivityForResult(intent, VIEWCHANGE_ACTIVITY_REQUEST_CODE);
                //startActivity(intent);
            }
        });

        Button manAccount = findViewById(R.id.manage_customer_account);
        manAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainMenuActivity.this, ManageCustomerAccountActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                //intent.putExtra("id",owner.getID());
                intent.putExtra("AllUsers", allUsers);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers) data.getSerializableExtra("AllUsers");
                customer = allUsers.getCustomerBasedOnID(customerID);
                //Save the data
                try {
                    writeToFile(CustomerMainMenuActivity.this, allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }

                TextView textView = findViewById(R.id.customer_credits);
                textView.setText(Double.toString(customer.getCredit()));
            }
        }

        if (requestCode == VIEWCHANGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers) data.getSerializableExtra("AllUsers");

                // save the data
                try {
                    writeToFile(CustomerMainMenuActivity.this, allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }

            }
        }
    }
}



