package com.news.search.beans.guardian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class GuardianResponseBean implements Serializable {

    @JsonProperty("pages")
    private int pages;
    @JsonProperty("results")
    private List<GuardianResultBean> results;



}
