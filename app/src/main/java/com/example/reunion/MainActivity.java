package com.example.reunion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity {


    public Button mAddButton;
    private static final int REQUEST_CODE_ADDMEETING=50;
    RecyclerView recyclerView;
    static RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Reunion> mMeetings;
    public ArrayList<ArrayList<Reunion>> mMeeting;
    List<String> test;
    public String meetingSubject;
    public String meetingTime;
    public String meetingRoom;
    TextView mTextView;
    EditText s;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = findViewById(R.id.add_button);
        mTextView=findViewById(R.id.textview);
        s=findViewById(R.id.Meeting_Subject);

        recyclerView=findViewById(R.id.meeting_view);
        recyclerView.setHasFixedSize(true);


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mMeetings=new ArrayList<Reunion>();
        mMeeting=new ArrayList<ArrayList<Reunion>>();
        recyclerViewAdapter=new RecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(recyclerViewAdapter);


        //get data from AddMeetingActivity
        //Add them to the meeting list
        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();

        mMeetings.add(new Reunion("m,ll","nkk,","ujknj"));

        if(bundle != null){
            Bundle extras=intent.getExtras();
            meetingSubject=extras.getString("Subject");
            meetingTime=extras.getString("Time");
            meetingRoom= extras.getString("Room");

        }




        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // The user just clicked
                Intent addMeetingActivityIntent=new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(addMeetingActivityIntent);
            }
        });

    }

    public static void addItem(String meetingSubject, String meetingTime, String meetingRoom) {
        mMeetings.add(new Reunion(meetingSubject,meetingTime,meetingRoom));
        recyclerViewAdapter.notifyItemInserted(mMeetings.size() - 1);
    }

    public void updateList(List<Reunion>mMeetings){


    }





}



