package com.url.shortener.ui.controller;

import com.url.shortener.ui.model.UrlDto;
import com.url.shortener.ui.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UrlController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UrlService service;

    String encodedURL ="";

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("encodedURL", encodedURL);
        return "home/homepage";
    }

    @ModelAttribute("urlDto")
    public UrlDto urlDto(Model model) {
        model.addAttribute("encodedURL", encodedURL);
        return new UrlDto();
    }


    @PostMapping("/encode")
    public String encodeUrl(@Valid @ModelAttribute("urlDto") UrlDto urlDto, BindingResult theBind, RedirectAttributes redirectAttributes, Model model) {

        if (theBind.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.urlDto", theBind);
            redirectAttributes.addFlashAttribute("urlDto", urlDto);

            return "redirect:/shortlink/home";
        }else{
            encodedURL = service.convertUrl(urlDto);
            model.addAttribute("encodedURL", encodedURL);
            //redirectAttributes.addFlashAttribute("encodedURL", encodedURL);
            return "/home/homepage";
        }
    }
}
