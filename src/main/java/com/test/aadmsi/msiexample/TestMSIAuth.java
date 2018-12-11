package com.test.aadmsi.msiexample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * Run this Application from Virtual machine with enabled System/User MSI
 * Support
 */
public class TestMSIAuth {

	static String serverName = ""; // Server URL xxxxxxx.database.windows.net
	static String databaseName = ""; // Name of database
	static String userMSIObjectId = ""; // Object ID of User Assigned MSI

	static String hostNameInCertificate = "*.database.windows.net"; // Host Name in Certificate
	static String ActiveDirectoryMSI = "ActiveDirectoryMSI"; // Authentication Mode for MSI Authentication

	public static void main(String[] args) throws SQLException {

		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setServerName(serverName);
		ds.setDatabaseName(databaseName);
		ds.setHostNameInCertificate(hostNameInCertificate);
		ds.setAuthentication(ActiveDirectoryMSI);

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT @@VERSION")) {
			System.out.println("\n\nConnected with SYSTEM MSI Authentication");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
		
		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT Distinct TABLE_NAME FROM information_schema.TABLES")) {
			System.out.println("Connected with SYSTEM MSI Authentication, Tables : ");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}

		ds = new SQLServerDataSource();
		ds.setServerName(serverName);
		ds.setDatabaseName(databaseName);
		ds.setAuthentication(ActiveDirectoryMSI);
		ds.setHostNameInCertificate(hostNameInCertificate);
		ds.setMSIObjectId(userMSIObjectId);

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT @@VERSION")) {
			System.out.println("\n\nConnected with User MSI Authentication");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
		
		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT Distinct TABLE_NAME FROM information_schema.TABLES")) {
			System.out.println("\n\nConnected with User MSI Authentication, Tables : ");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		}
	}
}