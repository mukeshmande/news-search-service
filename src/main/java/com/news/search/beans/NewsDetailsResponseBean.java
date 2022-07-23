package com.news.search.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDetailsResponseBean implements Serializable {
    private String newsWebsite;
    private String headLine;
    private String url;
}
