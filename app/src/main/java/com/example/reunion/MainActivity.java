package com.example.reunion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity {
    public TextView mGreeting;
    public Button mAddButton;
    private List<Reunion> meetings;
    private int REQUEST_CODE_ADDMEETING=50;
    private String SHARED_PREF_MEETING_INFO="SHARED_PREF_MEETING_INFO";
    private String SHARED_PREF_MEETING_INFO_SUBJECT="SHARED_PREF_MEETING_INFO_SUBJECT";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreeting = findViewById(R.id.greeting);
        mAddButton = findViewById(R.id.Add_Button);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                Intent addMeetingActivityIntent=new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivityForResult(addMeetingActivityIntent, REQUEST_CODE_ADDMEETING);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE_ADDMEETING == requestCode && RESULT_OK == resultCode && data != null) {
            String meetingSubject = data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_SUBJECT);
            String meetingPlace=data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_PLACE);
            String participants=data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_PARTICIPANTS);

            meetings.add(participants);









        }

    }


        }




