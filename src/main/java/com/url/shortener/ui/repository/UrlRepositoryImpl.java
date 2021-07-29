package com.url.shortener.ui.repository;

import com.url.shortener.ui.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UrlRepositoryImpl implements UrlRepository {

    @Value("${backend_url}")
    private String backendURL;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String convertUrl(UrlDto urlDto) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(backendURL+"/encode", urlDto, String.class);

        return result;
    }

    @Override
    public List<Url> getAllUrlList() {

        RestTemplate restTemplate = new RestTemplate();
        List<Url> urlList = restTemplate.getForObject(backendURL+"/list", ArrayList.class);

        return urlList;
    }

    @Override
    public Url getShortUrlStatistics(String shortUrl) {

        RestTemplate restTemplate = new RestTemplate();
        Url url = restTemplate.getForObject(backendURL+"/statistic/"+shortUrl, Url.class);
        return url;
    }

    @Override
    public String decodeUrl(String hashUrl) {

        try{
            RestTemplate restTemplate = new RestTemplate();
            String longUrl = restTemplate.getForObject(backendURL+"/decode/"+hashUrl, String.class);
            return longUrl;
        }catch(Exception exception){
            logger.error(exception.getLocalizedMessage());

            return exception.getLocalizedMessage();
        }
    }

    @Override
    public Boolean verifyShortUrl(String shortUrl) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean status = restTemplate.getForObject(backendURL+"/verify/"+shortUrl, Boolean.class);
        return status;
    }

    @Override
    public Page<PageUrl> findAll(int pageNo, String sortField, String sortDir) {

        String sortingRef="?sortField="+sortField+"&sortDir="+sortDir;
        RestTemplate restTemplate = new RestTemplate();
        PageHelper page = restTemplate.getForObject(backendURL+"/list/"+pageNo+sortingRef, PageHelper.class);
        return page;
    }
}
