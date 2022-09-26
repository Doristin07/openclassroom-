package com.example.reunion;

import static com.example.reunion.ListMeetingActivity.filterByRoom;
import static com.example.reunion.ListMeetingActivity.mMeetings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static model.Reunion.mColor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.mockito.Mockito.*;




import model.Reunion;

public class AllFunctionalitiesUnitTest {


    public Reunion newMeeting;
    private RecyclerViewAdapter adapter;
    @Before
    public void setup(){

    }


    @Test
    public void createNewMeeting() {
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        mMeetings.add(newMeeting);
        assertTrue(mMeetings.contains(newMeeting));
    }

    @Test
    public void removeMeeting() {
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        mMeetings.add(newMeeting);
        assertTrue(mMeetings.contains(newMeeting));
        adapter.removeMeeting(newMeeting,adapter);
        assertFalse(mMeetings.contains(newMeeting));
    }

    @Test
    public void filterMeetingByRoom() {
        mMeetings=new ArrayList<>();
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
       newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        mMeetings.add(newMeeting);
        for(Reunion meeting : mMeetings){
            String room =meeting.getMeetingRoom() ;
                assertEquals(meeting, filterByRoom(room));

            }
    }

    @Test
    public void filterMeetingByDate() {
        int year = 1970, month = 0, dayOfMonth = 1;
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
        for (Reunion meeting : mMeetings) {
            if (dateFormat.format(date)==meeting.getMeetingDate()) {
                assertEquals(ListMeetingActivity.filterByDate(dateFormat.format(date)), meeting.getMeetingDate());
            }else {
                assertNotEquals(ListMeetingActivity.filterByDate(dateFormat.format(date)), meeting.getMeetingDate());
            }

        }
    }
}

