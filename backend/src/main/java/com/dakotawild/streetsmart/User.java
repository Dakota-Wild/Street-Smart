package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class User {

  @Id
  public String id;
 
  private String firstName;
  private String lastName;
  private String email;
  private String password;

  public User() {}

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, firstName='%s', lastName='%s', email='%s', password='%s']",
        id, firstName, lastName, email, password);
  }
  
  public String getFirstName(){
    return this.firstName;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  
  public String getLastName(){
    return this.lastName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public String getEmail(){
    return this.email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public String getPassword(){
    return this.password;
  }

  public void setPassword(String password){
    this.password = password;
  }
}