package org.example.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetApiResponseByStr {
    @JsonProperty("description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonProperty("title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("covers")
    private List<Integer> covers;
    public List<Integer> getCovers() {
        return covers;
    }
    public void setCovers(List<Integer> covers) {
        this.covers = covers;
    }

    @JsonProperty("key")
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("authors")
    private List<BookGetApiAuthorsResponse> authors;
    public List<BookGetApiAuthorsResponse> getAuthors() {
        return authors;
    }
    public void setAuthors(List<BookGetApiAuthorsResponse> authors) {
        this.authors = authors;
    }

}
