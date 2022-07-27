package com.news.search.controllers;

import com.news.search.common.Constants;
import com.news.search.exceptions.NewsSearchException;
import com.news.search.services.impl.NewsSearchServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "news-search")
@Api(value = "news-search-controller", tags = "News Search Controller")
public class NewsSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSearchController.class);


    @Autowired
    NewsSearchServiceImpl newsSearchService;


    @ApiOperation(value = "Fetch relevant news for a keyword", notes = "Returns all relevant news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=String.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(value = "/")
    public ResponseEntity<String> getNewsSearch(
            @ApiParam("Query to fetch news results. Cannot be empty.")
            @RequestParam(name = "query") String query,
            @ApiParam("Page number . Cannot be empty.")
    @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber){
        LOGGER.info("Calling getNewsSearch with query - "+query);
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


    // comment this method to run locally
//    @PostConstruct
//    public void intializeApiCredentials() throws NewsSearchException {
//        Constants.setCredentials();
//    }

}
