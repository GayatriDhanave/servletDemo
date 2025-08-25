package com.servletexample.servletexample.dao;


import com.servletexample.servletexample.entity.User;

import java.util.*;

public class UserDAO {
    private static final Map<Integer, User> users = new HashMap<>();
    private static int idCounter = 1;

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(int id) {
        return users.get(id);
    }

    public void save(User user) {
        user.setId(idCounter++);
        users.put(user.getId(), user);
    }

    public void update(User user) {
        users.put(user.getId(), user);
    }

    public void delete(int id) {
        users.remove(id);
    }
}
