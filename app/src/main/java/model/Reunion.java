package model;

import java.util.List;

public class Reunion {

    private int mMeetingTime;
    private List<String> emails;
    private String mMeetingRoom;
    private String mMeetingSubject;


    public Reunion(String meetingSubject, String meetingRoom, int meetingTime, List<String> emails) {
        mMeetingTime = meetingTime;
        this.emails = emails;
        mMeetingRoom = meetingRoom;
        mMeetingSubject = meetingSubject;
    }

    public Reunion(String meetingRoom, String s, String stringExtra) {
    }

    public int getMeetingTime() {
        return mMeetingTime;
    }

    public void setMeetingTime(int mMeetingTime) {
        this.mMeetingTime = mMeetingTime;
    }

    public List<String> getParticipants() {
        return emails;
    }

    public void setParticipants(List<String> participants) {
        this.emails = participants;
    }

    public String getMeetingRoom() {
        return mMeetingRoom;
    }

    public void setMeetingRoom(String mMeetingPlace) {
        this.mMeetingRoom= mMeetingPlace;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public void setMeetingSubject(String mMeetingSubject) {
        this.mMeetingSubject = mMeetingSubject;
    }



}
