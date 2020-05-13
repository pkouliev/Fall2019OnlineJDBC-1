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
        //in executeQuery method we provide query as a parameter
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        //resultSet.next() -- returns true until it reaches last row.
        // and jumps to next row, if there is some row with data
        while (resultSet.next()) {
            //get data from 2nd column for every row
            //2nd column is a first name info
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }

        resultSet.beforeFirst(); // to comeback to the beginning of result set

        //some technical information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        //some technical infomration about resultset
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("Label of 1st column: " + resultSetMetaData.getColumnLabel(1));

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("salary"));
//        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
