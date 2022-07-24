package com.news.search.common;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String GUARDIAN = "The Guardian";
    public static final String NYTIMES = "The New York Times";

    public static final String GUARDIAN_API = "https://content.guardianapis.com/search";
    public static final String NYTIMES_API = "https://api.nytimes.com/svc/search/v2/articlesearch.json";


    public static final String GUARDIAN_API_KEY = "b231719c-f31e-4708-9ffe-a021c2aaecc0";
    public static final String NYTIMES_API_KEY = "Hl7gqlwxElw9U13Ia5e4nwW49TT2XygW";

    public static final List<String> NEWS_CHANNELS = Arrays.asList(new String[]{GUARDIAN, NYTIMES});

}
