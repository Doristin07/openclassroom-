package com.example.reunion;


import static com.example.reunion.ListMeetingActivity.mMeetings;
import static com.example.reunion.ListMeetingActivity.recyclerViewAdapter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito.*;




import model.Reunion;

public class MareuUnitTests {


    public Reunion newMeeting;
    private RecyclerViewAdapter adapter;


    @Test
    public void createNewMeeting() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        when(myActivity.addMeeting("Room 3" ,"Reunion test", "12h 30", emailTest,
                "06/07/22")).thenReturn(1);
    }

    @Test
    public void removeMeeting() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
       myActivity.addMeeting("Room 3" ,"Reunion test", "12h 30", emailTest,"06/07/22");

       for (Reunion item:mMeetings){
           mMeetings.remove(item);
           assertFalse(mMeetings.contains(item));
       }

    }

    @Test
    public void filterMeetingByRoom() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
       newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        mMeetings.add(newMeeting);
        String room;
        room=newMeeting.getMeetingRoom();
        assertEquals(0, myActivity.filterByRoom(room).size());

            }


    @Test
    public void filterMeetingByDate() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        int year = 2022, month = 12, dayOfMonth = 7;
        mMeetings=new ArrayList<>();

        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                java.util.Locale.getDefault());
        Date date = calendar.getTime();

        newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,dateFormat.format(date));
        mMeetings.add(newMeeting);
        String myDate=dateFormat.format(date);
        assertEquals(0, myActivity.filterByDate(myDate).size());

        }
    }


