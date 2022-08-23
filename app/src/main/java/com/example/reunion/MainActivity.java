package com.example.reunion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity {

    public Button mAddButton;
    private int REQUEST_CODE_ADDMEETING=50;
    private String SHARED_PREF_MEETING_INFO="SHARED_PREF_MEETING_INFO";
    private String SHARED_PREF_MEETING_INFO_SUBJECT="SHARED_PREF_MEETING_INFO_SUBJECT";
    public ArrayList<String> mdescription;
    public List<String> mEmails;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Reunion> mMeetings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddButton = findViewById(R.id.Add_Button);

        recyclerView=findViewById(R.id.meeting_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //connecting the Recyclerview
        mMeetings=new ArrayList<Reunion>();
        recyclerViewAdapter=new RecyclerViewAdapter(this,mMeetings);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


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
            String meetingTime= data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_TIME);
            String meetingRoom=data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_ROOM);
            String emails =data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_PARTICIPANTS);
            //add data to the recyclerview
            mMeetings.add(new Reunion(meetingRoom,meetingTime,meetingSubject));

        }


    }



}