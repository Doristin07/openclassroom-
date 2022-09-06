package com.example.reunion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Reunion;

public class MainActivity extends AppCompatActivity {


    public Button mAddButton;
    private static final int REQUEST_CODE_ADDMEETING=50;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<Reunion> mMeetings;
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
                showAlert();


            }
        });

    }


    public void showAlert(){
        AlertDialog dialog;
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);

        //Inflate the dialog_design
        View view=getLayoutInflater().inflate(R.layout.dialog_design,null);

        EditText topicEditText, roomEditText,timeEditText,emailEditText,dateEditText;
        topicEditText=view.findViewById(R.id.topic);
        roomEditText=view.findViewById(R.id.room);
        timeEditText=view.findViewById(R.id.time);
        emailEditText=view.findViewById(R.id.emails);
        dateEditText=view.findViewById(R.id.date);


        //Set Date picker Dialog
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,day);

                updateCalendar();

            }

            private void updateCalendar(){
                String pattern="MM/DD/YY";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                        java.util.Locale.getDefault());
                dateEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


        //set Time Picker Dialog
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                        String dateString = dateFormat.format(new Date()).toString();
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        timeEditText.setText(String.format("%02d:%02d", hourOfDay, minutes)+" "+amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });


        //set Dialog Buttons
        alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get data
                String meetingRoom=roomEditText.getText().toString();
                String meetingTopic=topicEditText.getText().toString();
                String meetingTime=timeEditText.getText().toString();
                String meetingParticipants=emailEditText.getText().toString();

                addmeeting(meetingRoom,meetingTime,meetingTopic);



            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //set the view to dialog
        alertDialogBuilder.setView(view);
        alertDialogBuilder.create().show();

    }

    public void addmeeting(String meetingSubject, String meetingTime, String meetingRoom){

        mMeetings.add(new Reunion(meetingSubject,meetingTime,meetingRoom));
        recyclerViewAdapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Meeting Added succesfully!", Toast.LENGTH_LONG).show();

    }

    public void deleteMeeting(){

    }





}



