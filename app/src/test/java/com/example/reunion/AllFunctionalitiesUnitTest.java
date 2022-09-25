package com.example.reunion;

import static com.example.reunion.ListMeetingActivity.mMeetings;
import static com.example.reunion.ListMeetingActivity.roomNumbers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockio.*;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Reunion;

public class AllFunctionalitiesUnitTest {


    public Reunion newMeeting;

    @mock


    @Test
    public void createNewMeeting() {
        List<Reunion> meetings = mMeetings;
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
        newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        meetings.add(newMeeting);
        assertTrue(meetings.contains(newMeeting));
    }


    @Test
    public void filterMeetingByRoom() {
        ArrayList<Reunion> meetings;
       meetings=mMeetings;
        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");
       newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,"06/07/22");
        meetings.add(newMeeting);
        for(Reunion meeting : meetings){
            for (String room : roomNumbers){
                if (room == roomNumbers[0]){
                    assertEquals(ListMeetingActivity.filterByRoom(room), meeting.getMeetingRoom());
                }else{
                    assertNotEquals(ListMeetingActivity.filterByRoom(room), meeting);
                }
            }
        }
    }

    @Test
    public void filterMeetingByDate() {
        int year = 1970, month = 0, dayOfMonth = 1;
        List<Reunion> meetings = mMeetings;

        List<String> emailTest = new ArrayList<>();
        emailTest.add("sam@gmail.com");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy",
                java.util.Locale.getDefault());
        Date date = calendar.getTime();

        newMeeting=new Reunion("Room 3" ,"Reunion test",   "12h 30", emailTest,dateFormat.format(date));
        meetings.add(newMeeting);
        for (Reunion meeting : meetings) {
            if (dateFormat.format(date)==meeting.getMeetingDate()) {
                assertEquals(ListMeetingActivity.filterByDate(dateFormat.format(date)), meeting.getMeetingDate());
            }else {
                assertNotEquals(ListMeetingActivity.filterByDate(dateFormat.format(date)), meeting.getMeetingDate());
            }

        }
    }
}

