package com.url.shortener.ui.controller;

import com.url.shortener.ui.model.ShortUrl;
import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/shortlink")
public class UrlStatisticsController {

    @Autowired
    UrlService service;

     Url url=new Url();

    private String noDataFound="";

    @Value("${backend_url}")
    private String backendURL;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/stats")
    public String showStatsPage(Model model) {
        url=new Url();
        noDataFound ="";
        model.addAttribute("url", url);
        model.addAttribute("noDataFound", noDataFound);
        return "pages/stats";
    }

    @ModelAttribute("shortUrl")
    public ShortUrl shortUrl(Model model) {
        model.addAttribute("url", url);
        model.addAttribute("noDataFound", noDataFound);
        return new ShortUrl();
    }

    @PostMapping("/stats")
    public String urlStats(@Valid @ModelAttribute("shortUrl") ShortUrl shortUrl, BindingResult theBind, RedirectAttributes redirectAttributes, Model model){
        if (theBind.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shortUrl", theBind);
            redirectAttributes.addFlashAttribute("shortUrl", shortUrl);
            return "redirect:/shortlink/stats";
        }else{
            Boolean shortUrlExist = service.verifyShortUrl(shortUrl.getShortUrl());
            if(shortUrlExist==true){
                url = service.getShortUrlStatistics(shortUrl.getShortUrl());
                url.setHashUrl(backendURL+"/"+url.getHashUrl());
                noDataFound="found";
            }else{
                noDataFound="No data found for code provided";
            }
            model.addAttribute("noDataFound", noDataFound);
            model.addAttribute("url", url);
            return "/pages/stats";
        }
    }
}
