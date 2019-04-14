package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import Model.AllUsers;
import Model.Customer;
import Model.Owner;

import static Controller.IO.writeToFile;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class PasswordRecoveryActivity extends AppCompatActivity {
    private Button confrimans;
    private TextView userquestions;
    private EditText userfinalanswer;
    private EditText SetNewpassword;
    private EditText Confirmnewpassword;
    private Button setnewpasswordforlogin;
    private LinearLayout tohidelayout;
    private AllUsers allUsers;
    private String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        final Intent intent = getIntent();

        //getting passing data from previous activity
        String KeySiteQuiz = (String)intent.getSerializableExtra("Question");
        allUsers = (AllUsers)intent.getSerializableExtra("AllUsers");
        userid = (String)intent.getSerializableExtra("Username");

        userquestions = findViewById(R.id.user_answer_password);
        userquestions.setText(KeySiteQuiz);

        userfinalanswer = findViewById(R.id.Sitekey_user_answer);

        confrimans = findViewById(R.id.get_password);

        //hide the set new password layout until user sucessfully answer his sitekey challenge question
        tohidelayout = findViewById(R.id.new_password_hide);
        tohidelayout.setVisibility(LinearLayout.GONE);

        SetNewpassword = findViewById(R.id.recovery_new_password);
        Confirmnewpassword = findViewById(R.id.recovery_confirm_password);
        setnewpasswordforlogin = findViewById(R.id.recovery_set_password);

        confrimans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkanswer = userfinalanswer.getText().toString();
                Owner owner = allUsers.getOwnerBasedOnID(userid);
                Customer customer = allUsers.getCustomerBasedOnID(userid);

                if (owner != null && customer == null)
                {
//                    //owner
//                    //check the answer of user sitekey answer
//                    boolean userpassword = allUsers.GetOwnerPassword(userid,checkanswer);
//                    //if user successfully answer sitekey question
//                    if(userpassword)
//                    {
//                        //display hiding layout and let the user to set new password
//                        tohidelayout.setVisibility(LinearLayout.VISIBLE);
//                        setnewpasswordforlogin.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String usernewpass = SetNewpassword.getText().toString();
//                                String userconfirmnewpass = Confirmnewpassword.getText().toString();
//                                boolean isnewpassset = allUsers.SetOwnerNewPassword(usernewpass,userconfirmnewpass,userid);
//                                if(isnewpassset)
//                                {
//                                    //write new password to the file
//                                    try {
//                                        writeToFile(context,allUsers);
//                                    } catch (IOException e) {
//                                        e.getStackTrace();
//                                    }
//                                    //as user change password, application force user to log out and send the user to main activity to signing in.
//                                    Intent i = new Intent(getBaseContext(), MainActivity.class);
//                                    i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(i);
//                                }
//                                else
//                                {
//                                    //won't let change password if user doesn't put same password, new password and confirm password
//                                    Toast.makeText(getBaseContext(),"Your New Password Doesn't match",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                    else
//                    {
//                        Toast.makeText(getBaseContext(),"Your SiteKey Challenge Answer is wrong",Toast.LENGTH_SHORT).show();
//                    }

                } else if (customer != null && owner == null)
                {
                    //for customer
//                    boolean userpassword = allUsers.GetCustomerPassword(userid,checkanswer);
//                    if(userpassword)
//                    {
//                        //display hiding layout and let the user to set new password
//                        tohidelayout.setVisibility(LinearLayout.VISIBLE);
//                        setnewpasswordforlogin.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String usernewpass = SetNewpassword.getText().toString();
//                                String userconfirmnewpass = Confirmnewpassword.getText().toString();
//                                boolean isnewpassset = allUsers.SetCustomerNewPassword(usernewpass,userconfirmnewpass,userid);
//                                if(isnewpassset)
//                                {
//                                    try {
//                                        writeToFile(context,allUsers);
//                                    } catch (IOException e) {
//                                        e.getStackTrace();
//                                    }
//                                    //as user change password, application force user to log out and send the user to main activity to signing in.
//                                    Intent i = new Intent(getBaseContext(), MainActivity.class);
//                                    i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(i);
//                                }
//                                else
//                                {
//                                    Toast.makeText(getBaseContext(),"Your New Password Doesn't match",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                    else
//                    {
//                        Toast.makeText(getBaseContext(),"Your SiteKey Challenge Answer is wrong",Toast.LENGTH_SHORT).show();
//                    }
                }
          }
       });


    }
}

