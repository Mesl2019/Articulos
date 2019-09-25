/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Martín
 */
public class ConnectDB {
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String url = "jdbc:sqlserver://localhost:1433;database=db_App;user=sa;password=123;";

    public Connection getConnection() throws SQLException {
        Connection cn = null;
        try {
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException e) {
            //Si no encuentra la clase
            //Si detecta un acceso ilegal
            //Instancia al driver de SQL.jar
            throw new SQLException(e.getMessage());
        }
        return cn;
    }
}
