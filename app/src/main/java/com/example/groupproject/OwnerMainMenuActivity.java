package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import Model.AllUsers;
import Model.Owner;
import static Controller.IO.writeToFile;


public class OwnerMainMenuActivity extends AppCompatActivity {
    private static final int ZERO_ACTIVITY_REQUEST_CODE = 0;
    private static final int APPT_ACTIVITY_REQUEST_CODE = 1;
    private static final int ANNOUNCE_ACTIVITY_REQUEST_CODE = 2;
    private static final int CREDIT_ACTIVITY_REQUEST_CODE = 3;
    AllUsers allUsers;
    String ownerID;
    Owner owner;

    //Toggles for display
    boolean dName;
    boolean dCredit;
    TextView textName;
    TextView textCredit;

    //Announcement
    TextView textAnnounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);

        final Intent intent = getIntent();
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        ownerID = intent.getStringExtra("ownerID");
        owner = allUsers.getOwnerBasedOnID(ownerID);

        //Display Info
        dName = owner.getdName();
        dCredit = owner.getdCredit();
        textName = findViewById(R.id.owner_name);
        textCredit = findViewById(R.id.owner_credits);
        if (dName) {
            textName.setText(owner.getCompanyName());
        }
        if (dCredit) {
            textCredit.setText(Double.toString(owner.getCredit()));
        }

        //Display Announcement
        textAnnounce = findViewById(R.id.owner_menu_announcement_content);
        textAnnounce.setText(owner.getAnnouncement());

        //manage credits
        Button butCredits = findViewById(R.id.manage_credits);
        butCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, CreditActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                intent.putExtra("id", owner.getID());
                intent.putExtra("AllUsers", allUsers);
                startActivityForResult(intent, CREDIT_ACTIVITY_REQUEST_CODE);
            }
        });


        //Setting button
        Button butSettings = findViewById(R.id.setting_button);
        butSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, SettingsMenu.class);
                intent.putExtra("dName", dName);
                intent.putExtra("dCredit", dCredit);
                startActivityForResult(intent, ZERO_ACTIVITY_REQUEST_CODE);
            }
        });

        //Announcement button
        Button butAnnounce = findViewById(R.id.add_announcement);
        butAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, AnnouncementActivity.class);
                intent.putExtra("AllUsers", allUsers);
                intent.putExtra("OwnerID", ownerID);
                startActivityForResult(intent, ANNOUNCE_ACTIVITY_REQUEST_CODE);
            }
        });

        //SignOut button
        Button butCancel = findViewById(R.id.sign_out_button);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send data back
                intent.putExtra("AllUsers", allUsers);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        // add appointment button
        Button addApts = findViewById(R.id.addapts);
        addApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, AddAppointmentActivity.class);
                intent.putExtra("ownerid", owner.getID());
                intent.putExtra("AllUsers", allUsers);
                startActivityForResult(intent, APPT_ACTIVITY_REQUEST_CODE);
            }
        });

        // view appointment button
        Button viewApts = findViewById(R.id.viewapts);
        viewApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, DisplayOwnerAppointmentListActivity.class);
                intent.putExtra("ownerid", owner.getID());
                intent.putExtra("AllUsers", allUsers);
                startActivity(intent);
            }
        });

        //manage account
        Button manAccount = findViewById(R.id.manage_account);
        manAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, ManageOwnerAccountActivity.class);
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

        if (requestCode == APPT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers) data.getSerializableExtra("AllUsers");
            }
        }
        if (requestCode == ZERO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Get data
                dName = data.getBooleanExtra("dName", false);
                dCredit = data.getBooleanExtra("dCredit", false);
                //Update and save owner
                owner.setdName(dName);
                owner.setdCredit(dCredit);

                // Save data to file
                Context context = OwnerMainMenuActivity.this;
                try {
                    writeToFile(context, allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }

                //Up date texts
                textName.setText("");
                textCredit.setText("");
                if (dName) {
                    textName.setText(owner.getCompanyName());
                }
                if (dCredit) {
                    textCredit.setText(Double.toString(owner.getCredit()));
                }
            }
        }

        if (requestCode == ANNOUNCE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers) data.getSerializableExtra("AllUsers");
                ownerID = data.getStringExtra("OwnerID");
                owner = allUsers.getOwnerBasedOnID(ownerID);
                textAnnounce.setText(owner.getAnnouncement());
                //Save the data
                try {
                    writeToFile(OwnerMainMenuActivity.this,allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }

        if (requestCode == CREDIT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers) data.getSerializableExtra("AllUsers");
                owner = allUsers.getOwnerBasedOnID(ownerID);
                //Save the data
                try {
                    writeToFile(OwnerMainMenuActivity.this,allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }

                String temp = Double.toString(owner.getCredit());
                Toast.makeText(OwnerMainMenuActivity.this,temp,Toast.LENGTH_LONG).show();
                //Reload data display
                if (dCredit) {
                    textCredit.setText(Double.toString(owner.getCredit()));
                }
            }
        }
    }

}


