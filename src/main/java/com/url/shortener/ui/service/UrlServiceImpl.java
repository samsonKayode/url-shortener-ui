package com.url.shortener.ui.service;

import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;
import com.url.shortener.ui.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    UrlRepository repository;

    @Override
    public String convertUrl(UrlDto urlDto) {
        return repository.convertUrl(urlDto);
    }

    @Override
    public List<Url> getAllUrlList() {
        return repository.getAllUrlList();
    }

    @Override
    public Url getShortUrlStatistics(String shortUrl) {
        return repository.getShortUrlStatistics(shortUrl);
    }

    @Override
    public String decodeUrl(String hashUrl) {
        return repository.decodeUrl(hashUrl);
    }

    @Override
    public Boolean verifyShortUrl(String shortUrl) {
        return repository.verifyShortUrl(shortUrl);
    }
}
