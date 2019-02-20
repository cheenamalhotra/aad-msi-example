package com.test.aadmsi.msiexample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


/**
 * Run this Application from Virtual machine with enabled System/User MSI Support
 */
public class TestMSIAuth extends CredentialManager {

    public static void main(String[] args) throws SQLException, InterruptedException {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(serverName);
        ds.setDatabaseName(databaseName);
        ds.setAuthentication(ActiveDirectoryMSI);

        try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT @@VERSION")) {
            System.out.println("Connected with SYSTEM MSI Authentication");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }

        ds = new SQLServerDataSource();
        ds.setServerName(serverName);
        ds.setDatabaseName(databaseName);
        ds.setAuthentication(ActiveDirectoryMSI);
        ds.setMSIClientId(userMSIClientId); // (Optional)

        try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT @@VERSION")) {
            System.out.println("Connected with User MSI Authentication");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
