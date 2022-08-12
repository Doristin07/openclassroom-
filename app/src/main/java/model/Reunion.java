package model;

import java.util.List;

public class Reunion {

    private int mMeetingTime;
    private List<String> emails;
    private String mMeetingPlace;
    private String mMeetingSubject;

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

    public String getMeetingPlace() {
        return mMeetingPlace;
    }

    public void setMeetingPlace(String mMeetingPlace) {
        this.mMeetingPlace = mMeetingPlace;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public void setMeetingSubject(String mMeetingSubject) {
        this.mMeetingSubject = mMeetingSubject;
    }



}
