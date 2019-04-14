package com.example.groupproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.AllUsers;
import Model.Owner;

public class DisplayCustomerListActivity extends AppCompatActivity implements DisplayCustomerListAdapter.ItemClickListener {
    DisplayCustomerListAdapter adapter;
    Owner owner;
    AllUsers allUsers;
    double amount;
    Intent intent;
    EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_customer_list);

        // get owner
        intent = getIntent();
        String ownerId = intent.getStringExtra("ownerid");
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        owner = allUsers.getOwnerBasedOnID(ownerId);

        // set up for recycler view
        RecyclerView recyclerView = findViewById(R.id.customerlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayCustomerListAdapter(this,owner);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, final int position) {
        // get prompts.xml view
        final Context context = this;
        result = findViewById(R.id.editTextResult);

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.popup, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                result.setText(userInput.getText());
                                amount = Double.parseDouble(result.getText().toString());
                                owner.sendCreditTo( adapter.getItem(position),amount );

                                intent.putExtra("AllUsers", allUsers);
                                intent.putExtra("OwnerID",owner.getID());
                                setResult(RESULT_OK, intent);
                                finish();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();


        }

}
