package com.example.reunion;


import static com.example.reunion.ListMeetingActivity.mMeetings;
import static com.example.reunion.ListMeetingActivity.setAutoCompleteTextView;

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
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import model.Reunion;

public class AddMeetingDialog {


    public static ChipGroup chipGroup;
    public static Chip chip;


    public static void showDialog(View v, Context context, String[] roomNumbers) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //Inflate the dialog_design
        View view = v;

        EditText topicEditText, roomEditText, timeEditText, emailEditText, dateEditText;
        topicEditText = view.findViewById(R.id.topic);
        roomEditText = view.findViewById(R.id.room);
        timeEditText = view.findViewById(R.id.time);
        emailEditText = view.findViewById(R.id.emails);
        dateEditText = view.findViewById(R.id.date);
        AutoCompleteTextView room = view.findViewById(R.id.room);
        chipGroup = view.findViewById(R.id.chip_group);

        setDialogButtons(context.getApplicationContext(), alertDialogBuilder, roomEditText, timeEditText, topicEditText, dateEditText);

        timePicker(timeEditText, context);
        datePicker(dateEditText, context);
        setAutoCompleteTextView(room, context, roomNumbers);
        room.setHint("Room Number");

        //set the view to dialog
        alertDialogBuilder.setView(view);
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        disableButton(roomEditText, topicEditText, timeEditText, dateEditText,
                emailEditText, dialog, context);
        addChip(emailEditText, context);


    }


    public static void setDialogButtons(Context cont, AlertDialog.Builder alertDialogBuilder, EditText roomEditText,
                                        EditText topicEditText, EditText timeEditText, EditText dateEditText) {

        //set Dialog Buttons
        alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //get data
                String meetingRoom = roomEditText.getText().toString();
                String meetingTime = timeEditText.getText().toString();
                String meetingTopic = topicEditText.getText().toString();
                String meetingDate = dateEditText.getText().toString();

                List<String> participants = new ArrayList<>();
                for (int j = 0; j < chipGroup.getChildCount(); j++) {
                    chip = (Chip) chipGroup.getChildAt(j);
                    Log.i("outside if", i + "chip=" + chip.getText().toString());
                    participants.add(chip.getText().toString());
                }
                if (meetingRoom.length() == 0 || meetingDate.length() == 0 || meetingTime
                        .length() == 0 || meetingTopic.length() == 0 || chipGroup.getChildCount() < 1) {
                    Toast.makeText(cont, "Incomplete Meeting Info!"+"     " +
                            "Meeting not added!", Toast.LENGTH_SHORT).show();
                } else {
                    ListMeetingActivity.addmeeting(meetingRoom, meetingTime, meetingTopic, participants, meetingDate);
                        Toast.makeText(cont, "New meeting added!", Toast.LENGTH_SHORT).show();
                    }
                }

        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(cont, "Adding a meeting has been abort", Toast.LENGTH_SHORT).show();
            }

        });

    }


    public static void timePicker(EditText timeEditText, Context cont2) {
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
                        timeEditText.setText(String.format("%02d:%02d", hourOfDay, minutes) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();


            }
        });
    }

    public static void datePicker(EditText dateEditText, Context cont3) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);


                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                        java.util.Locale.getDefault());
                dateEditText.setText(dateFormat.format(calendar.getTime()));

            }


        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(cont3, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                        datePickerDialog.show();

            }
        });

    }


    public static void addChip(EditText emailEdit, Context cont4) {

        emailEdit.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                String email = Objects.requireNonNull(emailEdit.getText().toString().trim());
                chip = new Chip(cont4);
                chip.setText(email);
                chip.setCloseIconVisible(true);
                String emailpattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
                if (!email.matches(emailpattern) && !email.isEmpty()) {
                    emailEdit.setError("Invalid Email!");
                } else {
                    chipGroup.addView(chip);
                    emailEdit.setText("");
                }

            }
            chip.setCheckedIconResource(R.drawable.ic_baseline_close_24);
            chip.setCloseIconEnabled(true);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chipGroup.removeView(chip);
                }
            });

            return false;

        });

    }

    public static void disableButton(EditText edt1, EditText edt2, EditText edt3, EditText edt4, EditText edt5, AlertDialog dialog, Context context) {
        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString()
                        .length() == 0 || edt4.getText().toString().length() == 0 || chipGroup.getChildCount() < 1) {

                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }

                checkRoom(edt1, edt3, edt4, context, dialog);

            }

        };
        edt1.addTextChangedListener(watcher);
        edt2.addTextChangedListener(watcher);
        edt3.addTextChangedListener(watcher);
        edt4.addTextChangedListener(watcher);
        edt5.addTextChangedListener(watcher);


    }

    public static void checkRoom(EditText edt1, EditText edt2, EditText edt3, Context context, AlertDialog dialog) {
        for (Reunion item : mMeetings) {
            if (edt1.getText().toString().equals(item.getMeetingRoom()) && edt2.getText().toString().equals(item.getMeetingTime()) &&
                    edt3.getText().toString().equals(item.getMeetingDate())) {
                Toast.makeText(context, "This meeting room is already booked", Toast.LENGTH_SHORT).show();
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                edt1.setError("Room already booked");

            } else {
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        }
    }
}


