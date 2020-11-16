package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;

@Document(collection="Users")
public class User {

  @Id
  public String id;
 
  public String firstName;
  public String lastName;
  public String email;
  public String password;

  public User() {}

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = passsword;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, firstName='%s', lastName='%s', email='%s', password='%s']",
        id, firstName, lastname, email, password);
  }
}