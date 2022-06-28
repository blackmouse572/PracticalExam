package com.fptedu.practicalexam.Utils;

import com.fptedu.practicalexam.Models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    protected static Connection con;

    public static void updateUser(String username, User newUser) {
        con = DBUtils.getConnection();
        String query = "UPDATE [dbo].[account] SET [username] = '" + newUser.getUsername() + "', [password] = '" + newUser.getPassword() + "', [fullname] = '" + newUser.getFullname() + "', [status] = '" + newUser.getStatus() + "', [role] = '" + newUser.getAdmin() + "' WHERE [username] = '" + username + "'";
        DBUtils.executeUpdate(query, con);
        DBUtils.closeConnection(con);
    }

    public static User getUser(String username) {
        con = DBUtils.getConnection();
        String query = "SELECT * FROM [dbo].[account] WHERE [username] = '" + username + "'";
        ResultSet rs = DBUtils.executeQuery(query, con);

        User user = new User();
        try {
            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setStatus(rs.getBoolean("status"));
                user.setAdmin(rs.getBoolean("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection(con);
        return user;
    }

    public static ArrayList<User> getAllUsers() {
        con = DBUtils.getConnection();
        String query = "SELECT * FROM [dbo].[account]";
        ResultSet rs = DBUtils.executeQuery(query, con);

        ArrayList<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setStatus(rs.getBoolean("status"));
                user.setAdmin(rs.getBoolean("role"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection(con);
        return users;
    }
}
