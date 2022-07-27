package com.news.search.beans.nytimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NYTimesDocsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String web_url;
    private NYTimesHeadlineBean headline;
}
