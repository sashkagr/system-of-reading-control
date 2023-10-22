package org.example.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.AuthorGetApiResponse;
import org.example.model.Book;
import org.example.model.BookGetApiResponse;
import org.example.model.BookSearchApiDocsResponse;
import org.example.model.BookSearchApiResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OpenLibraryApiHelper {

    public static BookGetApiResponse getBookByKeys(String keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        BookGetApiResponse bookGetApiResponse = new BookGetApiResponse();
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
                bookGetApiResponse = objectMapper.readValue(jsonResponse, BookGetApiResponse.class);
            } else {
                System.out.println("Error: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookGetApiResponse;
    }

    public static AuthorGetApiResponse getAuthorByKeys(String keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthorGetApiResponse authorGetApiResponse = new AuthorGetApiResponse();
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
                authorGetApiResponse = objectMapper.readValue(jsonResponse, AuthorGetApiResponse.class);
            } else {
                System.out.println("Error: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorGetApiResponse;
    }

    public static List<Book> searchBookByName(String name) {
        List<Book> result = new ArrayList<>();
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
                List<BookGetApiResponse> books = new ArrayList<>();
                for (BookSearchApiDocsResponse docsResponse : responses.getDocs())
                {
                    try {
                        BookGetApiResponse bookByKeys = getBookByKeys(docsResponse.getKey());
                        if (bookByKeys.getDescription() != null && bookByKeys.getTitle() != null && bookByKeys.getAuthors() != null && bookByKeys.getCovers() != null) {
                            books.add(bookByKeys);
                        }
                    }
                    catch (Exception e)
                    {
                    }
                }
                result = books.stream()
                        .map(OpenLibraryApiHelper::mapResponseToModel)
                        .collect(Collectors.toList());
            } else {
                System.out.println("Error: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Book mapResponseToModel(BookGetApiResponse bookGetApiResponse)
    {
        Book book = new Book();
        book.setId(bookGetApiResponse.getKey());
        book.setTitle(bookGetApiResponse.getTitle());
        AuthorGetApiResponse authorByKeys = getAuthorByKeys(bookGetApiResponse.getAuthors().get(0).getAuthor().getKey());
        book.setAuthor(authorByKeys.getPersonalName());
        book.setDescription(bookGetApiResponse.getDescription().getValue());
        book.setCover("https://covers.openlibrary.org/b/id/" + bookGetApiResponse.getCovers().get(0) + "-M.jpg");
        return book;
    }

}
