package com.example.reunion;


import static com.example.reunion.ListMeetingActivity.sMeetings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import model.Reunion;

public class MareuUnitTests {


    private RecyclerViewAdapter adapter;


    @Test
    public void createNewMeeting() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        sMeetings =new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        when(myActivity.addMeeting("Room 3" ,"Reunion test", "12h 30", emailTest,
                "06/07/22")).thenReturn(1);
    }

    @Test
    public void removeMeeting() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        sMeetings =new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
       myActivity.addMeeting("Room 3" ,"Reunion test", "12h 30", emailTest,"06/07/22");

        int position=0;
       for (Reunion item: sMeetings){
           myActivity.deleteMeeting(adapter,position);
           assertFalse(sMeetings.contains(item));
       }

    }

    @Test
    public void filterMeetingByRoom() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        sMeetings =new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        myActivity.addMeeting("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        String room;

            room="Room 3";
            when(myActivity.filterByRoom(room)).thenReturn(1);



    }


    @Test
    public void filterMeetingByDate() {
        ListMeetingActivity myActivity=mock(ListMeetingActivity.class);
        int year = 2022, month = 12, dayOfMonth = 7;
        sMeetings =new ArrayList<>();

        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                java.util.Locale.getDefault());
        Date date = calendar.getTime();

        String myDate=dateFormat.format(date);
        myActivity.addMeeting("Room 3" ,"Reunion test",   "12h 30", emailTest,myDate);

        when(myActivity.filterByDate(myDate)).thenReturn(1);

    }



}


