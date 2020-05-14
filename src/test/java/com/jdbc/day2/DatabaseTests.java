package com.jdbc.day2;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class DatabaseTests {

    final String DB_URL = "jdbc:oracle:thin:@3.90.175.72:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";


    @Test
    public void getEmployeesData() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet.TYPE_SCROLL_INSENSITIVE - make ResultSet scrollable
//        ResultSet.CONCUR_READ_ONLY - creates ResultSet object that cannot be updated but can be read
        String QUERY = "SELECT * FROM employees";
        ResultSet resultSet = statement.executeQuery(QUERY);

        List<Integer> employeeIDs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Map<String, Integer>> employeeIDsMap = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Integer> map = new HashMap<>();
            map.put("employee_id", resultSet.getInt("employee_id"));
            employeeIDsMap.add(map);

            employeeIDs.add(resultSet.getInt("employee_id"));
            names.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }

        System.out.println(employeeIDs);
        System.out.println(names);
        System.out.println(employeeIDsMap);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
