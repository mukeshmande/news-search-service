package com.news.search.services;

import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
import com.news.search.utils.NewsProvider;
import com.news.search.utils.NewsProviderFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsSearchService {

    public NewsSearchResponseBean getNewsSearch(String query, int pageNumber) {
        NewsProviderFactory newsProviderFactory = new NewsProviderFactory();
        NewsProvider newsProvider = newsProviderFactory.getNewsProvider(Constants.GUARDIAN);
        return newsProvider.getNews(query, pageNumber);
    }
}
