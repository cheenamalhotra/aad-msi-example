## Sample Application for AAD MSI Authentication using MSSQL JDBC Driver

This application demonstrates using **mssql-jdbc** driver (as on PR [#838](https://github.com/Microsoft/mssql-jdbc/pull/838)) to authenticate client applications with 'ActiveDirectoryMSI' authentication module.
This application must be run in Azure Virtual Machine with enabled support for "System/User Assigned Managed Service Identity".

**'lib'** folder in this Repository contains MSSQL JDBC Driver Jar used for testing [Built from PR [#838](https://github.com/Microsoft/mssql-jdbc/pull/838)].
> Note : Required setup to provide access to System/User Assigned Managed Identities to be able to access resources must be provided before running the application.
### Steps to run this Application:
- Navigate to "src\main\java\com\test\aadmsi\msiexample\TestMSIAuth.java" file and provide database, servername and User Assigned MSI (optional) details.
- Also provide User Assigned MSI Object ID in "src\main\java\com\test\aadmsi\msiexample\App.java" to validate User Assigned MSI working for current Virtual Machine.
- Run 'mvn clean install' from the root folder to run the application.
