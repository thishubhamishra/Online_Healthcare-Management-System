package com.healthcare.dao;

import com.healthcare.model.User;
import com.healthcare.util.DBUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT_SQL =
            "INSERT INTO users (name, email, password, role, phone) VALUES (?, ?, ?, ?, ?)";
    private static final String LOGIN_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String FIND_BY_ID_SQL =
            "SELECT * FROM users WHERE id = ?";

    @Override
    public boolean registerUser(User user) throws Exception {
        // basic server-side validation
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password too short");
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPhone());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public User login(String email, String password) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(LOGIN_SQL)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setPhone(rs.getString("phone"));
                    // password not set in object for safety
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User findById(int id) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setPhone(rs.getString("phone"));
                    return user;
                }
            }
        }
        return null;
    }
}
