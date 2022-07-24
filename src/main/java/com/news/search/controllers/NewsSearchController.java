package com.news.search.controllers;

import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.exceptions.NewsSearchException;
import com.news.search.services.NewsSearchService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "news-search")
@Api(value = "news-search-controller", tags = "News Search Controller")
public class NewsSearchController {
    @Autowired
    NewsSearchService newsSearchService;


    @ApiOperation(value = "Fetch relevant news for a keyword", notes = "Returns all relevant news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=String.class),
            @ApiResponse(code = 400, message = "Invalid input supplied"),
            @ApiResponse(code = 404, message = "Result not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @GetMapping(value = "/")
    public ResponseEntity<String> getNewsSearch(
            @ApiParam("Query to fetch news results. Cannot be empty.")
            @RequestParam(name = "query") String query,
            @ApiParam("Page number . Cannot be empty.")
    @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber){

        String result;
        try {
            result = newsSearchService.getNewsSearch(query, pageNumber);
        } catch (NewsSearchException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
