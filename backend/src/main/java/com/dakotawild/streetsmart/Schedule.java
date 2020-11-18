package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Schedules")
public class Schedule {
    
    @Id
    public String id;

    private String eventName;
    private String eventStartTime;
    private String eventEndTime;
    private String arrivalTime;
    private String address;
    private String userEmail;

    public Schedule(String eventName, String eventStartTime, String eventEndTime, String arrivalTime, String address, String userEmail){
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.arrivalTime = arrivalTime;
        this.address = address;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
      return String.format(
          "Schedule[id=%s, eventName='%s', eventStartTime='%s', eventEndTime='%s', arrivalTime='%s', address='%s', userEmail='%s']",
          id, eventName, eventStartTime, eventEndTime, arrivalTime, address, userEmail);
    }

    public String getEventName(){
        return this.eventName;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public String getEventStartTime(){
        return this.eventStartTime;
    }

    public void setEventStartTime(String eventStartTime){
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime(){
        return this.eventEndTime;
    }

    public void setEventEndTime(String eventEndTime){
        this.eventEndTime = eventEndTime;
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

    public String getUserEmail(){
        return this.userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
}
