package com.healthcare.dao;

import com.healthcare.model.User;

public interface UserDAO {
    boolean registerUser(User user) throws Exception;
    User login(String email, String password) throws Exception;
    User findById(int id) throws Exception;
}
