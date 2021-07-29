package com.url.shortener.ui.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageUrl {

    private int id;

    private String longUrl;

    private Date dateCreated;

    private String hashUrl;

    @JsonCreator
    public PageUrl(@JsonProperty("id") int id, @JsonProperty("longUrl") String longUrl, @JsonProperty("dateCreated") Date dateCreated, @JsonProperty("hashUrl") String hashUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.dateCreated = dateCreated;
        this.hashUrl = hashUrl;
    }
}
