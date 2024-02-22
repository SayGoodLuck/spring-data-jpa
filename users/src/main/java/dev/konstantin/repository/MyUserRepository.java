package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends MyRepository, JpaRepository<UserInfo, Long> { }
