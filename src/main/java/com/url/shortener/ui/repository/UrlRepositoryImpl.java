package com.url.shortener.ui.repository;

import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.model.UrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UrlRepositoryImpl implements UrlRepository {

    @Value("${backend_url}")
    private String backendURL;

    @Override
    public String convertUrl(UrlDto urlDto) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(backendURL+"/encode", urlDto, String.class);

        return result;
    }

    @Override
    public List<Url> getAllUrlList() {

        RestTemplate restTemplate = new RestTemplate();
        List<Url> urlList = (List<Url>) restTemplate.getForObject(backendURL+"/list", Url.class);
        return urlList;
    }

    @Override
    public Url getShortUrlStatistics(String shortUrl) {

        RestTemplate restTemplate = new RestTemplate();
        Url url = restTemplate.getForObject(backendURL+"/statistic/"+shortUrl, Url.class);
        return null;
    }

    @Override
    public String decodeUrl(String hashUrl) {

        RestTemplate restTemplate = new RestTemplate();
        String longUrl = restTemplate.getForObject(backendURL+"/decode/"+hashUrl, String.class);
        return longUrl;
    }
}
