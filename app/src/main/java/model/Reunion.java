package model;

import java.util.List;

public class Reunion {

    private String mMeetingTime;
    private String mMeetingRoom;
    private String mMeetingSubject;
    private List<String> mparticipants;


    public Reunion(String meetingRoom, String meetingTime, String meetingSubject, List<String> participants) {
        this.mMeetingRoom=meetingRoom;
        this.mMeetingTime=meetingTime;
        this.mMeetingSubject=meetingSubject;
        this.mparticipants=participants;
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

}
