package dev.konstantin.entity;

import dev.konstantin.config.Gender;

import javax.persistence.*;
import java.time.LocalDate;

import static dev.konstantin.config.Const.*;

@Entity
@Table(name = USER_TABLE)
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = USER_ID)
  private String id;

  @Column(name = USER_NAME, nullable = false)
  private String name;

  @Column(name = USER_LASTNAME, nullable = false)
  private String lastName;

  @Column(name = USER_EMAIL, nullable = false)
  private String email;

  @Column(name = USER_GENDER, nullable = false)
  private Gender gender;

  @Column(name = USER_BIRTHDAY, nullable = false)
  private LocalDate birthday;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastname) {
    this.lastName = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}
