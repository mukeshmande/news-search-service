package com.news.search.beans.guardian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GuardianResultBean implements Serializable {

    @JsonProperty("webTitle")
    private String webTitle;

    @JsonProperty("webUrl")
    private String webUrl;
}
