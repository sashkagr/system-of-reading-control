package org.example.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BookSearchApiResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OpenLibriaryApiHelper {

//   private static final String API_KEY = ResourceBundle.getBundle("application").getString("google.api.books.key");

    public static String getBookByKeys(String keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = "";
        try {
            String apiUrl = "https://openlibrary.org/" + keys+".json";
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
                BookSearchApiResponse responses = objectMapper.readValue(jsonResponse, BookSearchApiResponse.class);
                if (!responses.getDocs().isEmpty())
                {
                    jsonResponse = responses.getDocs().get(0).getKey();
                }
                else
                {
                    jsonResponse = "";
                }

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
    public static String searchBookByName(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = "";
        try {
            String apiUrl = "https://openlibrary.org/search.json?q=" + name.replace(' ','+');
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
                BookSearchApiResponse responses = objectMapper.readValue(jsonResponse, BookSearchApiResponse.class);
                if (!responses.getDocs().isEmpty())
                {
                    jsonResponse = responses.getDocs().get(0).getKey();
                }
                else
                {
                    jsonResponse = "";
                }
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
