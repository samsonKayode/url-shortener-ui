package com.url.shortener.ui.controller;

import com.url.shortener.ui.model.ShortUrl;
import com.url.shortener.ui.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DecodeUrlController {

    @Autowired
    UrlService service;

    private String longURL ="";

    @GetMapping("/decode")
    public String showDecodePage(Model model) {
        longURL="";
        model.addAttribute("longURL", longURL);
        return "pages/decode";
    }

    @ModelAttribute("shortUrl")
    public ShortUrl shortUrl(Model model) {
        model.addAttribute("longURL", longURL);
        return new ShortUrl();
    }

    @PostMapping("/decode")
    public String decodeUrl(@Valid @ModelAttribute("shortUrl") ShortUrl shortUrl, BindingResult theBind, RedirectAttributes redirectAttributes, Model model){
        if (theBind.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shortUrl", theBind);
            redirectAttributes.addFlashAttribute("shortUrl", shortUrl);
            return "redirect:/shortlink/decode";
        }else{
            Boolean shortUrlExist = service.verifyShortUrl(shortUrl.getShortUrl());
            if(shortUrlExist==true){
                longURL = service.decodeUrl(shortUrl.getShortUrl());
            }else{
                longURL="No data found for code provided";
            }
            model.addAttribute("longURL", longURL);
            return "pages/decode";
        }
    }
}
