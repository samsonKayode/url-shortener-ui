package com.url.shortener.ui.service;

import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;

import java.util.List;

public interface UrlService {

    public String convertUrl(UrlDto url);

    public List<Url> getAllUrlList();

    public Url getShortUrlStatistics(String shortUrl);

    public String decodeUrl(String hashUrl);

    public Boolean verifyShortUrl(String shortUrl);
}
