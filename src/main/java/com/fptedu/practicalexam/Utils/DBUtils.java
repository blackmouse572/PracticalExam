package com.fptedu.practicalexam.Utils;

import java.sql.Connection;
import java.sql.ResultSet;

public class DBUtils {
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=praticeExam;User=" + DB_USER + ";Password=" + DB_PASSWORD + ";trustServerCertificate=true;characterEncoding=UTF-8";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = java.sql.DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeUpdate(String sql, Connection con) {
        try {
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query, Connection con) {
        ResultSet rs = null;
        try {
            java.sql.Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
