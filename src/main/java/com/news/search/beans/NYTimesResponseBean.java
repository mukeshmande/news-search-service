package com.news.search.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NYTimesResponseBean implements Serializable {
    private List<NYTimesDocsBean> docs;
    private NYTimesMetaBean meta;
}
