package com.example.reunion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu,menu);
        MenuItem item= menu.findItem(R.id.room_picker_actions);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        switch(id){
            case R.id.room_picker_actions:
                showRoomDialog();
                break;
            case R.id.date_picker_actions:
                showDateDialog();
                break;
        }

        return true;
    }



    public static void setAutoCompleteTextView(AutoCompleteTextView room, Context context, String[] roomNumbers){
        ArrayAdapter<String> roomAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,roomNumbers);
        room.setAdapter(roomAdapter);}

    public static void addmeeting(String meetingRoom,String meetingTime,String meetingTopic, List<String> participants,String meetingDate){

        mMeetings.add(new Reunion(meetingRoom,meetingTopic, meetingTime,participants,meetingDate));
        recyclerViewAdapter.notifyDataSetChanged();

    }

    public void showRoomDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("AlertDialog");
        String[] items = getResources().getStringArray(R.array.room_numbers);

        alertDialog.setTitle("Choose Room");
        alertDialog.setSingleChoiceItems(items,-1,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int checkedItem) {
                        filterByRoom(items[checkedItem]);
                        dialogInterface.dismiss();

                    }
                });



        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    public void showDateDialog(){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,day);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                        java.util.Locale.getDefault());
                filterByDate(dateFormat.format(calendar.getTime ()));

            }

        };

        new DatePickerDialog(this, date, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


            }

    private void filterByRoom(String text) {
        ArrayList<Reunion> filteredlist = new ArrayList<Reunion>();

        for (Reunion item : mMeetings) {
            if (item.getMeetingRoom().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            recyclerViewAdapter.filterList(filteredlist);
        }
    }


    private void filterByDate(String text) {

        ArrayList<Reunion> filteredlist = new ArrayList<Reunion>();
        for (Reunion item : mMeetings) {
            if (item.getMeetingDate().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            recyclerViewAdapter.filterList(filteredlist);
        }
    }


}