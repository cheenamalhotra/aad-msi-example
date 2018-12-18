## Sample Application for AAD MSI Authentication using MSSQL JDBC Driver

This application demonstrates using **mssql-jdbc** driver (as on PR [#838](https://github.com/Microsoft/mssql-jdbc/pull/838)) to authenticate client applications with 'ActiveDirectoryMSI' authentication module.
This application must be run in Azure Virtual Machine/App Service/Function App with enabled support for "System/User Assigned Managed Service Identity".

**'lib'** folder in this Repository contains MSSQL JDBC Driver Jar used for testing [Built from PR [#838](https://github.com/Microsoft/mssql-jdbc/pull/838)].
> Note : Required setup to provide access to System/User Assigned Managed Identities to be able to access resources must be provided before running the application.
### Steps to run this Application:
- Navigate to "src\main\java\com\test\aadmsi\msiexample\CredentialManager.java" file and provide: Server Name, Database Name, and User Assigned MSI (optional).
- Run 'mvn clean install' from the root folder to build and test the application.
