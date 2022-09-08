package com.example.reunion;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.constraintlayout.widget.ConstraintHelper;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Methods_class extends MainActivity{

    static Boolean error;
    public static ChipGroup chipGroup;
    public static Chip chip;

    public static void showAlert(View v, Context context){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);

        //Inflate the dialog_design
        View view= v;

        EditText topicEditText, roomEditText,timeEditText,emailEditText,dateEditText;
        topicEditText=view.findViewById(R.id.topic);
        roomEditText=view.findViewById(R.id.room);
        timeEditText=view.findViewById(R.id.time);
        emailEditText=view.findViewById(R.id.emails);
        dateEditText=view.findViewById(R.id.date);
        chipGroup=view.findViewById(R.id.chip_group);

        setDialogButtons(context.getApplicationContext(), alertDialogBuilder,roomEditText,timeEditText,topicEditText);

        timePicker(timeEditText,context);
        datePicker(dateEditText,context);
        addChip(emailEditText,context);

        //set the view to dialog
        alertDialogBuilder.setView(view);
        AlertDialog dialog =alertDialogBuilder.create();
        dialog.show();


        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        topicEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable s) {

                checkEmail(context.getApplicationContext(), topicEditText);

                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!s.toString().isEmpty());
                }

        });


    }

    public static void setDialogButtons(Context cont,AlertDialog.Builder alertDialogBuilder, EditText roomEditText,
                                        EditText topicEditText, EditText timeEditText){

        //set Dialog Buttons
        alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //get data
                String meetingRoom=roomEditText.getText().toString();
                String meetingTopic=topicEditText.getText().toString();
                String meetingTime=timeEditText.getText().toString();

                List<String>participants = new ArrayList<>();
                for(int j=0;j<chipGroup.getChildCount();j++){
                    chip= (Chip) chipGroup.getChildAt(j);
                    Log.i("outside if",i+"chip="+chip.getText().toString());
                    participants.add(chip.getText().toString());
                }
                MainActivity.addmeeting(meetingRoom,meetingTime,meetingTopic,participants);}
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });

    }
    public static void checkEmail(Context context,EditText edit){
        String email=edit.getText().toString().trim();
        String emailpattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if(!email.matches(emailpattern)){
            edit.setError("Invalid Email!");
        }

    }
    public static void timePicker(EditText timeEditText, Context cont2){
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(cont2, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
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
    }

    public static void datePicker(EditText dateEditText, Context cont3){
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                        java.util.Locale.getDefault());
                dateEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(cont3, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    public static void checkError(AlertDialog dialog, String edit1, String edit2, String edit3){
        if (edit1.isEmpty()||edit2.isEmpty()||edit3.isEmpty()){
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }else{
            ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        }

    }
    public static void addChip(EditText emailEdit,Context cont4){
        {
            String email = emailEdit.getText().toString();
            if (email.length() > 0) {
                char lastChar = email.charAt(email.length() - 1);

                if (lastChar == ' ' || lastChar == ',') {
                    email = email.substring(0, email.length() - 1);
                    email = email.trim();
                    chip.setCloseIconVisible(true);
                    if (!email.isEmpty()) {
                        chip = new Chip(cont4);
                        chip.setText(email);
                        chipGroup.addView(chip);
                        emailEdit.setText("");}
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(chip);
                        }
                    });

                }

            }
        }

    }

}

