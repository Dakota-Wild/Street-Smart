package com.dakotawild.streetsmart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Schedules")
public class Schedule {

    @Id
    public String id;

    private String eventName;
    private String eventStartTime;
    private Date eventDate;
    private String arrivalTime;
    private String address;
    private String userEmail;
    private String leaveByTime;

    public Schedule(String eventName, String eventStartTime, Date eventDate, String arrivalTime, String address,
            String userEmail, String leaveByTime) throws ParseException {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventDate = eventDate;
        this.arrivalTime = arrivalTime;
        this.address = address;
        this.userEmail = userEmail;
        this.leaveByTime = leaveByTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Schedule[id=%s, eventName='%s', eventStartTime='%s', eventDate='%s', arrivalTime='%s', address='%s', userEmail='%s']",
                id, eventName, eventStartTime, eventDate, arrivalTime, address, userEmail);
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartTime() {
        return this.eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(String eventDate) throws ParseException {
        this.eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);
    }

    public String getEventArrivalTime(){
        return this.arrivalTime;
    }

    public void setEventArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public String getEventAddress(){
        return this.address;
    }

    public void setEventAddress(String address){
        this.address = address;
    }

    public String getEventEmail(){
        return this.userEmail;
    }

    public void setEventEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getLeaveByTime(){
        return this.leaveByTime;
    }

    public void setLeaveByTime(String leaveByTime){
        this.leaveByTime = leaveByTime;
    }
}
