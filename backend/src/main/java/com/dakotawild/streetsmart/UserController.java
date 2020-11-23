package com.dakotawild.streetsmart;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ScheduleRepository scheduleRepository;

  // @GetMapping("/users")
  // public List<User> getAllUsers() {
  // List<User> users = new ArrayList<User>();
  // try {
  // users = testrepo.findAll();
  // } catch (Exception e) {
  // System.out.println(e.getMessage());
  // }
  // return users;
  // }

  @GetMapping("/login-check/{email}/{password}")
  public User checkValidLogin(@PathVariable String email, @PathVariable String password) {

    User _user = userRepository.findByEmail(email);
    if (_user == null) {
      return null;
    }

    if (!password.equals(_user.getPassword())) {
      System.out.println("Incorrect password.");
      return null;
    }

    return _user;
  }

  @GetMapping("/user/{email}")
  public User getUser(@PathVariable String email) {
    User _user = userRepository.findByEmail(email);
    return _user;
  }

  @PostMapping("/user")
  public User postUser(@RequestBody User user) {

    User _user = userRepository.save(
        new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getHomeAddress()));
    return _user;
  }

  @GetMapping("/schedule/{email}")
  public List<Schedule> getSchedule(@PathVariable String email) {
    List<Schedule> _schedule = scheduleRepository.findByUserEmail(email);
    Collections.sort(_schedule, new Comparator<Schedule>() {
      public int compare(Schedule s1, Schedule s2) {
        return s1.getEventDate().compareTo(s2.getEventDate());
      }
    });
    return _schedule;
  }

  @PostMapping("/schedule")
  public Schedule postSchedule(@RequestBody Schedule schedule) {
    Schedule _schedule = scheduleRepository.save(new Schedule(schedule.getEventName(), schedule.getEventStartTime(),
        schedule.getEventDate(), schedule.getEventArrivalTime(), schedule.getEventAddress(), schedule.getEventEmail(),
        schedule.getLeaveByTime()));
    return _schedule;
  }
}