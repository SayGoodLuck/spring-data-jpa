package dev.konstantin.service;

import dev.konstantin.entity.UserInfo;

import java.util.List;

public interface UserService {

  List<UserInfo> getAllUsers();

  void save(UserInfo user);

  void update(UserInfo user);

  void delete(String pesel);

  UserInfo findById(String pesel);

  UserInfo findByEmail(String email);

  boolean isUserExist(String pesel);
}
