package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import Model.AllUsers;
import Model.Owner;

import static Controller.IO.writeToFile;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DeleteOwnerAccountActivity extends AppCompatActivity {

    Owner owner;
    AllUsers allUsers;

    private EditText duserID,dpassword; // user input for signin
    private Button deleteconfirm; //button to delete password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_owner_account);

        final Context context = this;

        final Intent intent = getIntent();
        allUsers = (AllUsers) intent.getSerializableExtra("AllUsers");
        String ownerId = intent.getStringExtra("ownerid");
        owner = allUsers.getOwnerBasedOnID(ownerId);



        duserID = findViewById(R.id.delete_user_name);
        dpassword = findViewById(R.id.delete_password);
        deleteconfirm = findViewById(R.id.delete_confrim);

        deleteconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting string input from edit text
                String dsUserid = duserID.getText().toString();
                String dosPassword = dpassword.getText().toString();
                //check owner's customerlist
                boolean customernumber = owner.isCustomerEmpty();
                //check user account is successfully deleted
                if(!customernumber)
                {
                    Toast.makeText(getBaseContext(), "Can't delete your account because there is one or more customer", Toast.LENGTH_LONG).show();
                }
                else
                    {
                boolean isAccountDeleted = allUsers.DeleteOaccount(dsUserid,dosPassword);
                //if yes
                if(isAccountDeleted) {
                    //write the updated information to the file
                    try {
                        writeToFile(context,allUsers);
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    //as user is no longer have account, application force user to log out and send the user to main activity.
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    Toast.makeText(getBaseContext(),"Account Successfully deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //wrong password so account is not deleted.
                    Toast.makeText(getBaseContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                }

            }}
        });

    }
}
