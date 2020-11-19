package com.dakotawild.streetsmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    //     List<User> users = new ArrayList<User>();
    //     try {
    //         users = testrepo.findAll();
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return users;
    // }

    @GetMapping("login-check")
    public User checkValidLogin(User user) {
      System.out.println(user.getEmail());
      User _user = userRepository.findByEmail(user.getEmail());
      if(_user == null) {
        return null;
      }

      if(!user.getPassword().equals(_user.getPassword())) {
        System.out.println("Incorrect password.");
        return null;
      }

      return _user;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
 
      User _user = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getHomeAddress()));
      return _user;
    }

    @PostMapping("/schedule")
    public Schedule postSchedule(@RequestBody Schedule schedule){
        Schedule _schedule = scheduleRepository.save(new Schedule(schedule.getEventName(), schedule.getEventStartTime(),schedule.getEventDate(),
                                                        schedule.getEventArrivalTime(), schedule.getEventAddress(), schedule.getEventEmail()));
        return _schedule;
    }
}