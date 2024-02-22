package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;

import java.util.List;

public interface MyRepository {

    UserInfo save(UserInfo user);

    void delete(String pesel);

    UserInfo findById(String pesel);

    List<UserInfo> findAll();

    UserInfo findByEmail(String email);
}
