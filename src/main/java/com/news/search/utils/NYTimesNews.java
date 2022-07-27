package com.news.search.utils;

import com.news.search.beans.NewsDetailsResponseBean;
import com.news.search.beans.NewsSearchResponseBean;
import com.news.search.beans.nytimes.NYTimesBean;
import com.news.search.beans.nytimes.NYTimesResponseBean;
import com.news.search.common.Constants;
import com.news.search.configs.RestTemplateConfig;
import com.news.search.exceptions.NewsSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NYTimesNews implements NewsProvider{

    private static final Logger LOGGER = LoggerFactory.getLogger(NYTimesNews.class);

    @Override
    public NewsSearchResponseBean getNews(String query, int page) throws NewsSearchException {
        LOGGER.info("getting news from The New York Times : {} ", query);

        NewsSearchResponseBean newsSearchResponseBean = new NewsSearchResponseBean();
        List<NewsDetailsResponseBean> newsDetailsResponseBeanList = new ArrayList<>();
        NYTimesBean nyTimesBean;
        try {
            nyTimesBean = RestTemplateConfig.getNewsFromProviderAPI(NYTimesBean.class, Constants.NYTIMES_API, query, page, Constants.NYTIMES_API_KEY);

        NYTimesResponseBean responseBean = nyTimesBean.getResponse();

        int totalPages = responseBean.getMeta().getHits()/10;
        if(responseBean.getMeta().getHits()%10 > 0)  totalPages+=1;

        newsSearchResponseBean.setTotalPages(totalPages);

        responseBean.getDocs().forEach(item -> {
            NewsDetailsResponseBean detailsResponseBean = new NewsDetailsResponseBean();
            detailsResponseBean.setNewsWebsite(Constants.NYTIMES);
            detailsResponseBean.setHeadLine(item.getHeadline().getMain());
            detailsResponseBean.setUrl(item.getWeb_url());
            newsDetailsResponseBeanList.add(detailsResponseBean);
        });

        newsSearchResponseBean.setNewsDetailsResponseBeanList(newsDetailsResponseBeanList);

        } catch (Exception e){
            throw new NewsSearchException(e.getMessage());
        }
        return newsSearchResponseBean;
    }
}
