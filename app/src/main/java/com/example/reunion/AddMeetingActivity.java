package com.example.reunion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.reunion.R;
import com.google.android.material.internal.TextWatcherAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import model.Reunion;

public class AddMeetingActivity extends AppCompatActivity {
    public static final String BUNDLE_EXTRA_SUBJECT = "BUNDLE_EXTRA_SUBJECT";
    public static final String BUNDLE_EXTRA_ROOM = "BUNDLE_EXTRA_ROOM";
    public static final String BUNDLE_EXTRA_TIME = "BUNDLE_EXTRA_TIME";
    public static final String BUNDLE_EXTRA_PARTICIPANTS = "BUNDLE_EXTRA_PARTICIPANTS";

    private EditText msubjectEditText;
    private EditText  mmeetingDateEditText;
    private EditText mparticipantsEditText;
    private Button msaveButton;
    private static final int REQUEST_CODE_ADDMEETING=50;
    public String mSubject;
    public String mparticipants;
    public EditText mmeetingTimeEditText;
    public Spinner roomNumber;
    public Boolean merror;
    private String emailInput;
    private String mroom;
    private String mtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        msubjectEditText=findViewById(R.id.Meeting_Subject);
        mmeetingDateEditText=findViewById(R.id.meeting_date);
        mparticipantsEditText=findViewById(R.id.Meeting_Participant);
        msaveButton=findViewById(R.id.Save_Button);
        mmeetingTimeEditText=findViewById(R.id.meeting_time);

        roomNumber=(Spinner)findViewById(R.id.room_spinner) ;


        //set an adapter to the spinner
        String[] roomNumbers=getResources().getStringArray(R.array.room_numbers);
        ArrayAdapter roomAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,roomNumbers);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomNumber.setAdapter(roomAdapter);

        mmeetingTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateEmail(mparticipantsEditText);

                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                            mmeetingTimeEditText.setText(String.format("%02d:%02d", hourOfDay, minutes)+" "+amPm);
                        }
                    }, currentHour, currentMinute, false);

                    timePickerDialog.show();


                }
        });


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
                mmeetingDateEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        mmeetingDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new DatePickerDialog(AddMeetingActivity.this, date, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


                msaveButton.setEnabled(false);
                msubjectEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        msaveButton.setEnabled(!s.toString().isEmpty());
                    }
                });



                msaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkEditText();


                        mroom = roomNumber.getSelectedItem().toString();
                        mSubject=msubjectEditText.getText().toString();
                        mtime=mmeetingTimeEditText.getText().toString();


                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SUBJECT, mSubject);
                        intent.putExtra(BUNDLE_EXTRA_ROOM, mroom);
                        intent.putExtra(BUNDLE_EXTRA_TIME,mtime);
                        setResult(Activity.RESULT_OK, intent);

                        // failed method
                        MainActivity.addItem(mroom,mtime,mSubject);

                        startActivity(new Intent(AddMeetingActivity.this,MainActivity.class));



                    }
                });



            }
    private boolean validateEmail(EditText mparticipantsEditText){
        emailInput=mparticipantsEditText.getText().toString();
        String email_pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern patterns = Pattern.compile(email_pattern, Pattern.CASE_INSENSITIVE);

        if(!emailInput.isEmpty() ){
            Toast.makeText(this,"Email is correct!",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this,"Email is incorrect!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }


          public void checkEditText() {
            if (TextUtils.isEmpty(msubjectEditText.getText().toString())){
            Toast.makeText(AddMeetingActivity.this,"Field cannot be empty",Toast.LENGTH_LONG);
            merror=true;

        }
    }
}


