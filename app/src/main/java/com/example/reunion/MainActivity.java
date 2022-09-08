package com.example.reunion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity  {


    private static ChipGroup chipGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = findViewById(R.id.add_button);
        mTextView=findViewById(R.id.textview);

        recyclerView=findViewById(R.id.meeting_view);
        recyclerView.setHasFixedSize(true);


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mMeetings=new ArrayList<Reunion>();
        recyclerViewAdapter=new RecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(recyclerViewAdapter);
        Context context=MainActivity.this;

        if(mMeetings.size() == mMeetings.size() - 1){
            Toast.makeText(MainActivity.this, "New Meeting Added", Toast.LENGTH_SHORT).show();
        }

        //Delete items
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                mMeetings.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);

            }
        });


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=MainActivity.this;
                View view=getLayoutInflater().inflate(R.layout.dialog_design,null);
                Methods_class.showAlert(view,context);


            }
        });

    }


    public static void addmeeting(String meetingSubject, String meetingTime, String meetingRoom,List<String> participants){

        mMeetings.add(new Reunion(meetingSubject,meetingTime,meetingRoom, participants));
        recyclerViewAdapter.notifyDataSetChanged();

    }


    }