package com.test.aadmsi.msiexample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Sample Application that runs without driver support to test VM configuration.
 */
public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("\nTesting System Assigned Managed Identity for Database");
		App.getToken("https://database.windows.net", null);
		
		System.out.println("\nTesting System Assigned Managed Identity for Azure Management");
		App.getToken("https://management.azure.com", null);

		System.out.println("\nTesting User Assigned Managed Identity");
		App.getToken("https://database.windows.net/", ""); // Pass Object ID of User Assigned MSI
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
