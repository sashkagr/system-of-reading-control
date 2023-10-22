package org.example.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleBooksApiHelper {
    private static final String API_KEY = "AIzaSyC01lVOawC92nEXiVOK62wRo6ZDMlIruLs";

    public static String getBookByISBN(String isbn) {
        String jsonResponse = "";
        try {
            String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + API_KEY;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response here and extract the book information
                jsonResponse = response.toString();
                System.out.println(jsonResponse);
            } else {
                System.out.println("Error: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }


}
