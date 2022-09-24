package com.example.reunion;

import static com.example.reunion.ListMeetingFragment.mMeetings;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import model.Reunion;

public class LamzoneMeetingUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }



    @Test
    public void removeMeeting() {
        List<Reunion> meetings = service.getMeetings();
        Reunion meeting = service.getMeetings().get(0);
        assertTrue(meetings.contains(meetings));
        service.removeMeeting(meeting);
        assertFalse(meetings.contains(meeting));
    }



    @Test
    public void createMeeting() {
        Reunion newMeeting;
        newMeeting=ListMeetingFragment.addmeeting("Room 3" ,"12h 30",   "", "Reunion test", new Date(1586627538),  new ArrayList<>(Arrays.asList(service.getUsers().get(1), service.getUsers().get(3))));
        assertTrue(mMeetings.contains(service.getMeeting(22)));
    }



    @Test
    public void filterMeetingByRoomIdWithSuccess() {
        List<Reunion> meetings = ListMeetingActivity.filterByRoom(service.getMeetingRooms().get(0).getId());
        for(Reunion meeting : meetings){
            for (String room : ListMeetingFragment.roomNumbers){
                if (room == room[0]){
                    assertEquals(meeting.getLocation(), room);
                }else{
                    assertNotEquals(meeting.getLocation(), room);
                }
            }
        }
    }

    @Test
    public void filterMeetingByDateWithSuccess() {
        int year = 1970, month = 0, dayOfMonth = 1;
        List<Reunion> meetings = ListMeetingFragment.filterByDate(year, month, dayOfMonth);
        Calendar calendar = Calendar.getInstance(); Calendar calendar1 = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, 0,0); calendar1.set(year, month, dayOfMonth+1, 0, 0);
        Date date = calendar.getTime(); Date date1 = calendar1.getTime();
        for (Reunion meeting : mMeetings) {
            assertTrue(meeting.getMeetingDate().after(date));
            assertTrue(meeting.getDate().before(date1));
        }
    }
}

