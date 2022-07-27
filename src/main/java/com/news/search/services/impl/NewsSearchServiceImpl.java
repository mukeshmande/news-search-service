package com.news.search.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.common.Constants;
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
        LOGGER.info("getting search result: {}", query);
        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        NewsSearchResponseBean result = new NewsSearchResponseBean();
        NewsProviderFactory newsProviderFactory = new NewsProviderFactory();
        StringBuilder errorMsg = new StringBuilder();

            int totalPages = 0;
            for (String channel : Constants.NEWS_CHANNELS) {
                NewsProvider newsProvider = newsProviderFactory.getNewsProvider(channel);
                NewsSearchResponseBean responseBean;

                try {
                    responseBean = newsProvider.getNews(query, pageNumber);
                } catch (NewsSearchException e) {
                    LOGGER.error("Error in getNewsSearch while fetching news: {}",  e.getMessage());
                    errorMsg.append(e.getMessage()+" ");
                    continue;
                }
                totalPages += responseBean.getTotalPages();
                result.setSearchKeyword(query);
                result.setCurrentPage(pageNumber);

                    result.getNewsDetailsResponseBeanList().addAll(responseBean.getNewsDetailsResponseBeanList());
            }
            result.setNextPage(result.getCurrentPage() < totalPages ? result.getCurrentPage() + 1 : result.getCurrentPage());
            result.setPreviousPage(result.getCurrentPage() - 1);
            long end = System.currentTimeMillis();
            result.setTimeTaken(end - start);

        try {
            if(result.getNewsDetailsResponseBeanList().size()!=0){
                if(result.getNewsDetailsResponseBeanList().size()>10){
                    result.setTotalPages(totalPages/2);
                    if(totalPages%2 > 0)  result.setTotalPages(result.getTotalPages()+1);
                }else
                    result.setTotalPages(totalPages);

                return objectMapper.writeValueAsString(result);
            }
            else{
                throw new NewsSearchException(errorMsg.toString());
            }


        } catch (JsonProcessingException e) {
            throw new NewsSearchException(e.getMessage());
        }
    }
}
