package Reusablecomponent;

import java.sql.*;
import java.util.Properties;

public class Database {
    Properties prop = new Properties();

    public String DB(String DBName, String SelectQuery) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        // Loading a driver
        Class.forName("com.mysql.jdbc.Driver");

        // Creating a connection
        String url = "jdbc:mysql://localhost:3306/"+DBName;
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, user, password);

        // Creating a Statement
        Statement st = con.createStatement();
        String sqlquery = "select *  from table_Name"+";";
        ResultSet rs = st.executeQuery(sqlquery);
        // closing the connection
        con.close();
        return sqlquery;
    }
}
