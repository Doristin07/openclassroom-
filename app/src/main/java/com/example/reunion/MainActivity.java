package com.example.reunion;

import static com.example.reunion.AddMeetingActivity.BUNDLE_EXTRA_ROOM;
import static com.example.reunion.AddMeetingActivity.BUNDLE_EXTRA_SUBJECT;
import static com.example.reunion.AddMeetingActivity.BUNDLE_EXTRA_TIME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity {

    public Button mAddButton;
    private int REQUEST_CODE_ADDMEETING=50;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Reunion> mMeetings;
    List<String> test;
    public String meetingSubject;
    public String meetingTime;
    public String meetingRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddButton = findViewById(R.id.add_button);

        recyclerView=findViewById(R.id.meeting_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //connecting the Recyclerview
        mMeetings=new ArrayList<Reunion>();
        recyclerViewAdapter=new RecyclerViewAdapter(this,mMeetings);

        // add Default Data
        mMeetings.add(new Reunion("Room 3","7:45 PM","Money"));


        //add data to the recyclerview
        mMeetings.add(new Reunion(meetingRoom, meetingTime, meetingSubject));
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

    public static void addItem(String meetingRoom, String meetingTime, String meetingSubject) {

        //Failed method

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (REQUEST_CODE_ADDMEETING == requestCode && RESULT_OK == resultCode && data != null) {

                meetingSubject = data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_SUBJECT);
                meetingTime = data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_TIME);
                meetingRoom = data.getStringExtra(AddMeetingActivity.BUNDLE_EXTRA_ROOM);




            }



    }



}