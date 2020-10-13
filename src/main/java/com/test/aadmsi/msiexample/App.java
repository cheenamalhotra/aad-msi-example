package com.test.aadmsi.msiexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.microsoft.azure.msiAuthTokenProvider.*;

/**
 * Sample Application that runs without driver support to test VM configuration.
 */
public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("\nTesting System Assigned Managed Identity for Database");
		App.getTokenUsingJar("https://database.windows.net", null);
		
		System.out.println("\nTesting System Assigned Managed Identity for Azure Management");
		App.getTokenUsingJar("https://management.azure.com", null);

		System.out.println("\nTesting User Assigned Managed Identity");
		App.getTokenUsingJar("https://database.windows.net/", ""); // Pass Object ID of User Assigned MSI
	}

    static String getTokenUsingJar(String resourceId, String objectId) throws Exception
	{
		MSICredentials credsProvider = MSICredentials.getMSICredentials();
		if (objectId != null && !objectId.isEmpty()) {
			credsProvider.updateObjectId(objectId);
		}
		
		String token = credsProvider.getToken(resourceId).accessToken();
		
		System.out.println("Access Token Using Jar file for resource : " + resourceId + ",  Token : "+ token);
		
		return token;
	}
	
	static String getToken(String resource, String objectId) throws Exception {
		String urlString = "http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource="
				+ resource;

		if (null != objectId) {
			urlString += "&object_id=" + objectId;
		}

		HttpURLConnection connection = null;

		try {
			connection = (HttpURLConnection) new URL(urlString).openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Metadata", "true");
			connection.connect();

			try (InputStream stream = connection.getInputStream()) {

				BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 100);
				String result = reader.readLine();
				String accessTokenIdentifier = "\"access_token\":\"";

				int startIndex = result.indexOf(accessTokenIdentifier) + accessTokenIdentifier.length();
				String accessToken = result.substring(startIndex, result.indexOf("\"", startIndex));

				System.out.println("Access Token: " + accessToken);

				return accessToken;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
