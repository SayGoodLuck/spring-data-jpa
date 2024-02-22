package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserMyRepository implements MyRepository {

    private final ConcurrentHashMap<String, UserInfo> map = new ConcurrentHashMap<>();

    public List<UserInfo> findAll() {
        return new ArrayList<>(map.values());
    }

    public boolean isUserExist(String id) {
        return map.get(id) != null;
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        Objects.requireNonNull(userInfo);
        map.put(userInfo.getId(), userInfo);
        return userInfo;
    }

    @Override
    public UserInfo findByEmail(String email) {
        for (String keys : map.keySet()) {
            if (Objects.equals(map.get(keys).getEmail(), email)) {
                return map.get(keys);
            }
        }
        return null;
    }

    @Override
    public void delete(String pesel) {
        if (isUserExist(pesel)) {
            map.remove(pesel);
        }
    }

    @Override
    public UserInfo findById(String id) {
        if (isUserExist(id)) {
            return map.get(id);
        }
        return null;
    }
}
