package com.example.reunion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity  {


    private static ChipGroup chipGroup;
    public FloatingActionButton mAddButton;
    RecyclerView recyclerView;
    static RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Reunion> mMeetings;
    public ArrayList<ArrayList<Reunion>> mMeeting;
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
        mMeetings=new ArrayList<Reunion>();
        recyclerViewAdapter=new RecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(recyclerViewAdapter);




        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=MainActivity.this;
                View view=getLayoutInflater().inflate(R.layout.dialog_design,null);
                String[] roomNumbers=getResources().getStringArray(R.array.room_numbers);
                Methods_class.showDialog(view,context,roomNumbers);
                if(mMeetings.size() == mMeetings.size() - 1){
                    Toast.makeText(MainActivity.this, "New Meeting Added", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    public static void setAutoCompleteTextView(AutoCompleteTextView room,Context context,String[] roomNumbers){
        ArrayAdapter<String> roomAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,roomNumbers);
        room.setAdapter(roomAdapter);}

    public static void addmeeting(String meetingRoom,String meetingTime,String meetingTopic, List<String> participants){

        mMeetings.add(new Reunion(meetingRoom,meetingTopic, meetingTime,participants));
        recyclerViewAdapter.notifyDataSetChanged();

    }


    }