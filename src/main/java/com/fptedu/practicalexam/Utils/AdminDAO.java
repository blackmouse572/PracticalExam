package com.fptedu.practicalexam.Utils;

import com.fptedu.practicalexam.Models.User;

public class AdminDAO extends UserDAO {
    public static void deleteUser(String username) {
        boolean currentStatus = AdminDAO.getUser(username).getStatus();
        con = DBUtils.getConnection();
        //set user status opposite of current status
        String sql = "UPDATE account SET status = " + (currentStatus ? 0 : 1) + " WHERE username = '" + username + "'";

        DBUtils.executeUpdate(sql, con);
        DBUtils.closeConnection(con);
    }

    public static void addAccount(User user) {
        con = DBUtils.getConnection();
        String query = "INSERT INTO [dbo].[account] VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFullname() + "', '" + user.getAdmin() + "', '" + user.getStatus() + "')";
        DBUtils.executeUpdate(query, con);
        DBUtils.closeConnection(con);
    }
}
