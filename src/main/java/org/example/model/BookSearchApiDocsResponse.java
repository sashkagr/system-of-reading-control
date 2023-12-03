package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearchApiDocsResponse {
    @JsonProperty("key")
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("author_name")
    private List<String> author_name;
    public List<String> getAuthor_name() {
        return author_name;
    }
    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    @JsonProperty("cover_i")
    private String cover_i;
    public String getCover_i() {
        return cover_i;
    }
    public void setCover_i(String cover_i) {
        this.cover_i = cover_i;
    }

    @JsonProperty("title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



}
