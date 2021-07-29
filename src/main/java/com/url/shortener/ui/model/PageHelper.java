package com.url.shortener.ui.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageHelper extends PageImpl<PageUrl> {

    @JsonCreator
    // Note: I don't need a sort, so I'm not including one here.
    // It shouldn't be too hard to add it in tho.
    public PageHelper(@JsonProperty("content") List<PageUrl> content, @JsonProperty("number") int number, @JsonProperty("size") int size,
                      @JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                      @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first,
                      @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public PageHelper(List<PageUrl> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageHelper(List<PageUrl> content) {
        super(content);
    }

    public PageHelper() {
        super(new ArrayList<>());
    }
}
