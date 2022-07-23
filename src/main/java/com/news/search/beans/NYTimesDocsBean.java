package com.news.search.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NYTimesDocsBean implements Serializable {
    private String web_url;
    private NYTimesHeadlineBean headline;
}
