package com.news.search.beans.nytimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NYTimesResponseBean implements Serializable {
    private List<NYTimesDocsBean> docs;
    private NYTimesMetaBean meta;
}
