package com.url.shortener.ui.controller;

import com.url.shortener.ui.model.PageUrl;
import com.url.shortener.ui.model.SearchDto;
import com.url.shortener.ui.model.Url;
import com.url.shortener.ui.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shortlink")
public class UrlListController {

    @Autowired
    UrlService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${backend_url}")
    private String backendURL;

    private List<Url> urlList = null;

    String longUrl="";

    //Start of search

    //SearchDto searchDto = new SearchDto();

    @ModelAttribute("searchDto")
    public SearchDto shortUrl(Model model) {
        //model.addAttribute("longURL", longURL);
        return new SearchDto();
    }

    @GetMapping("/find")
    public String searchLongUrl(@Valid @ModelAttribute("searchDto") SearchDto searchDto, Model model) {

        return paginatedList(1, "id", "asc", searchDto.getLongUrl(), model);
    }

    //End of search..

    @GetMapping("/list")
    public String urlList(Model model) {
        return paginatedList(1, "id", "asc", longUrl, model);
    }


    @GetMapping("/url-list/{pageNo}")
    public String paginatedList(@PathVariable int pageNo, @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir, @RequestParam("longUrl") String longUrl, Model model) {

        Page<PageUrl> page = service.findAll(pageNo, sortField, sortDir, longUrl);
        List<PageUrl> urlList = page.getContent();

        model.addAttribute("longUrl", longUrl);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("urlList", urlList);
        model.addAttribute("backendURL", backendURL + "/");
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "pages/list";
    }

}
