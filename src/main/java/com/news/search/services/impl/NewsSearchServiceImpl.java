package com.news.search.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
import com.news.search.controllers.NewsSearchController;
import com.news.search.exceptions.NewsSearchException;
import com.news.search.services.NewsSearchService;
import com.news.search.utils.NewsProvider;
import com.news.search.utils.NewsProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsSearchServiceImpl implements NewsSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSearchServiceImpl.class);

    @Override
    public String getNewsSearch(String query, int pageNumber) throws NewsSearchException {
        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        NewsSearchResponseBean result = new NewsSearchResponseBean();
        NewsProviderFactory newsProviderFactory = new NewsProviderFactory();
        try {
            int totalPages = 0;
            for (String channel : Constants.NEWS_CHANNELS) {
                NewsProvider newsProvider = newsProviderFactory.getNewsProvider(channel);
                NewsSearchResponseBean responseBean;

                responseBean = newsProvider.getNews(query, pageNumber);
                totalPages += responseBean.getTotalPages();
                result.setSearchKeyword(query);
                result.setCurrentPage(pageNumber);
                result.setNextPage(result.getNextPage() < responseBean.getTotalPages() ? result.getCurrentPage() + 1 : result.getCurrentPage());
                result.setPreviousPage(result.getCurrentPage() > 1 ? result.getCurrentPage() - 1 : 0);
                if (result.getNewsDetailsResponseBeanList() != null)
                    result.getNewsDetailsResponseBeanList().addAll(responseBean.getNewsDetailsResponseBeanList());
                else
                    result.setNewsDetailsResponseBeanList(responseBean.getNewsDetailsResponseBeanList());
            }
            long end = System.currentTimeMillis();
            result.setTimeTaken(end - start);
            result.setTotalPages(totalPages/2);

            if(totalPages%2 > 0)  result.setTotalPages(result.getTotalPages()+1);
            return objectMapper.writeValueAsString(result);

        } catch (Exception e) {
            throw new NewsSearchException(e.getMessage());
        }

    }
}
