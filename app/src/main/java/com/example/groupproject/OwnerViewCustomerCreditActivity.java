package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import Model.AllUsers;
import Model.Owner;

public class OwnerViewCustomerCreditActivity extends AppCompatActivity {
    DisplayCustomerListAdapter adapter;
    Owner owner;
    AllUsers allUsers;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view_customer_credit);

        // get owner
        intent = getIntent();
        String ownerId = intent.getStringExtra("id");
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        owner = allUsers.getOwnerBasedOnID(ownerId);

        // set up for recycler view
        RecyclerView recyclerView = findViewById(R.id.cust_credit);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayCustomerListAdapter(this,owner);
        recyclerView.setAdapter(adapter);

        // Back to Previous Menu
        Button back = findViewById(R.id.but_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
