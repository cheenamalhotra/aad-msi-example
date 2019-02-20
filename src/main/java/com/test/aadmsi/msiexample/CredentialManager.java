package com.test.aadmsi.msiexample;

public class CredentialManager {
    static String AZURE_SQL_SPN = "https://database.windows.net/";
    static String AZURE_RESOURCE_SPN = "https://management.azure.com";
    static String ActiveDirectoryMSI = "ActiveDirectoryMSI"; // Authentication Mode for MSI Authentication

    // Provide Connection details below:
    static String serverName = ""; // Server URL xxxxxxx.database.windows.net
    static String databaseName = ""; // Name of database
    static String userMSIClientId = ""; // (Optional) Client ID of User Assigned MSI
}
