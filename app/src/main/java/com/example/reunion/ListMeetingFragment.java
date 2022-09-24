package com.example.reunion;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import model.Reunion;

public class ListMeetingFragment extends Fragment {


    public static FloatingActionButton mAddButton;
    RecyclerView recyclerView;
    static RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    public static String[] roomNumbers;
    public static ArrayList<Reunion> mMeetings;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.list_meeting_fragment, container, false);

        mAddButton =view.findViewById(R.id.add_button);

        recyclerView=view.findViewById(R.id.meeting_view);
        recyclerView.setHasFixedSize(true);


        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mMeetings=new ArrayList<Reunion>();
        recyclerViewAdapter=new RecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(recyclerViewAdapter);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context= getContext();
                View view=getLayoutInflater().inflate(R.layout.dialog_design,null

                );
               roomNumbers=getResources().getStringArray(R.array.room_numbers);
                AddMeetingDialog.showDialog(view,context,roomNumbers);
            }
        });

        return view;
    }
    public static void setAutoCompleteTextView(AutoCompleteTextView room, Context context, String[] roomNumbers){
        ArrayAdapter<String> roomAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,roomNumbers);
        room.setAdapter(roomAdapter);}

    public static void addmeeting(String meetingRoom, String meetingTopic, String meetingTime, List<String> participants, String meetingDate){

        mMeetings.add(new Reunion(meetingRoom,meetingTime,meetingTopic,participants,meetingDate));
        recyclerViewAdapter.notifyDataSetChanged();


    }


}
