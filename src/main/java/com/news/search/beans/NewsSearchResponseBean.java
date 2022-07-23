package com.news.search.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsSearchResponseBean implements Serializable {
    private int totalPages;
    private String searchKeyword;
    private String city;
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private long timeTaken;
    private List<NewsDetailsResponseBean> newsDetailsResponseBeanList;

}
