package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookGetApiAuthorsResponse {

    @JsonProperty("author")
    private BookGetApiAuthorResponse author;

    public BookGetApiAuthorResponse getAuthor() {
        return author;
    }

    public void setAuthor(BookGetApiAuthorResponse author) {
        this.author = author;
    }
}
