package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearchApiResponse {
    @JsonProperty("docs")
    private List<BookSearchApiDocsResponse> docs;

    public List<BookSearchApiDocsResponse> getDocs() {
        return docs;
    }

    public void setDocs(List<BookSearchApiDocsResponse> docs) {
        this.docs = docs;
    }


}
