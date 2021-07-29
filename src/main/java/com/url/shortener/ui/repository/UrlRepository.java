package com.url.shortener.ui.repository;

import com.url.shortener.ui.model.PageUrl;
import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UrlRepository {

    public String convertUrl(UrlDto url);

    public List<Url> getAllUrlList();

    public Url getShortUrlStatistics(String shortUrl);

    public String decodeUrl(String hashUrl);

    public Boolean verifyShortUrl(String shortUrl);

    public Page<PageUrl> findAll(int PageNo, String sortField, String sortDir);
}
