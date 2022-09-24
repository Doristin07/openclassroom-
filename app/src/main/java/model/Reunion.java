package model;

import android.graphics.Color;

import java.util.List;
import java.util.Random;
import android.graphics.Color;

public class Reunion {

    private String mMeetingTime;
    private String mMeetingRoom;
    private String mMeetingSubject;
    private List<String> mparticipants;
    private String mMeetingDate;
    private static Random srandomColor = new Random();
    private Integer mColor;


    public Reunion(String meetingRoom, String meetingTime, String meetingSubject, List<String> participants, String MeetingDate) {
        this.mMeetingRoom = meetingRoom;
        this.mMeetingTime = meetingTime;
        this.mMeetingSubject = meetingSubject;
        this.mparticipants = participants;
        this.mMeetingDate=MeetingDate;
        // Generate random color
        mColor = Color.argb(
                srandomColor.nextInt(255),
                srandomColor.nextInt(255),
                srandomColor.nextInt(255),
                srandomColor.nextInt(255));

    }


    public String getMeetingTime() {
        return mMeetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        mMeetingTime = meetingTime;
    }

    public String getMeetingRoom() {
        return mMeetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        mMeetingRoom = meetingRoom;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public void setMeetingSubject(String meetingSubject) {
        mMeetingSubject = meetingSubject;
    }

    public List<String> getParticipants() {
        return mparticipants;
    }

    public void setParticipants(List<String> mparticipants) {
        this.mparticipants = mparticipants;
    }


    public List<String> getMparticipants() {
        return mparticipants;
    }

    public void setMparticipants(List<String> mparticipants) {
        this.mparticipants = mparticipants;
    }

    public String getMeetingDate() {
        return mMeetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        mMeetingDate = meetingDate;
    }
    public Integer getColor() {
        return mColor;
    }

}