package com.url.shortener.ui.controller;

import com.url.shortener.ui.model.PageUrl;
import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/shortlink")
public class UrlListController {

    @Autowired
    UrlService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${backend_url}")
    private String backendURL;

    private List<Url> urlList=null;



    /*
    @GetMapping("/list")
    public String urlList(Model model){
        urlList = service.getAllUrlList();
        model.addAttribute("urlList", urlList);
        model.addAttribute("backendURL", backendURL+"/");
        return "pages/list";
    }
    */
    @GetMapping("/list")
    public String urlList(Model model){

        return paginatedList(1, "id", "asc", model);
    }


    @GetMapping("/url-list/{pageNo}")
    public String paginatedList(@PathVariable int pageNo, @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir, Model model){

        Page<PageUrl> page = service.findAll(pageNo, sortField, sortDir);
        List<PageUrl> urlList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("urlList", urlList);
        model.addAttribute("backendURL", backendURL+"/");
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" : "asc");

        return "pages/list";
    }

}
