package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;


public class User {

  @Id
  public String id;
 
  public String userName;
  public String password;
  public String name;
  public String email;

  public User() {}

  public User(String userName, String password, String name, String email) {
    this.userName = userName;
    this.password = passsword;
    this.name = name;
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, userName='%s', password='%s', name='%s', email='%s']",
        userName, password, name, email);
  }

}