package com.example.reunion;

import static com.example.reunion.ListMeetingFragment.mAddButton;
import static com.example.reunion.ListMeetingFragment.mMeetings;
import static com.example.reunion.ListMeetingFragment.recyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Reunion;


public class ListMeetingActivity extends AppCompatActivity  {

   public static ArrayList<Reunion> filteredlist;
    public static FragmentTransaction fragmentTransaction;
    public static FragmentTransaction fragmentTransaction2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_meeting_activity);
       fragmentTransaction=getSupportFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.container,new ListMeetingFragment(),null).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);

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


    public void showRoomDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListMeetingActivity.this);
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
        filteredlist = new ArrayList<Reunion>();

        for (Reunion item : mMeetings) {
            if (item.getMeetingRoom().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            openFilterFragment();

        }
    }


    public void filterByDate(String text) {

        filteredlist = new ArrayList<Reunion>();
        for (Reunion item : mMeetings) {
            if (item.getMeetingDate().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);

            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            openFilterFragment();
        }
    }



    public void openFilterFragment(){
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new ItemFragment(),null).addToBackStack(null).commit();
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount()==0){
            this.finish();
        }else {
            getFragmentManager().popBackStack();
        }
    }


}