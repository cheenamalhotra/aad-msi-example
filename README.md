## Sample Application for AAD MSI Authentication using MSSQL JDBC Driver

This application demonstrates using **mssql-jdbc** driver (**v7.2.1**) to authenticate client applications with 'ActiveDirectoryMSI' authentication mode.
This application must be run in Azure Virtual Machine/App Service/Function App with enabled support for "System/User Assigned Managed Service Identity".

Both `7.2.1.jre8` and `7.2.1.jre11` version artifacts of the driver can be added as Maven dependency and used for testing.

> Note : Required setup to provide access to System/User Assigned Managed Identities to be able to access resources must be provided before running the application.
### Steps to run this Application:
- Navigate to "src\main\java\com\test\aadmsi\msiexample\CredentialManager.java" file and provide: Server Name, Database Name, and User Assigned MSI (optional).
- Run 'mvn clean install' from the root folder to build and test the application.
