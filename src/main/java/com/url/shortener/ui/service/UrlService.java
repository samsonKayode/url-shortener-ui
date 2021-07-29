package com.url.shortener.ui.service;

import com.url.shortener.ui.model.PageUrl;
import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;
import com.url.shortener.ui.model.UrlList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UrlService {

    public String convertUrl(UrlDto url);

    public List<Url> getAllUrlList();

    public Url getShortUrlStatistics(String shortUrl);

    public String decodeUrl(String hashUrl);

    public Boolean verifyShortUrl(String shortUrl);

    public Page<PageUrl> findAll(int pageNo, String sortField, String sortDir);
}
