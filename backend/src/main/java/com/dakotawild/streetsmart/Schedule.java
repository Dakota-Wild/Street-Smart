package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Schedules")
public class Schedule {
    
    @Id
    public String id;

    private String eventName;
    private String eventStartTime;
    private String eventDate;
    private String arrivalTime;
    private String address;
    private String userEmail;

    public Schedule(String eventName, String eventStartTime, String eventDate, String arrivalTime, String address, String userEmail){
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventDate = eventDate;
        this.arrivalTime = arrivalTime;
        this.address = address;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
      return String.format(
          "Schedule[id=%s, eventName='%s', eventStartTime='%s', eventDate='%s', arrivalTime='%s', address='%s', userEmail='%s']",
          id, eventName, eventStartTime, eventDate, arrivalTime, address, userEmail);
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


    public String getEventDate(){
        return this.eventDate;
    }

    public void setEventDate(String eventDate){
        this.eventDate = eventDate;
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
}
