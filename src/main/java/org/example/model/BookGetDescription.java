package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookGetDescription {
    @JsonProperty("description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
