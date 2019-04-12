package com.example.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import Model.AllUsers;
import Model.Owner;

public class DisplayCustomerListActivity extends AppCompatActivity implements DisplayCustomerListAdapter.ItemClickListener {
    DisplayCustomerListAdapter adapter;
    Owner owner;
    AllUsers allUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_customer_list);

        // get owner
        String ownerId = getIntent().getStringExtra("ownerid");
        allUsers = (AllUsers) getIntent().getSerializableExtra("AllUsers");
        owner = allUsers.getOwnerBasedOnID(ownerId);

        // set up for recycler view
        RecyclerView recyclerView = findViewById(R.id.customerlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayCustomerListAdapter(this,owner);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();    }


}
