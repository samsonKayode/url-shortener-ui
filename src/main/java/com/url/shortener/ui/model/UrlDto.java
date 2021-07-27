package com.url.shortener.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    @Size(min = 5, message = "url size must be greater than 5")
    private String longUrl;
}
