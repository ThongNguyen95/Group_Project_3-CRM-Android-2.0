package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import Model.AllUsers;
import Model.Customer;
import Model.Owner;
import Model.RetObject;

import static Controller.IO.writeToFile;

public class SignUp extends AppCompatActivity {
    AllUsers allUsers;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize the sign up
        final Intent intent = getIntent();
        allUsers = (AllUsers)intent.getSerializableExtra("AllUsers");
        final Context context = this;

        // Get data from user input
        final RadioGroup radGroup = findViewById(R.id.select_user);
        final RadioButton radOwner = findViewById(R.id.radOwner);
        final RadioButton radCust = findViewById(R.id.radCustomer);

        final EditText editName = findViewById(R.id.edit_name);
        final EditText editID = findViewById(R.id.edit_id);
        final EditText editPW = findViewById(R.id.edit_pw);
        final EditText editAssociate = findViewById(R.id.edit_associate);
        final EditText editAns = findViewById(R.id.edit_sitekey_answer);

        spinner = findViewById(R.id.security_spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.SiteKey_Challenge_Question,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(staticAdapter);

        //confirm button
        Button butConfirm = findViewById(R.id.confirm);
        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getEditableText().toString();
                String id = editID.getEditableText().toString();
                String pw = editPW.getEditableText().toString();
                String as = editAssociate.getEditableText().toString();
                String quiz = spinner.getSelectedItem().toString();
                String ans = editAns.getEditableText().toString();

                //Check the length of input
                RetObject retValue = checkLength(name, id, pw, ans);
                if (retValue.getBool()) {
                    //Reset retValue
                    retValue.setBool(false);
                    // Check selected radio button
                    int selected = radGroup.getCheckedRadioButtonId();
                    if (selected == radOwner.getId()) {
                        retValue = signUpAsOwner(name, id, pw, quiz, ans);
                    } else if (selected == radCust.getId()) {
                        retValue = signUpAsCustomer(name, id, pw, as, quiz, ans);
                    } else {
                        retValue = new RetObject();
                        retValue.setMsg("Please select your account type at the top.");
                    }
                }
                //After processing the sign up data
                Toast.makeText(SignUp.this, retValue.getMsg(), Toast.LENGTH_SHORT).show();
                if (retValue.getBool() == true) {
                    // Save data to file
                    try {
                        writeToFile(context,allUsers);
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    // Send data back to MainActivity
                    intent.putExtra("AllUsers", allUsers);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        //cancel button
        Button butCancel = findViewById(R.id.cancel);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected RetObject checkLength(String _name, String _id, String _pw, String _ans) {
        RetObject ret = new RetObject();
        if (_name.length() < 4) {
            ret.setMsg("Name must contain at least 4 characters");
        } else if (_id.length() < 4) {
            ret.setMsg("ID must contain at least 4 characters");
        } else if (_pw.length() < 4) {
            ret.setMsg("Password must contain at least 4 characters");
        } else if (_ans.length() < 4) {
            ret.setMsg("Answer must contain at least 4 characters");
        } else {
            ret.setBool(true);
        }
        return ret;
    }

    protected RetObject signUpAsOwner(String _name, String _id, String _pw,String _quiz,String _ans) {
        //Check if business already existed
        RetObject ret = new RetObject();
        RetObject temp = allUsers.idExisted(_id);
        //Check ID
        if (temp.getBool()) {
            ret.setMsg(temp.getMsg());
            return ret;
        }
        //Check name
        temp = allUsers.businessExisted(_name);
        if (!temp.getBool()) {
            allUsers.addOwner(new Owner(_id,_pw,_name, 0,_quiz,_ans));
            ret.setBool(true);
            ret.setMsg("Sign up successfully!");
        } else {
            ret.setMsg(temp.getMsg());
        }
        return ret;
    }

    protected RetObject signUpAsCustomer(String _name, String _id, String _pw, String _aName,String _quiz,String _ans) {
        //Check if Customer already existed
        RetObject ret = new RetObject();
        RetObject temp = allUsers.idExisted(_id);
        //Check ID
        if (temp.getBool()) {
            ret.setMsg(temp.getMsg());
            return ret;
        }
        //Check name
        temp = allUsers.customerExisted(_name);
        if (!temp.getBool()) {
            Owner owner = allUsers.getOwnerBasedOnName(_aName);
            if (owner == null) {
                ret.setMsg("Could not find associated company for this account. Try again!");
                return ret;
            } else {
                allUsers.addCustomer(new Customer(_id, _pw, _name, owner,_quiz,_ans));
                ret.setBool(true);
                ret.setMsg("Sign up successfully!");
            }
        } else {
            ret.setMsg(temp.getMsg());
        }
        return ret;
    }

    //Methods for JUnit Testing
    public void allUsersInIt() {
        allUsers = new AllUsers();
    }
    public AllUsers getAllUsers() {
        return allUsers;
    }
}
