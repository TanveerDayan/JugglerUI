package com.juggler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ConnectionEstablisher {
	String baseUrl = null;

	public ConnectionEstablisher(String baseURLValue) {
		baseUrl=baseURLValue;
	}
	public String connectRest(String path, Map<String, String> parameters)
			throws IOException {
		URL url = new URL(baseUrl + path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		PrintWriter wr = new PrintWriter(conn.getOutputStream(), true);
		StringBuilder builder = new StringBuilder();
		for (String key : parameters.keySet()) {
			builder.append(key + "="
					+ URLEncoder.encode(parameters.get(key), "UTF-8"));
			builder.append("&");
		}
		builder.setLength(builder.length() - 1);
		wr.println(builder);
		wr.close();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String output = br.readLine();
		return output;
	}
}
