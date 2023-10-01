package com.open.hotel.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.security.Security;
import com.open.hotel.threadVariables.VariableManager;

public class DataBase {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    Statement smt = null;
    Connection conn = null;

    public DataBase() {

    }

    public Statement connectDB(String dbType, String dbName) {

        String env = System.getProperty("Environment");
        if (env == null) {
            env = Config.properties.getProperty("Environment");
        }
        String url = Config.properties.getProperty(env + "_" + dbName + "_DB_URL");
        String userName = Config.properties.getProperty(env + "_" + dbName + "_DB_username");
        String password = Config.properties.getProperty(env + "_" + dbName + "_DB_password");
        Security security = new Security();
        password = security.decryptPassword(password);
        String driverClassName = Config.properties.getProperty(dbType + "_DriverClass");
        try {
            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, userName, password);
            smt = conn.createStatement();
        } catch (Exception e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return smt;
    }

    public HashMap<String, String> getResultSet(String query) {
        HashMap<String, String> data = new HashMap<String, String>();
        try {
            ResultSet rs = smt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            rs.next();
            for (int i = 1; i <= columnCount; i++) {
                data.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
            }
        } catch (SQLException e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }

    public HashMap<String, ArrayList<String>> getMultipleRowData(String query) {
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        ArrayList<String> headings = new ArrayList<String>();
        try {
            ResultSet rs = smt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<ArrayList<String>> rowObj = new ArrayList<ArrayList<String>>();
            for (int i = 1; i <= columnCount; i++) {
                headings.add(rsmd.getColumnName(i));
                rowObj.add(new ArrayList<String>());
            }
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    rowObj.get(i - 1).add(rs.getString(i));
                }
            }
            for (int i = 0; i < columnCount; i++) {
                data.put(headings.get(i), rowObj.get(i));
            }
        } catch (SQLException e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }

    public void closeDB() {
        try {
            smt.close();
            conn.close();
        } catch (Exception e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e);
        }
    }
}
