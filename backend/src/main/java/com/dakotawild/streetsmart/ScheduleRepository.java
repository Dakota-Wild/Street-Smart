package com.dakotawild.streetsmart;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    
    public List<Schedule> findByEventName(String eventName);
    public List<Schedule> findByEventStartTime(String eventStartTime);
    public List<Schedule> findByEventDate(String eventDate);
    public List<Schedule> findByArrivalTime(String arrivalTime);
    public List<Schedule> findByAddress(String address);
    public List<Schedule> findByUserEmail(String userEmail);

}
