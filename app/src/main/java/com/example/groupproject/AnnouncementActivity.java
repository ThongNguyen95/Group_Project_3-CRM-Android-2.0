package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Model.Owner;

public class AnnouncementActivity extends AppCompatActivity {
    Owner owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        // get owner
        final Intent intent = getIntent();
        owner = (Owner) intent.getSerializableExtra("Owner");
        Toast.makeText(getBaseContext(),owner.getID(),Toast.LENGTH_SHORT).show();
        //Get today date
        Calendar today = new GregorianCalendar();
        int hour = today.get(Calendar.HOUR_OF_DAY);
        int min = today.get(Calendar.MINUTE);
        int day = today.get(Calendar.DAY_OF_MONTH);
        int month = today.get(Calendar.MONTH);
        int year = today.get(Calendar.YEAR);

        StringBuilder stringBuild = new StringBuilder();
        stringBuild.append("(");
        stringBuild.append(month + 1);
        stringBuild.append("/");
        stringBuild.append(day);
        stringBuild.append("/");
        stringBuild.append(year);
        stringBuild.append(" ");
        stringBuild.append(hour);
        stringBuild.append(":");
        stringBuild.append(min);
        stringBuild.append(")");
        final String date = stringBuild.toString();

        final TextView textDate = findViewById(R.id.text_date);
        textDate.append(" ");
        textDate.append(date);

        //Set message and confirm
        Button butAdd = findViewById(R.id.but_add);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editAnnounce = findViewById(R.id.edit_announce);
                String announcement = date + " " + editAnnounce.getEditableText().toString();

                owner.setAnnouncement(announcement);
                intent.putExtra("Owner", owner);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        //Cancel
        Button butCancel = findViewById(R.id.but_cancel);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
