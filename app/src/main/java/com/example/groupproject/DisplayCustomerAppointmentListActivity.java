package com.example.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

public class DisplayCustomerAppointmentListActivity extends AppCompatActivity implements DisplayCustomerAppointmentListAdapter.ItemClickListener {

    DisplayCustomerAppointmentListAdapter adapter;
    Owner owner;
    Customer customer;
    AllUsers allUsers;
    public ArrayList<Calendar> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_customer_appointment_list);

        // getter
        String custID = getIntent().getStringExtra("customerID");
        allUsers = (AllUsers) getIntent().getSerializableExtra("AllUsers");
        customer = allUsers.getCustomerBasedOnID(custID);
        owner = customer.getBus();

        // make a new array list and add only appointment date that belong with the customer
        tempList = new ArrayList<>();
        String ownerID = customer.getBus().getID();
        int size = allUsers.getOwnerBasedOnID(ownerID).getAppointmentCustList().size();
        for(int i =0; i<size; i++){
            String allCustName = allUsers.getOwnerBasedOnID(ownerID).getAppointmentCustList().get(i).getCustomerName();
            String curCustName = customer.getCustomerName();
            if(allCustName.equals(curCustName)){
                tempList.add(allUsers.getOwnerBasedOnID(ownerID).getAppointmentDateList().get(i));
            }
        }

        // set up for recycler view
        RecyclerView recyclerView = findViewById(R.id.appointment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayCustomerAppointmentListAdapter(this,tempList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

    }
}
