package com.news.search.common;

import com.news.search.exceptions.NewsSearchException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Constants {

    public static final String GUARDIAN = "The Guardian";
    public static final String NYTIMES = "The New York Times";

    public static final String GUARDIAN_API = "https://content.guardianapis.com/search";
    public static final String NYTIMES_API = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

    private Constants() {
    }

    // provide api keys to run locally
    public static String GUARDIAN_API_KEY = "b231719c-f31e-4708-9ffe-a021c2aaecc0";
    public static String NYTIMES_API_KEY = "Hl7gqlwxElw9U13Ia5e4nwW49TT2XygW";
    public static final List<String> NEWS_CHANNELS = Arrays.asList(new String[]{GUARDIAN, NYTIMES});

    public static void setCredentials() throws NewsSearchException {
        try(InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("credentials.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            GUARDIAN_API_KEY = properties.getProperty("GUARDIAN_API_KEY");
            NYTIMES_API_KEY = properties.getProperty("NYTIMES_API_KEY");
        } catch (IOException e) {
            throw new NewsSearchException(e.getMessage());
        }


    }
}
