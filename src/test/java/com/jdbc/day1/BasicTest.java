package com.jdbc.day1;

import java.sql.*;

public class BasicTest {


    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:oracle:thin:@54.198.155.113:1521:xe";
        String username = "hr";
        String password = "hr";
        //to establish connection with a database

        Connection connection = DriverManager.getConnection(URL, username, password);

        //ResultSet.TYPE_SCROLL_INSENSITIVE
        //The constant indicating the type for a <code>ResultSet</code> object
        //     * that is scrollable but generally not sensitive to changes to the data
        //     * that underlies the <code>ResultSet</code>.
        //ResultSet.CONCUR_READ_ONLY : The constant indicating the concurrency mode for a ResultSet</code> object that may NOT be updated.
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
