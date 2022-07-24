package com.news.search.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
import com.news.search.exceptions.NewsSearchException;
import com.news.search.utils.NewsProvider;
import com.news.search.utils.NewsProviderFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsSearchService {

    public String getNewsSearch(String query, int pageNumber) throws NewsSearchException {
        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        NewsSearchResponseBean result = new NewsSearchResponseBean();
        NewsProviderFactory newsProviderFactory = new NewsProviderFactory();
        try {

            for (String channel : Constants.NEWS_CHANNELS) {
                NewsProvider newsProvider = newsProviderFactory.getNewsProvider(channel);
                NewsSearchResponseBean responseBean;

                responseBean = newsProvider.getNews(query, pageNumber);

                result.setTotalPages(result.getTotalPages() + responseBean.getTotalPages());
                result.setSearchKeyword(query);
                result.setCurrentPage(pageNumber);
                result.setNextPage(result.getNextPage() < result.getTotalPages() ? result.getCurrentPage() + 1 : result.getCurrentPage());
                result.setPreviousPage(result.getCurrentPage() > 1 ? result.getCurrentPage() - 1 : 0);
                if (result.getNewsDetailsResponseBeanList() != null)
                    result.getNewsDetailsResponseBeanList().addAll(responseBean.getNewsDetailsResponseBeanList());
                else
                    result.setNewsDetailsResponseBeanList(responseBean.getNewsDetailsResponseBeanList());
            }
            long end = System.currentTimeMillis();
            result.setTimeTaken(end - start);
            return objectMapper.writeValueAsString(result);

        } catch (Exception e) {
            throw new NewsSearchException(e.getMessage());
        }

    }
}
