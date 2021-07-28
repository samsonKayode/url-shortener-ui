package com.url.shortener.ui.repository;

import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;

import java.util.List;

public interface UrlRepository {

    public String convertUrl(UrlDto url);

    public List<Url> getAllUrlList();

    public Url getShortUrlStatistics(String shortUrl);

    public String decodeUrl(String hashUrl);

    public Boolean verifyShortUrl(String shortUrl);
}
