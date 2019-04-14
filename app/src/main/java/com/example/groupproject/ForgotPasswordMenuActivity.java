package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;


public class ForgotPasswordMenuActivity extends AppCompatActivity {

    private EditText fusername;
    private Button findbutton;
    private AllUsers allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_menu);
        final Intent intent = getIntent();
        allUsers = (AllUsers)intent.getSerializableExtra("AllUsers");
        fusername = findViewById(R.id.forgot_password_username);
        findbutton = findViewById(R.id.find_password);

        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finduserid = fusername.getText().toString();
                Owner owner = allUsers.getOwnerBasedOnID(finduserid);
                Customer customer = allUsers.getCustomerBasedOnID(finduserid);
                if (owner != null && customer == null)
                {
                    //for owner
                    //getting user SiteKey Challenege question
                    String retQuiz = allUsers.ForgotOwnerPassword(finduserid);
                    //Toast.makeText(getBaseContext(),retQuiz,Toast.LENGTH_SHORT).show();
                    //passing Question, account information and username to password recovery activity
                    Intent intent = new Intent(ForgotPasswordMenuActivity.this, PasswordRecoveryActivity.class);
                    intent.putExtra("Question",retQuiz);
                    intent.putExtra("AllUsers",allUsers);
                    intent.putExtra("Username",finduserid);
                    startActivity(intent);

                } else if (customer != null && owner == null)
                {
                    //for customer
                    //getting user Sitekey Challenge question
                    //Passing Question, account information and username to password recovery activity
                    String retQuiz = allUsers.ForgotCustomerPassword(finduserid);
                    Intent intent = new Intent(ForgotPasswordMenuActivity.this, PasswordRecoveryActivity.class);
                    intent.putExtra("Question",retQuiz);
                    intent.putExtra("AllUsers",allUsers);
                    intent.putExtra("Username",finduserid);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getBaseContext(),"wrong user name or haven't sing up yet!",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
