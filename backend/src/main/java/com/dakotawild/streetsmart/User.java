package com.dakotawild.streetsmart;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;

@Document(collection="Users")
public class User {

  @Id
  public String id;
 
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String homeAddress;

  public User(String firstName, String lastName, String email, String password, String homeAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.homeAddress = homeAddress;
  }

  @Override
  public String toString() {
    return String.format(
        "User[id=%s, firstName='%s', lastName='%s', email='%s', password='%s', homeAddress='%s']",
        id, firstName, lastName, email, password, homeAddress);
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

  public String getHomeAddress(){
    return this.homeAddress;
  }

  public void setHomeAddress(String homeAddress){
    this.homeAddress = homeAddress;
  }
}